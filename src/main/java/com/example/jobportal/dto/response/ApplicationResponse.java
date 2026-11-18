package com.example.jobportal.dto.response;


import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationResponse {


    private Long id;


    private String status;


    private LocalDate appliedDate;


    private Long userId;


    private String userName;


    private Long jobId;


    private String jobTitle;


}