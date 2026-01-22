package com.lpro.vmsystems.controller;

import com.lpro.vmsystems.business.MachineService;
import com.lpro.vmsystems.dto.machineCreateRequest.MachineCreateRequest;
import com.lpro.vmsystems.dto.machineCreateResponse.MachineResponse;
import com.lpro.vmsystems.dto.machineStatusUpdateRequest.MachineStatusUpdateRequest;
import com.lpro.vmsystems.dto.machineUpdateRequest.MachineUpdateRequest;
import com.lpro.vmsystems.infrastruture.entity.Machine;
import com.lpro.vmsystems.infrastruture.entity.enums.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/machine")
@RequiredArgsConstructor
@Tag(name = "machine", description = "Virtual machine management controller")
public class MachineController {
    private final MachineService machineService;

    @PostMapping
    @Operation(summary = "Machine Data Save", description = "method for creating a virtual machine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<MachineResponse> saveMachine(@RequestBody @Valid MachineCreateRequest request) {
        Machine savedMachine = machineService.saveMachine(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMachine.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }


    @GetMapping("/{id}")
    @Operation(summary = "Find Machine By ID", description = "method for find a virtual machine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successed finding"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<MachineResponse> findMachineById(@PathVariable Integer id) {
        Machine machine = machineService.findMachineById(id);
        return ResponseEntity.ok(new MachineResponse(machine));
    }


    @GetMapping
    @Operation(
            summary = "Find all registered machines",
            description = "Returns a list of all virtual machines"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved machines"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<MachineResponse>> findAllMachines() {
        List<MachineResponse> machines = machineService.findAllMachines()
                .stream()
                .map(MachineResponse::new)
                .toList();
        return ResponseEntity.ok(machines);
    }



    @DeleteMapping
    @Operation(summary = "Delete Machine By ID", description = "method for delete a virtual machine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Successed delete"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteMachineById(@RequestParam Integer id) {
        machineService.deleteMachineById(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update Machine By ID", description = "method for update a virtual machine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successed update"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> updateMachineById(@PathVariable Integer id, @RequestBody MachineUpdateRequest machine) {
        machineService.updateMachineById(id, machine);
        return ResponseEntity.ok().build();
    }


    @PatchMapping("/{id}/status")
    @Operation(summary = "Status Update Machine By ID", description = "method for update the status of the virtual machine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successed status update"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> changeStatusMachineById(@PathVariable Integer id, @RequestBody MachineStatusUpdateRequest status) {
        machineService.changeMachineStatus(id, status.getStatus());
        return ResponseEntity.noContent().build();
    }

}
