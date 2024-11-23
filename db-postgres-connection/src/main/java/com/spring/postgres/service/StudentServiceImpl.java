package com.spring.postgres.service;

import com.spring.postgres.dto.StudentRequest;
import com.spring.postgres.dto.StudentResponse;
import com.spring.postgres.model.Student;
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
        Student student = studentRepository.save(StudentRequest.toStudent(studentRequest));
        return StudentResponse.toStudentResponse(student);
    }

    @Override
    public List<StudentResponse> findAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream().map(
                StudentResponse::toStudentResponse
        ).toList();
    }

    @Override
    public StudentResponse findStudentById(Integer studentId) {
        Student student = studentRepository.getReferenceById(studentId);
        return StudentResponse.toStudentResponse(student);
    }

    @Override
    public StudentResponse updateStudentById(Integer studentId, StudentRequest studentRequest) {
        Student student = studentRepository.getReferenceById(studentId);
        student.setStudentName(studentRequest.getName());
        student.setEmail(studentRequest.getEmail());
        student.setAge(studentRequest.getAge());
        Student updatedStudent = studentRepository.save(student);
        return StudentResponse.toStudentResponse(updatedStudent);
    }

    @Override
    public void deleteStudentById(Integer studentId) {

    }
}
