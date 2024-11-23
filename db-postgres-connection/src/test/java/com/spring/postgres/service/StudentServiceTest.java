package com.spring.postgres.service;

import com.spring.postgres.dto.StudentRequest;
import com.spring.postgres.dto.StudentResponse;
import com.spring.postgres.model.Student;
import com.spring.postgres.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;
    private Student student;

    @BeforeEach
    public void setup() {
        studentService = new StudentServiceImpl(studentRepository);
        student = new Student();
        student.setStudentName("Vishwajeet Kotkar");
        student.setEmail("vishwajeet.kotkar@springbox.com");
        student.setAge(24);
    }

    @Test
    public void create_student_should_return_student_response_dto_and_created_201_status_code() {
        StudentRequest studentRequest = new StudentRequest("Vishwajeet Kotkar", "vishwajeet.kotkar@springbox.com",24);
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentResponse studentResponse = studentService.saveStudent(studentRequest);
        assertNotNull(studentResponse);
        assertEquals("Vishwajeet Kotkar", studentResponse.getStudentName());
    }

    @Test
    public void find_all_should_return_list_of_studentResponse_and_status_ok() {
        when(studentRepository.findAll()).thenReturn(List.of(student));

        List<StudentResponse> responseList = studentService.findAllStudents();
        assertNotNull(responseList);
        assertEquals(1, responseList.size());
        assertEquals(24, responseList.getFirst().getAge());
    }
}
