package com.example.jobportal.service;


import com.example.jobportal.dto.request.ApplyJobRequest;
import com.example.jobportal.dto.response.ApplicationResponse;
import com.example.jobportal.entity.Job;
import com.example.jobportal.entity.JobApplication;
import com.example.jobportal.entity.User;
import com.example.jobportal.exception.ResourceNotFoundException;
import com.example.jobportal.mapper.ApplicationMapper;
import com.example.jobportal.repository.JobApplicationRepository;
import com.example.jobportal.repository.JobRepository;
import com.example.jobportal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;



@Service
@RequiredArgsConstructor
public class ApplicationService {



    private final JobApplicationRepository applicationRepository;

    private final UserRepository userRepository;

    private final JobRepository jobRepository;

    private final ApplicationMapper applicationMapper;




    // APPLY FOR JOB

    public ApplicationResponse applyForJob(ApplyJobRequest request){

        User user = userRepository.findById(request.getUserId()).orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User not found"
                                ));

        Job job = jobRepository.findById(request.getJobId()).orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Job not found"
                                ));


        JobApplication application = new JobApplication();

        application.setUser(user);

        application.setJob(job);

        application.setStatus("PENDING");

        application.setAppliedDate(LocalDate.now());

        JobApplication saved = applicationRepository.save(application);


        return applicationMapper.toResponse(saved);

    }


    // GET ALL APPLICATIONS

    public List<ApplicationResponse> getAllApplications(){


        return applicationRepository.findAll()

                .stream()

                .map(applicationMapper::toResponse)

                .toList();

    }

    // GET APPLICATION BY USER

    public List<ApplicationResponse> getUserApplications(Long userId){


        return applicationRepository.findByUserId(userId)

                .stream()

                .map(applicationMapper::toResponse)

                .toList();

    }


    // UPDATE APPLICATION STATUS

    public ApplicationResponse updateStatus(
            Long id,
            String status
    ){


        JobApplication application = applicationRepository.findById(id).orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Application not found"
                                ));



        application.setStatus(status);

        JobApplication updated = applicationRepository.save(application);

        return applicationMapper.toResponse(updated);

    }

}