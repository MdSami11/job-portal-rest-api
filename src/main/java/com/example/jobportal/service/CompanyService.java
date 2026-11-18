package com.example.jobportal.service;


import com.example.jobportal.dto.request.CreateCompanyRequest;
import com.example.jobportal.dto.request.UpdateCompanyRequest;
import com.example.jobportal.dto.response.CompanyResponse;
import com.example.jobportal.entity.Company;
import com.example.jobportal.exception.ResourceNotFoundException;
import com.example.jobportal.mapper.CompanyMapper;
import com.example.jobportal.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class CompanyService {


    private final CompanyRepository companyRepository;

    private final CompanyMapper companyMapper;


    private static final Logger logger =
            LoggerFactory.getLogger(CompanyService.class);



    // CREATE COMPANY (POST)

    public CompanyResponse createCompany(CreateCompanyRequest request) {


        Company company = new Company();


        company.setName(request.getName());

        company.setDescription(request.getDescription());

        company.setLocation(request.getLocation());


        Company savedCompany =
                companyRepository.save(company);


        logger.info("Company created with id {}", savedCompany.getId());


        return companyMapper.toResponse(savedCompany);

    }




    // GET ALL COMPANIES

    public List<CompanyResponse> getAllCompanies() {


        return companyRepository.findAll()

                .stream()

                .map(companyMapper::toResponse)

                .toList();

    }





    // GET COMPANY BY ID

    public CompanyResponse getCompanyById(Long id) {


        Company company = findCompanyById(id);


        return companyMapper.toResponse(company);

    }






    // UPDATE COMPANY (PUT)

    public CompanyResponse updateCompany(
            Long id,
            UpdateCompanyRequest request
    ) {


        Company company =
                findCompanyById(id);



        company.setName(request.getName());

        company.setDescription(request.getDescription());

        company.setLocation(request.getLocation());



        Company updatedCompany =
                companyRepository.save(company);



        logger.info("Company updated with id {}", id);


        return companyMapper.toResponse(updatedCompany);

    }



    // DELETE COMPANY

    public void deleteCompany(Long id) {


        Company company = findCompanyById(id);

        companyRepository.delete(company);

        logger.info("Company deleted with id {}", id);

    }


    // COMMON METHOD

    private Company findCompanyById(Long id) {


        return companyRepository.findById(id).orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Company not found with id: " + id
                        ));

    }


}