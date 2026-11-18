package com.example.jobportal.mapper;


import com.example.jobportal.dto.response.ApplicationResponse;
import com.example.jobportal.entity.JobApplication;
import org.springframework.stereotype.Component;


@Component
public class ApplicationMapper {


    public ApplicationResponse toResponse(JobApplication application){


        return new ApplicationResponse(

                application.getId(),

                application.getStatus(),

                application.getAppliedDate(),

                application.getUser().getId(),

                application.getUser().getName(),

                application.getJob().getId(),

                application.getJob().getTitle()

        );

    }

}