package com.example.crudapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "houses")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "house")
    private List<User> users;


    public House() {
    }

    public House(Long id, String address, List<User> users) {
        this.id = id;
        this.address = address;
        this.users = users;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
