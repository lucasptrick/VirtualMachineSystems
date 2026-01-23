package com.lpro.vmsystems.business;

import com.lpro.vmsystems.dto.machineCreateRequest.MachineCreateRequest;
import com.lpro.vmsystems.dto.machineCreateResponse.MachineResponse;
import com.lpro.vmsystems.dto.machineUpdateRequest.MachineUpdateRequest;
import com.lpro.vmsystems.infrastruture.entity.Machine;
import com.lpro.vmsystems.infrastruture.entity.User;
import com.lpro.vmsystems.infrastruture.entity.enums.Status;
import com.lpro.vmsystems.infrastruture.entity.repository.MachineRepository;
import com.lpro.vmsystems.infrastruture.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MachineService {
    private final MachineRepository machineRepository;
    private final UserRepository userRepository;
    private final AuthService authService;

    public Machine saveMachine(MachineCreateRequest request, Integer userId) {

        User loggedUser = authService.getAuthenticatedUser(userId);


        Machine machine = new Machine();
        machine.setName(request.getName());
        machine.setCpu(request.getCpu());
        machine.setMemory(request.getMemory());
        machine.setDisk(request.getDisk());

        machine.setStatus(Status.STARTED);
        machine.setDateCreation(LocalDateTime.now());
        machine.setUser(loggedUser);


        return machineRepository.saveAndFlush(machine);

    }

    public Machine findMachineById(Integer id){
        return machineRepository.findMachineById(id).orElseThrow(
                () -> new RuntimeException("Machine with id " + id + " not found")
        );
    }

    public List<Machine> findAllMachines() {
        List<Machine> machines = machineRepository.findAll();

        if (machines.isEmpty()) {
            throw new RuntimeException("No virtual machines registered");
        }

        return machines;
    }

    public List<MachineResponse> findByUser(Integer userId) {

        List<Machine> machines = machineRepository.findByUserId(userId);

        if (machines.isEmpty()) {
            throw new RuntimeException("User has no machines");
        }

        return machines
                .stream()
                .map(MachineResponse::new) // 👈 usa o construtor que recebe Machine
                .toList();
    }





    public void updateMachineById(Integer id, MachineUpdateRequest machine) {
        Machine machineEntity = findMachineById(id);

        if (machine.getName() != null) machineEntity.setName(machine.getName());
        if (machine.getCpu() != null) machineEntity.setCpu(machine.getCpu());
        if (machine.getMemory() != null) machineEntity.setMemory(machine.getMemory());
        if (machine.getDisk() != null) machineEntity.setDisk(machine.getDisk());

        machineRepository.save(machineEntity);
    }

    public void changeMachineStatus(Integer id, Status status){
        Machine machineToUpdate = findMachineById(id);

        if (machineToUpdate.getStatus() == Status.STOPPED &&
                (status == Status.SUSPENDED || status == Status.STOPPED)) {
            throw new IllegalStateException("Machine with id " + id + " is already stopped");
        }

        machineToUpdate.setStatus(status);
        machineRepository.save(machineToUpdate);
    }

    public void deleteMachineById(Integer id){
        Machine machineToDelete = findMachineById(id);
        machineRepository.deleteMachineById(machineToDelete.getId());
    }


}
