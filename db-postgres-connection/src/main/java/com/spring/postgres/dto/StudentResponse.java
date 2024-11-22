package com.spring.postgres.dto;

import com.spring.postgres.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {

    private Integer id;
    private String studentName;
    private String email;
    private Integer age;

    public static StudentResponse toStudentResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getStudentName(),
                student.getEmail(),
                student.getAge()
        );
    }
}
