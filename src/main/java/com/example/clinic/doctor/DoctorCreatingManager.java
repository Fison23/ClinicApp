package com.example.clinic.doctor;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DoctorCreatingManager {

    List <Specialization> listOfSpec = new ArrayList<>(Arrays.asList(
            Specialization.THERAPYST,
            Specialization.PEDIATRICIAN,
            Specialization.PSYCHIATRIST,
            Specialization.OTOLARYNGOLOGIST,
            Specialization.DERMATOLOGIST,
            Specialization.GASTROENTEROLOGIST
    ));

    public Specialization getSpec (Integer indexOfSpec) {
        return listOfSpec.get(indexOfSpec);
    }

}



