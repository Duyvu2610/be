package com.gmo.gmo.entities;

import jakarta.persistence.*;
import org.springframework.context.annotation.Primary;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;

    public User(){}

    public User(String name, String password) {
        this.userName = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
