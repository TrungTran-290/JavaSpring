package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface HomeRepo extends MongoRepository<Student, String> {
//    List<Home> findbyPublished(boolean published);
//    @Query()
}
