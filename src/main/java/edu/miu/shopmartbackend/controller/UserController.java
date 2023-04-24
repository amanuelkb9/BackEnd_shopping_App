package edu.miu.shopmartbackend.controller;

import edu.miu.shopmartbackend.model.Role;
import edu.miu.shopmartbackend.model.dto.RoleToUserDto;
import edu.miu.shopmartbackend.model.dto.UserDto;
import edu.miu.shopmartbackend.model.dto.UsernamePassDto;
import edu.miu.shopmartbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

   @Autowired
   private  final UserService userService;


    @PostMapping("/add")
    public void addUser(@RequestBody UsernamePassDto user) {
         userService.addUser(user);
    }

     @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }


    @PatchMapping("/{seller_id}/approveseller")
    UserDto approveSeller( @PathVariable long seller_id){
        return userService.approveSeller(seller_id);
    }


    @PatchMapping("/{buyer_id}/approvebuyer")
    UserDto approveBuyer( @PathVariable long buyer_id){
        return userService.approveBuyer(buyer_id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.refreshToken(request, response);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Role saveRole(@RequestBody Role role) {
        return userService.saveRole(role);
    }

//    @PostMapping("/users")
//    @ResponseStatus(HttpStatus.OK)
//    public void addRoleToUser(@RequestBody RoleToUserDto roleToUserDto) {
//        System.out.println("add role...........");
//        userService.addRoleToUser(roleToUserDto.getUsername(), roleToUserDto.getRole());
//    }
    @PostMapping("/addUser")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto){
        System.out.println(userDto);

        userService.registerUser(userDto);
        System.out.println(userDto);
        return new ResponseEntity(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/addUser/{role}")
    public String registerUserWithRole(@RequestBody UserDto userDto, @PathVariable String role){
        System.out.println(userDto);
//        Role role1 = new Role(null,role);
//        userService.saveRole(role1);
        userService.addUser(new UsernamePassDto(userDto.getUsername(),userDto.getPassword()));
        userService.addRoleToUser( userDto.getUsername(), role);
//         var userRole= userService.getRoleByRoleName(role);
         UserDto registeredUser =  userService.registerUser(userDto);
        System.out.println("registered user --- " + registeredUser );
        UserDto userDto1 = (role.equals("buyer")) ? userService.approveBuyer(registeredUser.getId())
                        : userService.approveSeller(registeredUser.getId());
        System.out.println("after user registered &  aprroved --- " + userDto1 );
//          userService.addRoleToUser(userDto.getUsername(), userRole);
//          return new ResponseEntity("user registered successfully ", HttpStatus.CREATED);
        return "registeredUser";
      }
    }




