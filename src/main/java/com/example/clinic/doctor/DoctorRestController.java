package com.example.clinic.doctor;

import com.example.clinic.visit.Visit;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class DoctorRestController {

    private final DoctorRepository doctorRepository;
    private final DoctorCreatingManager doctorCreatingManager;

    @PostMapping(path = "/doctor")

    public ResponseEntity<HttpStatus> createDoctor(@RequestBody CreateDoctorRequest createDoctorRequest,
                                                   @RequestParam Integer numberOfSpec) {
        Specialization specialization = doctorCreatingManager.getSpec(numberOfSpec);
        doctorRepository.save(
                Doctor.builder()
                        .name(createDoctorRequest.getName())
                        .surname(createDoctorRequest.getSurname())
                        .specialization(specialization)
                        .build());
                return ResponseEntity.ok(HttpStatus.OK);
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

    @GetMapping("doctors/{specialisation}")
    public List<Doctor> getDoctorsBySpec(@PathVariable(name = "specialisation") Specialization specialisation) {
        return doctorRepository
                .findAll()
                .stream()
                .filter(doctor -> doctor.getSpecialization() == specialisation)
                .collect(Collectors.toList());
    }

    @GetMapping ("specializations")
    public List <Specialization> getAllSpecializations () {
        return List.of(Specialization.values());
    }
}

