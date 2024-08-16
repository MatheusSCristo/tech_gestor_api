package com.gestaotech.api.dto.Auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GoogleAuthRequestDto {
    @JsonProperty("access_token")
    public String accessToken;
}
