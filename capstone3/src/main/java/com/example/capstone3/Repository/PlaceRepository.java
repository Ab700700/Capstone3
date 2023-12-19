package com.example.capstone3.Repository;

import com.example.capstone3.Model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place,Integer> {
 Place findPlaceById(Integer id);

 List<Place> findPlacesByCompanyName(String name);

 List<Place> findPlacesByCategoryStartsWith(String word);
}
