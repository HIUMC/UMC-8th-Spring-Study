package com.example.hongik_hospital.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "appointment",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"patient_id", "doctor_id"})
        }
)
public class Appointment {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime reserveTime;

    private Boolean isCompleted;

    private Integer cost;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
