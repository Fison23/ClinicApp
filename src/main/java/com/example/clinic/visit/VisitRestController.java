package com.example.clinic.visit;

import com.example.clinic.doctor.Doctor;
import com.example.clinic.doctor.DoctorRepository;
import com.example.clinic.patient.Patient;
import com.example.clinic.patient.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
public class VisitRestController {

    private final VisitRepository visitRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @PostMapping("/visit")
    public String createVisit(
            @RequestParam("doctorId") String doctorId,
            @RequestParam("patientId") String patientId,
            @RequestParam("visitTime") String visitTime) {

        LocalDateTime visitTimeAndDate = LocalDateTime.parse(visitTime, DateTimeFormatter.ISO_DATE_TIME);
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        checkIsVisitAlreadyExist(doctor, patient, visitTimeAndDate);

        return visitRepository.save(
                        Visit.builder()
                                .isVisit(false)
                                .timeOfVisit(visitTimeAndDate)
                                .patient(patient)
                                .doctor(doctor)
                                .build())
                .getId();
    }

    private void checkIsVisitAlreadyExist(Doctor doctor, Patient patient, LocalDateTime visitTime) {
        visitRepository.findByTimeOfVisitAndDoctorAndPatient(visitTime,
                        doctor,
                        patient)
                .ifPresent((visit) -> {
                    throw new VisitException("Visit with id: " + visit.getId() + " already exist");
                });
    }

}
