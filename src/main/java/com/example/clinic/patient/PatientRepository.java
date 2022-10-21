package com.example.clinic.patient;

import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository <Patient, String> {
}
