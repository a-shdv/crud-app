package com.example.crudapp.service;

import com.example.crudapp.dto.HouseDto;
import com.example.crudapp.exception.HouseAlreadyExistsException;
import com.example.crudapp.exception.HouseNotFoundException;
import com.example.crudapp.exception.HousesListIsEmptyException;
import com.example.crudapp.model.House;

import java.util.List;

public interface HouseService {
    void createHouse(House house) throws HouseAlreadyExistsException;

    List<HouseDto> getHouses() throws HousesListIsEmptyException;

    HouseDto getHouseById(Long id) throws HouseNotFoundException;

    House getHouseByAddress(String address) throws HouseNotFoundException;

    HouseDto updateHouseById(Long id, House reqHouse) throws HouseNotFoundException;

    Long deleteHouseById(Long id) throws HouseNotFoundException;
}
