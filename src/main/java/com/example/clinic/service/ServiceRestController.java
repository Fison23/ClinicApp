package com.example.clinic.service;

import com.example.clinic.specialisation.Specialization;
import com.example.clinic.specialisation.SpecializationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ServiceRestController {

    private final ServiceRepository serviceRepository;
    private final SpecializationRepository specializationRepository;

    @PostMapping("service")
    public ResponseEntity<HttpStatus> addService(@RequestParam String specializationid, @RequestBody CreateServiceRequest createServiceRequest) {

        Specialization serviceSpecialization = specializationRepository.findById(specializationid).orElseThrow();

        String serviceName = createServiceRequest.getName();
        Integer servicePrice = createServiceRequest.getPrice();
        Integer serviceDuration = createServiceRequest.getDurationInMinutes();

        serviceRepository.save(
                Service.builder()
                        .name(serviceName)
                        .price(servicePrice)
                        .durationInMinutes(serviceDuration)
                        .specialization(serviceSpecialization)
                        .build()
        );
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("services")
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }
}
