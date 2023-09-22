package com.example.crudapp.controller;


import com.example.crudapp.dto.AdminAddTenantToHouseDto;
import com.example.crudapp.exception.HouseNotFoundException;
import com.example.crudapp.exception.UserNotFoundException;
import com.example.crudapp.model.House;
import com.example.crudapp.model.User;
import com.example.crudapp.service.HouseService;
import com.example.crudapp.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;
    private final HouseService houseService;

    @Autowired
    public AdminController(UserService userService, HouseService houseService) {
        this.userService = userService;
        this.houseService = houseService;
    }

    @PostMapping
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<?> addTenant(@RequestBody AdminAddTenantToHouseDto dto) throws UserNotFoundException, HouseNotFoundException {
        String username = userService.getUserById(dto.getUserId()).getUsername();
        String address = houseService.getHouseById(dto.getHouseId()).getAddress();

        House house = houseService.getHouseByAddress(address);
        User user = userService.findUserByUsername(username);

        user.setHouse(house);
        house.setUser(user);

        userService.updateUserById(user.getId(), user);
        houseService.updateHouseById(house.getId(), house);

        return ResponseEntity.ok().body("Ok");
    }
}
