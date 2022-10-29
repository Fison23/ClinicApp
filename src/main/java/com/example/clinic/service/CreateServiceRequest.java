package com.example.clinic.service;

import lombok.Data;

@Data
public class CreateServiceRequest {

    private String name;

    private Integer price;

    private Integer durationInMinutes;

}
