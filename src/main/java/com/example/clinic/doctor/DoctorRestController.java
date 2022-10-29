package com.example.clinic.doctor;

import com.example.clinic.specialisation.Specialization;
import com.example.clinic.specialisation.SpecializationRepository;
import com.example.clinic.visit.Visit;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class DoctorRestController {

    private final DoctorRepository doctorRepository;
    private final SpecializationRepository specializationRepository;

    @PostMapping(path = "/doctor")

    public ResponseEntity<HttpStatus> createDoctor(@RequestBody CreateDoctorRequest createDoctorRequest,
                                                   @RequestParam String specializationId) {

        Specialization specialization = specializationRepository.findById(specializationId).get();
        doctorRepository.save(
                Doctor.builder()
                        .name(createDoctorRequest.getName())
                        .surname(createDoctorRequest.getSurname())
                        .specialization(specialization)
                        .build());
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping(path = "/doctor/{doctorId}")
    public Doctor findDoctor(@PathVariable("doctorId") String id) {
        return doctorRepository.findById(id).orElseThrow();
    }

    @GetMapping(path = "/doctors")
    public List<Doctor> findDoctors() {
        return doctorRepository.findAll();
    }

    @GetMapping(path = "/doctors/{doctorId}/visits")
    public Set<Visit> findDoctors(@PathVariable("doctorId") String id) {
        return doctorRepository.findById(id).orElseThrow().getVisits();
    }

}

