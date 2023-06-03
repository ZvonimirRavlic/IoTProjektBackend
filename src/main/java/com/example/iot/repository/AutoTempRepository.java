package com.example.iot.repository;

import com.example.iot.entiteti.AutoTemp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoTempRepository extends JpaRepository<AutoTemp, Integer> {
    AutoTemp findFirstByDobGreaterThanEqualOrderByDobAsc(Integer dob);


}
