package com.example.jobportal.service;


import com.example.jobportal.dto.request.CreateCompanyRequest;
import com.example.jobportal.dto.response.CompanyResponse;
import com.example.jobportal.entity.Company;
import com.example.jobportal.exception.ResourceNotFoundException;
import com.example.jobportal.mapper.CompanyMapper;
import com.example.jobportal.repository.CompanyRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {


    @Mock
    private CompanyRepository companyRepository;


    @Mock
    private CompanyMapper companyMapper;


    @InjectMocks
    private CompanyService companyService;



    @Test
    void createCompany_shouldReturnCompanyResponse(){


        CreateCompanyRequest request = new CreateCompanyRequest(
                        "Google",
                        "Tech Company",
                        "Bangalore");


        Company company = new Company();
        company.setId(1L);
        company.setName("Google");


        CompanyResponse response = new CompanyResponse(
                        1L,
                        "Google",
                        "Tech Company",
                        "Bangalore");



        when(companyRepository.save(any(Company.class))).thenReturn(company);

        when(companyMapper.toResponse(company)).thenReturn(response);


        CompanyResponse result = companyService.createCompany(request);


        assertEquals("Google",
                result.getName()
        );



        verify(companyRepository).save(any(Company.class));

    }


    @Test
    void getCompanyById_shouldReturnCompany(){


        Company company = new Company();
        company.setId(1L);
        company.setName("Google");



        CompanyResponse response = new CompanyResponse(
                        1L,
                        "Google",
                        "Tech Company",
                        "Bangalore");



        when(companyRepository.findById(1L))
                .thenReturn(
                        java.util.Optional.of(company));



        when(companyMapper.toResponse(company))
                .thenReturn(response);


        CompanyResponse result = companyService.getCompanyById(1L);


        assertEquals(
                "Google",
                result.getName());



        verify(companyRepository)
                .findById(1L);

    }


    @Test
    void getCompanyById_shouldThrowExceptionWhenNotFound(){


        when(companyRepository.findById(99L)).thenReturn(
                        java.util.Optional.empty());



        assertThrows(

                ResourceNotFoundException.class, () -> companyService.getCompanyById(99L));



        verify(companyRepository)
                .findById(99L);

    }

}