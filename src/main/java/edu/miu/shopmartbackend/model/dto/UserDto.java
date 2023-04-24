package edu.miu.shopmartbackend.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import edu.miu.shopmartbackend.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    @JsonProperty("user_id")
    private Long id;

    @JsonProperty("f_name")
    private String firstname;

    @JsonProperty("l_name")
    private String lastname;

    @JsonProperty("user_role")
    private List<Role> roles;

    @Column(nullable=false)
    @NotNull(message = "* userName is required")
    private String username;

    @Column(nullable=false)
    @NotNull(message = "* password is required")
    private String password;
    @Email(message="{errors.invalid_email}")
    private String email;
    //    @JsonProperty("points")
//    private int points;
    private boolean isApproved;


    public UserDto(Long id, String username, String email, List<Role> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;


    }
}
