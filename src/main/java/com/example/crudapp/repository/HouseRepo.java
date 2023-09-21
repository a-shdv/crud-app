package com.example.crudapp.repository;

import com.example.crudapp.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepo extends JpaRepository<House, Long> {
    House findHouseByAddress(String address);
}
