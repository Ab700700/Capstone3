package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Company;
import com.example.capstone3.Repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    // 66
    public List<Company> getCompany(){
        return companyRepository.findAll();
    }
    // 67
    public void addCompany(Company company){
        company.setStatus("notactive");
        companyRepository.save(company);
    }
    // 68
    public void updateCompany(Integer id , Company company){
        Company oldCompany = companyRepository.findCompaniesById(id);
        if(oldCompany==null){
            throw new ApiException("company id not found");
        }
        oldCompany.setCompany_name(company.getCompany_name());
        oldCompany.setPassword(company.getPassword());
        oldCompany.setEmail(company.getEmail());
        oldCompany.setPhone_number(company.getPhone_number());
        oldCompany.setPermission(company.getPermission());
        oldCompany.setCommercial_register_num(company.getCommercial_register_num());
        oldCompany.setProfit(company.getProfit());
        companyRepository.save(oldCompany);
    }
    // 69
    public void deleteCompany(Integer id){
        Company company = companyRepository.findCompaniesById(id);
        if(company==null){
            throw new ApiException("company id not found");
        }
        companyRepository.delete(company);
    }
    // 70
    public List<Company> getCompanyByStatus(String status){
        List<Company> companies =companyRepository.findCompaniesByStatus(status);
        if(companies.isEmpty()){
            throw new ApiException("no company with this status");
        }
        return companies;
    }
    //71
    public List<Company> getByCompanyName(String name){
        List<Company> companies =companyRepository.findCompaniesByCompany_name(name);
        if(companies.isEmpty()){
            throw new ApiException("no company with this name");
        }
        return companies;
    }
}
