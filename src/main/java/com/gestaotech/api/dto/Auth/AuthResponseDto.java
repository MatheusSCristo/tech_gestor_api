package com.gestaotech.api.dto.Auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestaotech.api.dto.User.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDto {
    private UserResponseDto userResponseDto;
    @JsonProperty("access_token")
    private String accessToken;

}
