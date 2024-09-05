package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectRepository subjectRepository;

    // Retrieve subject by ID
    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable("id") Long id) {
        Optional<Subject> subjectData = subjectRepository.findById(String.valueOf(id));
        if (subjectData.isPresent()) {
            return new ResponseEntity<>(subjectData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new subject
    @PostMapping("/create")
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        try {
            Subject _subject = subjectRepository.save(subject);
            return new ResponseEntity<>(_subject, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an existing subject
    @PutMapping("/update/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable("id") Long id, @RequestBody Subject subject) {
        Optional<Subject> subjectData = subjectRepository.findById(String.valueOf(id));
        if (subjectData.isPresent()) {
            Subject _subject = subjectData.get();
            _subject.setName(subject.getName()); // Assuming the subject has a 'name' field
            // Add other fields as necessary
            return new ResponseEntity<>(subjectRepository.save(_subject), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a subject by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteSubject(@PathVariable("id") Long id) {
        try {
            subjectRepository.deleteById(String.valueOf(id));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
