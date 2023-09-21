package com.example.crudapp.controller;

import com.example.crudapp.exception.HouseAlreadyExistsException;
import com.example.crudapp.exception.HouseNotFoundException;
import com.example.crudapp.exception.HousesListIsEmptyException;
import com.example.crudapp.model.House;
import com.example.crudapp.service.impl.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/houses")
public class HouseController {
    private final HouseService houseService;

    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PostMapping
    public ResponseEntity<?> createHouse(@RequestBody House house) {
        try {
            houseService.createHouse(house);
            return ResponseEntity.ok("Успех!");
        } catch (HouseAlreadyExistsException houseAlreadyExistsException) {
            return ResponseEntity.badRequest().body(houseAlreadyExistsException.toString());
        }
    }

    @GetMapping
    public ResponseEntity<?> getHouses() {
        try {
            return ResponseEntity.ok(houseService.getHouses());
        } catch (HousesListIsEmptyException housesListIsEmptyException) {
            return ResponseEntity.badRequest().body(housesListIsEmptyException.toString());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getHouseById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(houseService.getHouseById(id));
        } catch (HouseNotFoundException houseNotFoundException) {
            return ResponseEntity.badRequest().body(houseNotFoundException.toString());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateHouseById(@PathVariable(name = "id") Long id,
                                             @RequestBody House reqHouse) {
        try {
            return ResponseEntity.ok(houseService.updateHouseById(id, reqHouse));
        } catch (HouseNotFoundException houseNotFoundException) {
            return ResponseEntity.badRequest().body(houseNotFoundException.toString());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHouseById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(houseService.deleteHouseById(id));
        } catch (HouseNotFoundException houseNotFoundException) {
            return ResponseEntity.badRequest().body(houseNotFoundException.toString());
        }
    }
}
