package com.example.jobportal.entity;


import jakarta.persistence.*;
import lombok.*;


import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Job {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;


    private String description;


    private double salary;


    private String location;



    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;



    @OneToMany(
            mappedBy = "job",
            cascade = CascadeType.ALL
    )
    private List<JobApplication> applications;


}