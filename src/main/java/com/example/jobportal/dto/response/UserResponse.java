package com.example.jobportal.dto.response;


import com.example.jobportal.entity.Role;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {


    private Long id;


    private String name;


    private String email;


    private Role role;


}