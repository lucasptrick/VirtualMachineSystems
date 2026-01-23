package com.lpro.vmsystems.dto.loginResponseDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Data returned from a LoginUser")
public class LoginResponseDTO {

    @Schema(example = "1")
    private Integer id;

    @Schema(example = "Fulano de Tal")
    private String name;

    @Schema(example = "fulano@email.com")
    private String email;



}
