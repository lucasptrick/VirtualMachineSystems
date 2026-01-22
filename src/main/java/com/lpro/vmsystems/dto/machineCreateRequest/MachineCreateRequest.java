package com.lpro.vmsystems.dto.machineCreateRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Dados para criação de uma Machine")
public class MachineCreateRequest {

    @NotBlank
    @Schema(example = "Servidor de Produção")
    private String name;

    @NotNull
    @Schema(example = "4")
    private Integer cpu;

    @NotNull
    @Schema(example = "16.0")
    private Double memory;

    @NotNull
    @Schema(example = "256.0")
    private Double disk;

    // getters e setters
}

