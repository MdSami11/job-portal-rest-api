package com.example.jobportal.dto.response;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDTO {


    private Long id;


    private String status;


    private LocalDate appliedDate;


    private Long userId;


    private Long jobId;


}