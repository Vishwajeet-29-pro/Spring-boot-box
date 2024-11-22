package com.spring.postgres.service;

import com.spring.postgres.dto.StudentRequest;
import com.spring.postgres.dto.StudentResponse;

import java.util.List;

public interface StudentService {
    StudentResponse saveStudent(StudentRequest studentRequest);
    List<StudentResponse> findAllStudents();
    StudentResponse findStudentById(Integer studentId);
    StudentResponse updateStudentById(Integer studentId, StudentRequest studentRequest);
    void deleteStudentById(Integer studentId);
}
