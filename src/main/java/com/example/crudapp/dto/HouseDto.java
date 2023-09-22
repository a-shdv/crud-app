package com.example.crudapp.dto;

import com.example.crudapp.model.House;

import java.util.ArrayList;
import java.util.List;

public class HouseDto {
    private Long id;
    private String address;


    public HouseDto() {
    }

    public HouseDto(Long id, String address) {
        this.id = id;
        this.address = address;
    }

    public static HouseDto toHouseDto(House house) {
        HouseDto dto = new HouseDto();
        dto.setId(house.getId());
        dto.setAddress(house.getAddress());
        return dto;
    }

    public static List<HouseDto> toHousesDto(List<House> houses) {
        List<HouseDto> dtos = new ArrayList<>();
        for (House house : houses) {
            dtos.add(new HouseDto(house.getId(), house.getAddress()));
        }
        return dtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
