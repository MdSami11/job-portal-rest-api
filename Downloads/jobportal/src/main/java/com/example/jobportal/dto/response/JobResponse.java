package com.example.jobportal.dto.response;


import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobResponse {


    private Long id;

    private String title;

    private String description;

    private double salary;

    private String location;

    private Long companyId;

    private String companyName;


}