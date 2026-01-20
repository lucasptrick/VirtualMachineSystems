package com.lpro.vmsystems.controller;

import com.lpro.vmsystems.business.MachineService;
import com.lpro.vmsystems.infrastruture.entity.Machine;
import com.lpro.vmsystems.infrastruture.entity.enums.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    public ResponseEntity<Void> saveMachine(@RequestBody Machine machine) {
        Machine savedMachine = machineService.saveMachine(machine);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMachine.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }


    @GetMapping
    @Operation(summary = "Find Machine By ID", description = "method for find a virtual machine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successed finding"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    public ResponseEntity<Machine> findMachineById(@RequestParam Integer id) {
        return ResponseEntity.ok(machineService.findMachineById(id));
    }


    @DeleteMapping
    @Operation(summary = "Delete Machine By ID", description = "method for delete a virtual machine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Successed delete"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    public ResponseEntity<Void> deleteMachine(@RequestParam Integer id) {
        machineService.deleteMachineById(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping
    @Operation(summary = "Update Machine By ID", description = "method for update a virtual machine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successed update"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    public ResponseEntity<Void> updateMachine(@RequestParam Integer id, @RequestBody Machine machine) {
        machineService.updateMachineById(id, machine);
        return ResponseEntity.ok().build();
    }


    @PatchMapping("/{id}/status")
    @Operation(summary = "Status Update Machine By ID", description = "method for update the status of the virtual machine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successed status update"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    public ResponseEntity<Machine> changeStatus(@PathVariable Integer id, @RequestParam Status status) {
        machineService.changeMachineStatus(id, status);
        return ResponseEntity.noContent().build();
    }

}
