package com.example.crudapp.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column
    private String username;

    @Column
    private String password;

    @Column
    private int age;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"))
    @ElementCollection(targetClass = UserRoles.class, fetch = FetchType.EAGER)
    private Set<UserRoles> userRoles = new HashSet<>();

    public User() {
    }

    public User(String username, String password, int age, House house) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.house = house;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
