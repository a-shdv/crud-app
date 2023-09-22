package com.example.crudapp.dto;

import com.example.crudapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class AdminDto {
    private Long id;
    private String username;
    private int age;

    public AdminDto() {

    }

    public AdminDto(Long id, String username, int age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    public static AdminDto toUserDto(User user) {
        AdminDto dto = new AdminDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setAge(user.getAge());
        return dto;
    }

    public static List<AdminDto> toUsersDto(List<User> users) {
        List<AdminDto> dtos = new ArrayList<>();
        for (User user : users) {
            dtos.add(new AdminDto(user.getId(), user.getUsername(), user.getAge()));
        }
        return dtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
