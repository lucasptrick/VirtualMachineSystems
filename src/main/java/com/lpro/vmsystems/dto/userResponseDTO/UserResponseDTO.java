package com.lpro.vmsystems.dto.userResponseDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
@Schema(description = "Data returned from a User")
public class UserResponseDTO {

    @Schema(example = "1")
    Integer id;

    @Schema(example = "Fulano de Tal")
    String name;

    @Schema(example = "fulano@email.com")
    String email;

    public UserResponseDTO(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}

