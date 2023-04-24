package edu.miu.shopmartbackend.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.shopmartbackend.model.Role;
import edu.miu.shopmartbackend.model.dto.UserDto;
import edu.miu.shopmartbackend.model.dto.UsernamePassDto;
import edu.miu.shopmartbackend.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link UserController#addUser(UsernamePassDto)}
     */
    @Test
    void testAddUser() throws Exception {
        doNothing().when(userService).addUser((UsernamePassDto) any());

        UsernamePassDto usernamePassDto = new UsernamePassDto();
        usernamePassDto.setPassword("iloveyou");
        usernamePassDto.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(usernamePassDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/users/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link UserController#deleteUser(long)}
     */
    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(userService).deleteUser(anyLong());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/users/{id}", 1L);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link UserController#deleteUser(long)}
     */
    @Test
    void testDeleteUser2() throws Exception {
        doNothing().when(userService).deleteUser(anyLong());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/v1/users/{id}", 1L);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link UserController#approveSeller(long)}
     */
    @Test
    void testApproveSeller() throws Exception {
        when(userService.approveSeller(anyLong())).thenReturn(new UserDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .patch("/api/v1/users/{seller_id}/approveseller", 1L);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"firstName\":null,\"lastName\":null,\"username\":null,\"password\":null,\"email\":null,\"aproved"
                                        + "\":false}"));
    }

    /**
     * Method under test: {@link UserController#approveBuyer(long)}
     */
    @Test
    void testApproveBuyer() throws Exception {
        when(userService.approveBuyer(anyLong())).thenReturn(new UserDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .patch("/api/v1/users/{buyer_id}/approvebuyer", 1L);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"firstName\":null,\"lastName\":null,\"username\":null,\"password\":null,\"email\":null,\"aproved"
                                        + "\":false}"));
    }

    /**
     * Method under test: {@link UserController#refreshToken(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRefreshToken() throws Exception {
        doNothing().when(userService).refreshToken((HttpServletRequest) any(), (HttpServletResponse) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users/token/refresh");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link UserController#registerUser(UserDto)}
     */
    @Test
    void testRegisterUser() throws Exception {
        when(userService.registerUser((UserDto) any())).thenReturn(new UserDto());

        UserDto userDto = new UserDto();
        userDto.setAproved(true);
        userDto.setEmail("jane.doe@example.org");
        userDto.setFirstName("Jane");
        userDto.setId(1L);
        userDto.setLastName("Doe");
        userDto.setPassword("iloveyou");
        userDto.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(userDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/users/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"username\":\"janedoe\",\"password\":\"iloveyou\",\"email\":\"jane"
                                        + ".doe@example.org\",\"aproved\":true}"));
    }

    /**
     * Method under test: {@link UserController#registerUserWithRole(UserDto, String)}
     */
    @Test
    void testRegisterUserWithRole() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        when(userService.approveSeller(anyLong())).thenReturn(new UserDto());
        when(userService.registerUser((UserDto) any())).thenReturn(userDto);
        doNothing().when(userService).addRoleToUser((String) any(), (String) any());
        doNothing().when(userService).addUser((UsernamePassDto) any());

        UserDto userDto1 = new UserDto();
        userDto1.setAproved(true);
        userDto1.setEmail("jane.doe@example.org");
        userDto1.setFirstName("Jane");
        userDto1.setId(1L);
        userDto1.setLastName("Doe");
        userDto1.setPassword("iloveyou");
        userDto1.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(userDto1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/users/addUser/{role}", "Role")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("registeredUser"));
    }

    /**
     * Method under test: {@link UserController#saveRole(Role)}
     */
    @Test
    void testSaveRole() throws Exception {
        Role role = new Role();
        role.setId(1L);
        role.setRole("Role");
        String content = (new ObjectMapper()).writeValueAsString(role);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }
}

