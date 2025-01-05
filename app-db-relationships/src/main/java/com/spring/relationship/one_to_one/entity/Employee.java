package com.spring.relationship.one_to_one.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String employeeName;
    @Column(unique = true)
    private String email;
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private ParkingSpot parkingSpot;
}
