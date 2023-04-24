package edu.miu.shopmartbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleDto {

    @JsonProperty("role_id")
    private Long id;
    @JsonProperty("role_name")
    private String role;



}

