package com.example.clinic.specialisation;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class SpecializationRestController {

    private final SpecializationRepository specializationRepository;


    @PostMapping("specialization")
    public ResponseEntity<HttpStatus> createSpecialization(@RequestBody String name) {
        specializationRepository.save(
                Specialization.builder()
                        .name(name)
                        .build()
        );
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("specializations")
    public List<Specialization> getAllSpecializations() {
        return specializationRepository.findAll();
    }


}
