package com.example.capstone3.Repository;

import com.example.capstone3.Model.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessRepository extends JpaRepository<Business,Integer> {
    Business findBusinessById(Integer id);
    Business findBusinessByCompanyName(String companyname);

    List<Business> findBusinessesByStatus(String status);


}
