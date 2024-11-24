package com.spring.postgres.controller;

import com.spring.postgres.dto.StudentRequest;
import com.spring.postgres.dto.StudentResponse;
import com.spring.postgres.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponse> saveStudent(@RequestBody StudentRequest studentRequest) {
        return new ResponseEntity<>(studentService.saveStudent(studentRequest), HttpStatus.CREATED);
    }
}
