package com.example.clinic.patient;

import lombok.Data;

@Data
public class CreatePatientRequest {

    private String name;
    private String surname;
    private Integer age;

}
