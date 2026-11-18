package com.example.jobportal.dto.request;


import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplyJobRequest {


    private Long userId;

    private Long jobId;


}