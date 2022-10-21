package com.example.clinic.doctor;

import com.example.clinic.patient.PatientRestController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final DoctorRestController doctorRestController;
    private final PatientRestController patientRestController;

    @GetMapping("/alldoctors")
    public String showDoctors (Model model) {
        model.addAttribute("doctors", doctorRestController.findDoctors());
        return "doctors";
    }

    @GetMapping("/allpatients")
    public String showPatients (Model model) {
        model.addAttribute("patients", patientRestController.findPatients());
        return "patients";
    }
}
