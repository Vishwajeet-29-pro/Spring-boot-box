package com.spring.postgres.service;

import com.spring.postgres.dto.StudentRequest;
import com.spring.postgres.dto.StudentResponse;
import com.spring.postgres.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public StudentResponse saveStudent(StudentRequest studentRequest) {
        return null;
    }

    @Override
    public List<StudentResponse> findAllStudents() {
        return List.of();
    }

    @Override
    public StudentResponse findStudentById(Integer studentId) {
        return null;
    }

    @Override
    public StudentResponse updateStudentById(Integer studentId, StudentRequest studentRequest) {
        return null;
    }

    @Override
    public void deleteStudentById(Integer studentId) {

    }
}
