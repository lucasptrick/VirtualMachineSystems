package com.lpro.vmsystems.dto.machineUpdateRequest;

import com.lpro.vmsystems.infrastruture.entity.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Dados para atualização de uma Machine")
public class MachineUpdateRequest {

    private String name;
    private Integer cpu;
    private Double memory;
    private Double disk;
    private Status status;
}

