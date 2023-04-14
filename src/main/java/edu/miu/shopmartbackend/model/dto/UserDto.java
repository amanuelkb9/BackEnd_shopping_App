package edu.miu.shopmartbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.miu.shopmartbackend.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @JsonProperty("user_name")
    private String username;

    @JsonProperty("points")
    private int points;
    private boolean isAproved;

}
