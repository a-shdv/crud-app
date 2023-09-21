package com.example.crudapp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "houses")
public class House extends BaseEntity {

    @Column
    private String address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "house")
    private List<User> users;

    public House() {
    }

    public House(String address, List<User> users) {
        this.address = address;
        this.users = users;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
