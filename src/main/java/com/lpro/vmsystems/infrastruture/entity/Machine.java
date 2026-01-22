package com.lpro.vmsystems.infrastruture.entity;

import com.lpro.vmsystems.infrastruture.entity.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "machine")
@Entity
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 5, message = "Must have at least 5 characters.")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Positive(message = "Must have positive values.")
    @Column(name = "cpu")
    private Integer cpu;

    @Positive(message = "Must have positive values.")
    @Column(name = "memory")
    private Double memory;

    @Positive(message = "Must have positive values.")
    @Column(name = "disk")
    private Double disk;

    @Column(name = "dateCreation", updatable = false)
    private LocalDateTime dateCreation;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;


    @PrePersist
    public void prePersist() {
        this.dateCreation = LocalDateTime.now();

        if (this.status == null) {
            this.status = Status.STARTED;
        }
    }
}
