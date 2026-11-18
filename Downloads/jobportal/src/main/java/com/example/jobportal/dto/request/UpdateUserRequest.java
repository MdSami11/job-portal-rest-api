package com.example.jobportal.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {


    @NotBlank
    private String name;


    @Email
    @NotBlank
    private String email;


}