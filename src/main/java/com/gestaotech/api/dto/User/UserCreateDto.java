package com.gestaotech.api.dto.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestaotech.api.entity.Structure;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class UserCreateDto {
    private String name;
    @JsonProperty("structure_id")
    private Integer structureId;
    private String start;
    private String email;
    private String password;
    @JsonProperty("image_url")
    private String imageUrl;

}
