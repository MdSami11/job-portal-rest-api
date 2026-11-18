package com.example.jobportal.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCompanyRequest {


    @NotBlank
    private String name;


    @NotBlank
    private String description;


    @NotBlank
    private String location;

}