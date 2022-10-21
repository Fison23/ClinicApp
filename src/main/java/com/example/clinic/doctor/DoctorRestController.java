package com.example.clinic.doctor;

import com.example.clinic.visit.Visit;
import com.example.clinic.visit.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class DoctorRestController {

    private final DoctorRepository doctorRepository;
    private final VisitRepository visitRepository;

    @PostMapping(path = "/doctor")
    public String createDoctor(@RequestBody CreateDoctorRequest createDoctorRequest) {
        return doctorRepository.save(
                Doctor.builder()
                        .name(createDoctorRequest.getName())
                        .surname(createDoctorRequest.getSurname())
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

    @GetMapping(path = "/visits")
    private List<Visit> showAllvisits () {
        return visitRepository.findAll();

    }

}