package com.example.clinic.visit;

import com.example.clinic.doctor.Doctor;
import com.example.clinic.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface VisitRepository extends JpaRepository <Visit, String> {

    Optional<Visit> findByTimeOfVisitAndDoctorAndPatient(LocalDateTime timeOfVisit, Doctor doctor, Patient patient);
}
