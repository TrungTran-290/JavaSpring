package com.example.demo;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Department")
public class Department {
    @Id private String id;
    private String nameDepartment;
    private List<User> users; // Changed from User[] to List<User>

    // Constructors
    public Department(String id, String nameDepartment, List<User> users) {
        this.id = id;
        this.nameDepartment = nameDepartment;
        this.users = users;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
