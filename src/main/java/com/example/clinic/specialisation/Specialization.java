package com.example.clinic.specialisation;

import com.example.clinic.doctor.Doctor;
import com.example.clinic.service.Service;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "specialization")
@Builder
public class Specialization {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "specialization", fetch = FetchType.EAGER)
    private Set<Doctor> doctors;

    @OneToMany(mappedBy = "specialization", fetch = FetchType.EAGER)
    private Set<Service> services;


}
