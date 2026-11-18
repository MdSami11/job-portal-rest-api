package com.example.jobportal.mapper;


import com.example.jobportal.dto.response.JobResponse;
import com.example.jobportal.entity.Job;
import org.springframework.stereotype.Component;


@Component
public class JobMapper {



    public JobResponse toResponse(Job job){


        return new JobResponse(

                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getSalary(),
                job.getLocation(),
                job.getCompany().getId(),
                job.getCompany().getName()

        );

    }

}