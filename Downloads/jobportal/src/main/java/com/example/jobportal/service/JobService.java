package com.example.jobportal.service;


import com.example.jobportal.dto.request.CreateJobRequest;
import com.example.jobportal.dto.request.UpdateJobRequest;
import com.example.jobportal.dto.response.JobResponse;
import com.example.jobportal.entity.Company;
import com.example.jobportal.entity.Job;
import com.example.jobportal.exception.ResourceNotFoundException;
import com.example.jobportal.mapper.JobMapper;
import com.example.jobportal.repository.CompanyRepository;
import com.example.jobportal.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;



@Service
@RequiredArgsConstructor
public class JobService {


    private final JobRepository jobRepository;

    private final CompanyRepository companyRepository;

    private final JobMapper jobMapper;



    // CREATE JOB

    public JobResponse createJob(CreateJobRequest request){


        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Company not found"
                                ));



        Job job = new Job();

        job.setTitle(request.getTitle());

        job.setDescription(request.getDescription());

        job.setSalary(request.getSalary());

        job.setLocation(request.getLocation());

        job.setCompany(company);



        Job savedJob = jobRepository.save(job);

        return jobMapper.toResponse(savedJob);

    }


    // GET ALL WITH PAGINATION

    public Page<JobResponse> getAllJobs(Pageable pageable){


        return jobRepository.findAll(pageable).map(jobMapper::toResponse);

    }


    // SEARCH JOBS

    public List<JobResponse> searchJobs(String keyword){


        return jobRepository
                .findByTitleContainingIgnoreCase(keyword)

                .stream()

                .map(jobMapper::toResponse)

                .toList();

    }


    // GET BY ID

    public JobResponse getJobById(Long id){

        Job job = findJobById(id);

        return jobMapper.toResponse(job);

    }


    // UPDATE JOB

    public JobResponse updateJob(
            Long id,
            UpdateJobRequest request
    ){

        Job job = findJobById(id);


        job.setTitle(request.getTitle());

        job.setDescription(request.getDescription());

        job.setSalary(request.getSalary());

        job.setLocation(request.getLocation());


        Job updated = jobRepository.save(job);

        return jobMapper.toResponse(updated);

    }


    // DELETE JOB

    public void deleteJob(Long id){

        Job job = findJobById(id);

        jobRepository.delete(job);

    }


    private Job findJobById(Long id){


        return jobRepository.findById(id).orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job not found with id: "+id
                        ));

    }

}