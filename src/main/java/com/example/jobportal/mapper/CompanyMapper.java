package com.example.jobportal.mapper;


import com.example.jobportal.dto.response.CompanyResponse;
import com.example.jobportal.entity.Company;
import org.springframework.stereotype.Component;


@Component
public class CompanyMapper {

    public CompanyResponse toResponse(Company company){

        return new CompanyResponse(

                company.getId(),

                company.getName(),

                company.getDescription(),

                company.getLocation()

        );
    }
}