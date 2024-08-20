package com.gestaotech.api.dto.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestaotech.api.enums.StructureEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class GoogleUserCreateDto {
    @NotBlank(message = "É necessário informar o nome do usuário")
    private String name;
    @JsonProperty("structure_id")
    @NotNull(message = "É necessário informar a estrutura do usuário")
    private StructureEnum structureId;
    @NotBlank(message = "É necessário informar o semestre de início do usuário")
    private String start;
    @NotBlank(message = "É necessário informar o email do usuário")
    @Email(message = "Email invalido")
    private String email;
    @NotNull(message = "Url da imagem não pode ser null")
    @JsonProperty("image_url")
    private String imageUrl;

}
