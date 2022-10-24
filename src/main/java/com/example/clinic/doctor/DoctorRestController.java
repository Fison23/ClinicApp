package com.example.clinic.doctor;

import com.example.clinic.visit.Visit;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class DoctorRestController{

    private final DoctorRepository doctorRepository;
    private final DoctorCreatingManager doctorCreatingManager;

    @PostMapping(path = "/doctor")

    public String createDoctor(@RequestBody CreateDoctorRequest createDoctorRequest,
                               @RequestParam Integer numberOfSpec) {
        Specialization specialization = doctorCreatingManager.getSpec(numberOfSpec);
        return doctorRepository.save(
                Doctor.builder()
                        .name(createDoctorRequest.getName())
                        .surname(createDoctorRequest.getSurname())
                        .specialization(specialization)
                        .build()
        ).getId();
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