package com.lpro.vmsystems.dto.userRequestDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Data for creating a user")
public class UserRequestDTO {

    @NotNull
    @Schema(example = "Fulano de Tal")
    String name;

    @NotNull
    @Schema(example = "fulano@email.com")
    String email;

    @NotNull
    @Schema(example = "123")
    String password;
}

