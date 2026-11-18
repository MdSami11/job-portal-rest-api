package com.example.jobportal.mapper;


import com.example.jobportal.dto.response.UserResponse;
import com.example.jobportal.entity.User;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {


    public UserResponse toDTO(User user){

        return new UserResponse(

                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()

        );

    }

}