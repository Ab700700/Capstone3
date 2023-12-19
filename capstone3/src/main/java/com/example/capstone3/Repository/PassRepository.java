package com.example.capstone3.Repository;

import com.example.capstone3.Model.Pass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PassRepository extends JpaRepository<Pass,Integer> {
    Pass findPassById(Integer id);
    @Query("select p from Pass p where  p.user.id=?1")
    List<Pass> findPassesById(Integer id);

    List<Pass> findPassByStatus(String status);

    List<Pass> findPassByPriceBetween(Double start_price,Double end_price);

    @Query("select p from Pass p where p.start_date<?1 and p.end_date>?1")
    List<Pass> PassByStart_dateAfterAndEnd_dateBefore(Date  date);
}
