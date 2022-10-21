package com.example.clinic.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PatientRestController {

    private final PatientRepository patientRepository;

    @PostMapping(path = "/patient")
    public String createPatient (@RequestBody CreatePatientRequest createPatientRequest) {
        return patientRepository.save(Patient
                .builder()
                .name(createPatientRequest.getName())
                .surname(createPatientRequest.getSurname())
                .age(createPatientRequest.getAge())
                .build()
        ).getId();

    }

    @GetMapping(path = "/patient/{patientID}")
    public Patient findPatient(@PathVariable("patientID") String id) {
        return patientRepository.findById(id).orElseThrow();
    }

    @GetMapping(path = "/patients")
    public List<Patient> findPatients() {
        return patientRepository.findAll();
    }

    @GetMapping(path = "/patientsbyage")
    public List<Patient> findPatientByage(@RequestParam Integer minAge, @RequestParam Integer maxAge) {
        return patientRepository.findAll()
                .stream()
                .filter(el -> el.getAge() > minAge && el.getAge() < maxAge)
                .collect(Collectors.toList());
    }

}
