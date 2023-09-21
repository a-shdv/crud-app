package com.example.crudapp.service.impl;

import com.example.crudapp.dto.HouseDto;
import com.example.crudapp.exception.HouseAlreadyExistsException;
import com.example.crudapp.exception.HouseNotFoundException;
import com.example.crudapp.exception.HousesListIsEmptyException;
import com.example.crudapp.model.House;
import com.example.crudapp.repository.HouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService implements com.example.crudapp.service.HouseService {
    private final HouseRepo houseRepo;

    @Autowired
    public HouseService(HouseRepo houseRepo) {
        this.houseRepo = houseRepo;
    }

    @Override
    public void createHouse(House house) throws HouseAlreadyExistsException {
        if (houseRepo.findHouseByAddress(house.getAddress()) != null) {
            throw new HouseAlreadyExistsException("Дом по адресу " + house.getAddress() + " уже существует!");
        }
        houseRepo.save(house);
    }

    @Override
    public List<HouseDto> getHouses() throws HousesListIsEmptyException {
        List<House> houses = houseRepo.findAll();
        if (houses.isEmpty()) {
            throw new HousesListIsEmptyException("Список домов пуст!");
        }
        return HouseDto.toHousesDto(houses);
    }

    @Override
    public HouseDto getHouseById(Long id) throws HouseNotFoundException {
        House house = houseRepo.findById(id).get();
        if (house.getId() == null) {
            throw new HouseNotFoundException("Дом не найден!");
        }
        return HouseDto.toHouseDto(house);
    }

    @Override
    public HouseDto updateHouseById(Long id, House reqHouse) throws HouseNotFoundException {
        House dbHouse = houseRepo.findById(id).get();
        if (dbHouse.getId() == null) {
            throw new HouseNotFoundException("Дом не найден!");
        }
        dbHouse.setAddress(reqHouse.getAddress());
        houseRepo.save(dbHouse);
        return HouseDto.toHouseDto(dbHouse);
    }

    @Override
    public Long deleteHouseById(Long id) throws HouseNotFoundException {
        House house = houseRepo.findById(id).get();
        if (house.getId() == null) {
            throw new HouseNotFoundException("Дом не найден!");
        }
        houseRepo.deleteById(id);
        return id;
    }
}
