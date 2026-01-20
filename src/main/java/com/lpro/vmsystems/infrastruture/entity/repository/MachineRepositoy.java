package com.lpro.vmsystems.infrastruture.entity.repository;

import com.lpro.vmsystems.infrastruture.entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MachineRepositoy extends JpaRepository<Machine, Integer> {
    Optional<Machine> findMachineById(Integer id);

    @Transactional
    void deleteMachineById(Integer id);
}
