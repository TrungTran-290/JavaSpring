package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("subject")
public class Subject {
    @Id public String id;
    public String name;
    public String Anger;
    public int age;

    public Subject(String id, String name, String anger, int age) {
        this.id = id;
        this.name = name;
        Anger = anger;
        this.age = age;
    }

    public Subject() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnger() {
        return Anger;
    }

    public void setAnger(String anger) {
        Anger = anger;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
