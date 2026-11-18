package com.example.jobportal.dto.response;


import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {

    private Long id;


    private String name;


    private String description;


    private String location;


}