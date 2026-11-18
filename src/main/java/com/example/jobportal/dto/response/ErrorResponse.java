package com.example.jobportal.dto.response;


import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {


    private int status;


    private String message;


    private LocalDateTime timestamp;


}