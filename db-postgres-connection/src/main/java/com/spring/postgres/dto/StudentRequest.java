package com.spring.postgres.dto;

import com.spring.postgres.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {

    private String name;
    private String email;
    private Integer age;

    public static Student toStudent(StudentRequest studentRequest) {
       Student student = new Student();
       student.setStudentName(studentRequest.getName());
       student.setEmail(studentRequest.getEmail());
       student.setAge(studentRequest.getAge());

       return student;
    }
}
