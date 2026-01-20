package com.lpro.vmsystems.business;

import com.lpro.vmsystems.infrastruture.entity.Machine;
import com.lpro.vmsystems.infrastruture.entity.enums.Status;
import com.lpro.vmsystems.infrastruture.entity.repository.MachineRepositoy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MachineService {
    private final MachineRepositoy machineRepository;

    public Machine saveMachine(Machine machine){
        machineRepository.saveAndFlush(machine);
        return machine;
    }

    public Machine findMachineById(Integer id){
        return machineRepository.findMachineById(id).orElseThrow(
                () -> new RuntimeException("Machine with id " + id + " not found")
        );
    }

    public void updateMachineById(Integer id, Machine machine){
        Machine machineEntity = findMachineById(id);

        Machine machineUpdate = Machine.builder()
                .name(machine.getName() != null ?
                        machine.getName() : machineEntity.getName())

                .cpu(machine.getCpu() != null ?
                        machine.getCpu() : machineEntity.getCpu())

                .memory(machine.getMemory() != null ?
                        machine.getMemory() : machineEntity.getMemory())

                .disk(machine.getDisk() != null ?
                        machine.getDisk() : machineEntity.getDisk())

                .status(machine.getStatus() != null ?
                        machine.getStatus() : machineEntity.getStatus())

                .id(machineEntity.getId())
                .dateCreation(machineEntity.getDateCreation())
                .build();

        machineRepository.save(machineUpdate);
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
