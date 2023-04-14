package edu.miu.shopmartbackend.controller;

import edu.miu.shopmartbackend.model.Role;
import edu.miu.shopmartbackend.model.dto.RoleToUserDto;
import edu.miu.shopmartbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Role saveRole(@RequestBody Role role) {
        return userService.saveRole(role);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public void addRoleToUser(@RequestBody RoleToUserDto roleToUserDto) {
        System.out.println("add role...........");
        userService.addRoleToUser(roleToUserDto.getUsername(), roleToUserDto.getRole());
    }
}
