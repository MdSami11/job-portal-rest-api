package com.example.jobportal.service;


import com.example.jobportal.dto.request.CreateJobRequest;
import com.example.jobportal.dto.response.JobResponse;
import com.example.jobportal.entity.Company;
import com.example.jobportal.entity.Job;
import com.example.jobportal.exception.ResourceNotFoundException;
import com.example.jobportal.mapper.JobMapper;
import com.example.jobportal.repository.CompanyRepository;
import com.example.jobportal.repository.JobRepository;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class JobServiceTest {



    @Mock
    private JobRepository jobRepository;



    @Mock
    private CompanyRepository companyRepository;



    @Mock
    private JobMapper jobMapper;



    @InjectMocks
    private JobService jobService;




    @Test
    void createJob_shouldReturnJobResponse(){


        CreateJobRequest request = new CreateJobRequest(
                        "Java Developer",
                        "Spring Boot Developer",
                        80000,
                        "Bangalore",
                        1L
                );



        Company company = new Company();
        company.setId(1L);
        company.setName("Google");



        Job job = new Job();


        job.setId(1L);

        job.setTitle("Java Developer");

        job.setCompany(company);



        JobResponse response = new JobResponse(
                        1L,
                        "Java Developer",
                        "Spring Boot Developer",
                        80000,
                        "Bangalore",
                        1L,
                        "Google"
                );



        when(companyRepository.findById(1L))
                .thenReturn(
                        Optional.of(company)
                );



        when(jobRepository.save(any(Job.class)))
                .thenReturn(job);



        when(jobMapper.toResponse(job))
                .thenReturn(response);




        JobResponse result =
                jobService.createJob(request);




        assertEquals(
                "Java Developer",
                result.getTitle()
        );



        verify(companyRepository)
                .findById(1L);



        verify(jobRepository)
                .save(any(Job.class));

    }




    @Test
    void getJobById_shouldReturnJob(){


        Job job = new Job();
        job.setId(1L);
        job.setTitle("Backend Developer");



        JobResponse response = new JobResponse(
                        1L,
                        "Backend Developer",
                        "Spring Boot",
                        70000,
                        "Chennai",
                        1L,
                        "Google"
                );



        when(jobRepository.findById(1L))
                .thenReturn(
                        Optional.of(job)
                );



        when(jobMapper.toResponse(job))
                .thenReturn(response);




        JobResponse result =
                jobService.getJobById(1L);




        assertEquals(
                "Backend Developer",
                result.getTitle()
        );


        verify(jobRepository)
                .findById(1L);


    }





    @Test
    void getJobById_shouldThrowExceptionWhenNotFound(){



        when(jobRepository.findById(99L))
                .thenReturn(
                        Optional.empty()
                );



        assertThrows(ResourceNotFoundException.class,

                () -> jobService.getJobById(99L)

        );



        verify(jobRepository)
                .findById(99L);


    }




    @Test
    void deleteJob_shouldDeleteJob(){


        Job job = new Job();
        job.setId(1L);



        when(jobRepository.findById(1L))
                .thenReturn(
                        Optional.of(job)
                );


        jobService.deleteJob(1L);


        verify(jobRepository)
                .delete(job);


    }


}