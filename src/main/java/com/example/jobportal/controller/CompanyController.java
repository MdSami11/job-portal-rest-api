package com.example.jobportal.controller;


import com.example.jobportal.dto.request.CreateCompanyRequest;
import com.example.jobportal.dto.request.UpdateCompanyRequest;
import com.example.jobportal.dto.response.CompanyResponse;
import com.example.jobportal.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {



    private final CompanyService companyService;



    // CREATE COMPANY

    @PostMapping
    public ResponseEntity<CompanyResponse> createCompany(@Valid @RequestBody CreateCompanyRequest request){

        return new ResponseEntity<>(companyService.createCompany(request),
                HttpStatus.CREATED
        );

    }


    // GET ALL COMPANIES

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAllCompanies(){

        return ResponseEntity.ok(
                companyService.getAllCompanies()
        );

    }


    // GET COMPANY BY ID

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable Long id){

        return ResponseEntity.ok(
                companyService.getCompanyById(id)
        );

    }


    // UPDATE COMPANY

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(@PathVariable Long id,
                                                         @Valid @RequestBody UpdateCompanyRequest request){

        return ResponseEntity.ok(companyService.updateCompany(id, request));

    }


    // DELETE COMPANY

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id){

        companyService.deleteCompany(id);

        return ResponseEntity.noContent().build();

    }
}