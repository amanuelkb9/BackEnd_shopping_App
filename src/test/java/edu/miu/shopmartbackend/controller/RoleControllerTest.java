package edu.miu.shopmartbackend.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.shopmartbackend.model.Role;
import edu.miu.shopmartbackend.model.dto.RoleToUserDto;
import edu.miu.shopmartbackend.service.UserService;
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

@ContextConfiguration(classes = {RoleController.class})
@ExtendWith(SpringExtension.class)
class RoleControllerTest {
    @Autowired
    private RoleController roleController;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link RoleController#addRoleToUser(RoleToUserDto)}
     */
    @Test
    void testAddRoleToUser() throws Exception {
        doNothing().when(userService).addRoleToUser((String) any(), (String) any());

        RoleToUserDto roleToUserDto = new RoleToUserDto();
        roleToUserDto.setRole("Role");
        roleToUserDto.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(roleToUserDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/roles/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(roleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link RoleController#saveRole(Role)}
     */
    @Test
    void testSaveRole() throws Exception {
        Role role = new Role();
        role.setId(1L);
        role.setRole("Role");
        String content = (new ObjectMapper()).writeValueAsString(role);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/roles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(roleController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }
}

