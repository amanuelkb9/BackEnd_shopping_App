package edu.miu.shopmartbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsernamePassDto {

        @Column(nullable=false)
    @NotNull(message = "* userName is required")
    private String username;

    @Column(nullable=false)
    @NotNull(message = "* password is required")
    private String password;
}
