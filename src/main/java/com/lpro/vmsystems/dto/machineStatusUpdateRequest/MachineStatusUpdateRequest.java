package com.lpro.vmsystems.dto.machineStatusUpdateRequest;

import com.lpro.vmsystems.infrastruture.entity.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MachineStatusUpdateRequest {
    private Status status;
}

