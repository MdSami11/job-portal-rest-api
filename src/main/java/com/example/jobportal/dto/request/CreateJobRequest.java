package com.example.jobportal.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobRequest {


    @NotBlank
    private String title;


    @NotBlank
    private String description;


    private double salary;


    @NotBlank
    private String location;


    private Long companyId;


}