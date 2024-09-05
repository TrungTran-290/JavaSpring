package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public ResponseEntity<List<User>> getUser() {
        try {
            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    ;
//    @GetMapping("")
//    public ResponseEntity<User> get1User(@PathVariable("id") String id) {
//        Optional<User> getuser = userRepository.findById(String.valueOf(id));
//        if (getuser.isPresent()) {
//            return new ResponseEntity<>(getuser.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @PostMapping("/create")
    public ResponseEntity<User> add1User(@RequestBody User user){
        try{
            User _user = userRepository.save(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<User> update1User(@PathVariable("id") String id, @RequestBody User user){
        Optional<User> updateuser = userRepository.findById(id);
        if(updateuser.isPresent()){
            User _user = updateuser.get();
            _user.setAge(user.getAge());
            _user.setName(user.getName());
            _user.setPosition(user.getPosition());
            _user.setSkills(user.getSkills());
            return new ResponseEntity<>(userRepository.save(_user),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> delete1User(@PathVariable("id") String id, @RequestBody User user){
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
