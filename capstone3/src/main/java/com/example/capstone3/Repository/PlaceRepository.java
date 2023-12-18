package com.example.capstone3.Repository;

import com.example.capstone3.Model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place,Integer> {
 Place findPlaceById(Integer id);
}
