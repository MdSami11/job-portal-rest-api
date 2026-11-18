package com.example.jobportal.controller;


import com.example.jobportal.dto.request.CreateJobRequest;
import com.example.jobportal.dto.request.UpdateJobRequest;
import com.example.jobportal.dto.response.JobResponse;
import com.example.jobportal.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {


    private final JobService jobService;



    // CREATE JOB

    @PostMapping
    public ResponseEntity<JobResponse> createJob(@Valid @RequestBody CreateJobRequest request){

        return new ResponseEntity<>(
                jobService.createJob(request),
                HttpStatus.CREATED
        );

    }



    // GET ALL JOBS WITH PAGINATION

    @GetMapping
    public ResponseEntity<Page<JobResponse>> getAllJobs(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(jobService.getAllJobs(
                        PageRequest.of(page,size)
                )

        );

    }


    // SEARCH JOBS

    @GetMapping("/search")
    public ResponseEntity<List<JobResponse>> searchJobs(@RequestParam String keyword){

        return ResponseEntity.ok(jobService.searchJobs(keyword));

    }


    // GET JOB BY ID

    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> getJobById(@PathVariable Long id){

        return ResponseEntity.ok(jobService.getJobById(id));

    }


    // UPDATE JOB

    @PutMapping("/{id}")
    public ResponseEntity<JobResponse> updateJob(@PathVariable Long id,
                                                 @Valid @RequestBody UpdateJobRequest request){

        return ResponseEntity.ok(jobService.updateJob(id,request));

    }


    // DELETE JOB

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id){


        jobService.deleteJob(id);

        return ResponseEntity.noContent().build();

    }


}