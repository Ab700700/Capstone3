package com.example.capstone3.Repository;

import com.example.capstone3.Model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.invoke.StringConcatException;
import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {
    Company findCompaniesById(Integer id);
    List<Company> findCompaniesByStatus(String status);
    @Query("select c from Company c where c.company_name=?1")
    List<Company> findCompaniesByCompany_name(String company_name);

    Company findCompaniesByIdAndStatus(Integer id,String status);
}
