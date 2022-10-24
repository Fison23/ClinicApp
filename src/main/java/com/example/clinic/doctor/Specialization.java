package com.example.clinic.doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Specialization {
    THERAPYST (100, 20),
    OTOLARYNGOLOGIST (200, 25),
    DERMATOLOGIST (300, 35),
    GASTROENTEROLOGIST (225, 20),
    PEDIATRICIAN (350, 40),
    PSYCHIATRIST (400, 25);

    private int price;
    private int duration;



}
