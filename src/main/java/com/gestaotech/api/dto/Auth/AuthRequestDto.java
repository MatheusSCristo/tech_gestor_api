package com.gestaotech.api.dto.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDto {

    @NotBlank(message = "É necessário informar o email do usuário")
    @Email
    private String username;
    @NotBlank(message = "É necessário informar a senha do usuário")
    private String password;
}
