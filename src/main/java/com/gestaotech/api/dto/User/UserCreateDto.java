package com.gestaotech.api.dto.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestaotech.api.entity.Structure;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserCreateDto {
    @NotBlank
    private String name;
    @JsonProperty("structure_id")
    @NotNull
    private Integer structureId;
    @NotBlank
    private String start;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Length(min = 6)
    private String password;
    @JsonProperty("image_url")
    private String imageUrl;

}
