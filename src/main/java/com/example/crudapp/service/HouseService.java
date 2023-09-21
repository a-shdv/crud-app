package com.example.crudapp.service;

import com.example.crudapp.dto.HouseDto;
import com.example.crudapp.exception.HouseAlreadyExistsException;
import com.example.crudapp.exception.HouseNotFoundException;
import com.example.crudapp.exception.HousesListIsEmptyException;
import com.example.crudapp.exception.UserNotFoundException;
import com.example.crudapp.model.House;
import com.example.crudapp.model.User;
import com.example.crudapp.repository.HouseRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService {
    private final HouseRepo houseRepo;

    public HouseService(HouseRepo houseRepo) {
        this.houseRepo = houseRepo;
    }

    public void createHouse(House house) throws HouseAlreadyExistsException {
        if (houseRepo.findHouseByAddress(house.getAddress()) != null) {
            throw new HouseAlreadyExistsException("Дом по адресу " + house.getAddress() + " уже существует!");
        }
        houseRepo.save(house);
    }

    public List<HouseDto> getHouses() throws HousesListIsEmptyException {
        List<House> houses = houseRepo.findAll();
        if (houses.isEmpty()) {
            throw new HousesListIsEmptyException("Список домов пуст!");
        }
        return HouseDto.toHousesDto(houses);
    }

    public HouseDto getHouseById(Long id) throws HouseNotFoundException {
        House house = houseRepo.findById(id).get();
        if (house.getId() == null) {
            throw new HouseNotFoundException("Дом не найден!");
        }
        return HouseDto.toHouseDto(house);
    }

    public HouseDto updateHouseById(Long id, House reqHouse) throws HouseNotFoundException {
        House dbHouse = houseRepo.findById(id).get();
        if (dbHouse.getId() == null) {
            throw new HouseNotFoundException("Дом не найден!");
        }
        dbHouse.setAddress(reqHouse.getAddress());
        houseRepo.save(dbHouse);
        return HouseDto.toHouseDto(dbHouse);
    }

    public Long deleteHouseById(Long id) throws HouseNotFoundException {
        House house = houseRepo.findById(id).get();
        if (house.getId() == null) {
            throw new HouseNotFoundException("Дом не найден!");
        }
        houseRepo.deleteById(id);
        return id;
    }
}
