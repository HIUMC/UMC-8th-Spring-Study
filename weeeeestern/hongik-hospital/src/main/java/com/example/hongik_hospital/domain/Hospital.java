package com.example.hongik_hospital.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hospital  {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String address;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Department> departments = new ArrayList<>();

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Doctor> doctors = new ArrayList<>();
}
