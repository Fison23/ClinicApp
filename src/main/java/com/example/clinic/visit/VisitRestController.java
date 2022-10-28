package com.example.clinic.visit;

import com.example.clinic.doctor.Doctor;
import com.example.clinic.doctor.DoctorCreatingManager;
import com.example.clinic.doctor.DoctorRepository;
import com.example.clinic.patient.Patient;
import com.example.clinic.patient.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class VisitRestController {

    private final VisitRepository visitRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final DoctorCreatingManager doctorCreatingManager;

    @PostMapping("/visit")
    public String createVisit(@RequestParam (name = "doctorid") String doctorId,
                              @RequestParam (name = "patientid") String patientId,
                              @RequestParam (name = "timeofvisit") String timeOfVisit,
                              @RequestBody String doctorConclusion) {

        LocalDateTime datetimeofvisit = LocalDateTime.parse(timeOfVisit, DateTimeFormatter.ISO_DATE_TIME);
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = patientRepository.findById(patientId).orElseThrow();
        LocalDateTime endTimeOfVisit = datetimeofvisit.plusMinutes(doctor.getSpecialization().getDuration());


        checkIsVisitAlreadyExist(doctor, patient, datetimeofvisit);

        Visit newVisit = visitRepository.save(
                        Visit.builder()
                                .isVisit(true)
                                .patient(patient)
                                .timeOfVisit(datetimeofvisit)
                                .price(doctor.getSpecialization().getPrice())
                                .doctorConclusion(doctorConclusion)
                                .endTimeOfVisit(endTimeOfVisit)
                                .duration(doctor.getSpecialization().getDuration())
                                .doctor(doctor)
                                .build());
        return newVisit.getId();

    }

    private void checkIsVisitAlreadyExist(Doctor doctor, Patient patient, LocalDateTime visitTime) {
        visitRepository.findByTimeOfVisitAndDoctorAndPatient(visitTime,
                        doctor,
                        patient)
                .ifPresent((visit) -> {
                    throw new VisitException("Visit with id: " + visit.getId() + " already exist");
                });
    }

    @GetMapping(path = "/visits")
    private List<Visit> showAllvisits () {
        return visitRepository.findAll();

    }

    @DeleteMapping(path = "/visit/{id}")
    public void cancelVisit (@PathVariable ("id") String visitId) {
        visitRepository.deleteById(visitId);
    }

}
