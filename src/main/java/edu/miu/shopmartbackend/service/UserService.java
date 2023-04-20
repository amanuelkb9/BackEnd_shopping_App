package edu.miu.shopmartbackend.service;

import edu.miu.shopmartbackend.model.*;
import edu.miu.shopmartbackend.model.dto.UserDto;
import edu.miu.shopmartbackend.model.dto.UsernamePassDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface UserService {


    void addUser(UsernamePassDto user);

    void deleteUser(long id);

    Role saveRole(Role role);

    void addRoleToUser(String username, String role);

    UserDto approveSeller(long id);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;


<<<<<<<<< Temporary merge branch 1
    UserDto registerUser(UserDto userDto);

    UserDto approveBuyer(long buyer_id);
||||||||| 08421f4
=========
    UserDto approveBuyer(long buyer_id);
>>>>>>>>> Temporary merge branch 2
}
