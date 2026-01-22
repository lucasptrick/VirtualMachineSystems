package com.lpro.vmsystems.dto.machineCreateResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@Schema(description = "Dados retornados de uma Machine")
public class MachineResponse {

    @Schema(example = "1")
    private Integer id;

    @Schema(example = "Servidor Principal")
    private String name;

    @Schema(example = "4")
    private Integer cpu;

    @Schema(example = "16.0")
    private Double memory;

    @Schema(example = "256.0")
    private Double disk;

    @Schema(example = "2026-01-21T18:25:56")
    private LocalDateTime dateCreation;

    @Schema(example = "STARTED")
    private String status;

    public MachineResponse(com.lpro.vmsystems.infrastruture.entity.Machine machine) {
        this.id = machine.getId();
        this.name = machine.getName();
        this.cpu = machine.getCpu();
        this.memory = machine.getMemory();
        this.disk = machine.getDisk();
        this.dateCreation = machine.getDateCreation();
        this.status = machine.getStatus().name();
    }
}

