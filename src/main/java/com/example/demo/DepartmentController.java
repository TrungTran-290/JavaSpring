package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Department")
public class DepartmentController {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    UserRepository userRepository;
    @GetMapping("")
    public ResponseEntity<List<Department>> departmentResponseEntity(){
        try {
            List<Department> departmentList = departmentRepository.findAll();
            if(departmentList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(departmentList,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Department> add (@RequestBody Department department){
        try{
            Department _department = departmentRepository.save(department);
            return new ResponseEntity<>(_department,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Department> updateDepartmentAndUser(
            @PathVariable("id") String id,
            @RequestBody Department department,
            @RequestBody User user) {

        Optional<Department> optionalDepartment = departmentRepository.findById(id);

        if (optionalDepartment.isPresent()) {
            Department _department = optionalDepartment.get();
            _department.setNameDepartment(department.getNameDepartment());

            List<User> users = _department.getUsers();
            for (User _user : users) {
                if (_user.getId().equals(user.getId())) {
                    _user.setAge(user.getAge());
                    _user.setName(user.getName());
                    _user.setPosition(user.getPosition());
                    _user.setSkills(user.getSkills());
                    break;
                }
            }

            Department updatedDepartment = departmentRepository.save(_department);
            return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
