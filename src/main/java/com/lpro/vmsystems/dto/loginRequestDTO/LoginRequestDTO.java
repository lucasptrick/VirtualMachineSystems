package com.lpro.vmsystems.dto.loginRequestDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Data for creating a LoginUser")
public class LoginRequestDTO {

        @NotNull
        @Email
        @Schema(example = "fulano@email.com")
        String email;


        @NotBlank
        @Schema(example = "123")
        String password;
}

