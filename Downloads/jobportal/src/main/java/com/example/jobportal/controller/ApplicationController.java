package com.example.jobportal.controller;


import com.example.jobportal.dto.request.ApplyJobRequest;
import com.example.jobportal.dto.response.ApplicationResponse;
import com.example.jobportal.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationController {


    private final ApplicationService applicationService;


    // APPLY FOR JOB

    @PostMapping
    public ResponseEntity<ApplicationResponse> applyJob(@RequestBody ApplyJobRequest request){


        return new ResponseEntity<>(applicationService.applyForJob(request),

                HttpStatus.CREATED

        );

    }



    // GET ALL APPLICATIONS

    @GetMapping
    public ResponseEntity<List<ApplicationResponse>> getAllApplications(){


        return ResponseEntity.ok(applicationService.getAllApplications());

    }



    // GET USER APPLICATIONS

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ApplicationResponse>> getUserApplications(@PathVariable Long userId){


        return ResponseEntity.ok(applicationService.getUserApplications(userId)

        );

    }


    // UPDATE APPLICATION STATUS

    @PutMapping("/{id}/status")
    public ResponseEntity<ApplicationResponse> updateStatus(@PathVariable Long id,
                                                            @RequestParam String status){


        return ResponseEntity.ok(applicationService.updateStatus(id,status));

    }

}