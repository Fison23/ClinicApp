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

    HashMap<Specialization, Integer> doctorPrice = new HashMap<>(Map.of(
            Specialization.DERMATOLOGIST, 100,
            Specialization.THERAPYST, 200,
            Specialization.PSYCHIATRIST, 300,
            Specialization.PEDIATRICIAN, 400,
            Specialization.GASTROENTEROLOGIST, 600,
            Specialization.OTOLARYNGOLOGIST, 500));

    HashMap<Specialization, Integer> visitDuration = new HashMap<>(Map.of(
            Specialization.DERMATOLOGIST, 25,
            Specialization.THERAPYST, 30,
            Specialization.PSYCHIATRIST, 30,
            Specialization.PEDIATRICIAN, 40,
            Specialization.GASTROENTEROLOGIST, 65,
            Specialization.OTOLARYNGOLOGIST, 20));


    public Integer getPrice(Specialization specialization) {
        return doctorPrice.get(specialization).intValue();
    }

    public Specialization getSpec (Integer indexOfSpec) {
        return listOfSpec.get(indexOfSpec);
    }

    public Long getVisitDuration (Specialization specialization) {
        return visitDuration.get(specialization).longValue();

    }
}



