package com.example.capstone3.Repository;

import com.example.capstone3.Model.Pass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassRepository extends JpaRepository<Pass,Integer> {
    Pass findPassById(Integer id);
}
