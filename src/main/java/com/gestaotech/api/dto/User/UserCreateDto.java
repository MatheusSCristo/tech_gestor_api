package com.gestaotech.api.dto.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestaotech.api.entity.Structure;
import com.gestaotech.api.enums.StructureEnum;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserCreateDto {
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
    @NotBlank(message = "É necessário informar a senha do usuário")
    @Length(min = 6,message = "A senha precisa ter no mínimo 6 caracteres")
    private String password;
    @NotNull(message = "Url da imagem não pode ser null")
    @JsonProperty("image_url")
    private String imageUrl;

}
