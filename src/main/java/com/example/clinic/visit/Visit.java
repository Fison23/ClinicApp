package com.example.clinic.visit;

import com.example.clinic.doctor.Doctor;
import com.example.clinic.patient.Patient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visit")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Visit {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private String id;

    @Column(name = "is_visit", nullable = false)
    private boolean isVisit;

    @Column(name = "time_of_visit", nullable = false)
    private LocalDateTime timeOfVisit;


    @Column (name = "doctor_conclusion")
    private String doctorConclusion;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;


}