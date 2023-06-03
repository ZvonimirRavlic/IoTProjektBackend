package com.example.iot.repository;

import com.example.iot.entiteti.MinMaxDob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MinMaxDobRepository extends JpaRepository<MinMaxDob, Integer> {
}
