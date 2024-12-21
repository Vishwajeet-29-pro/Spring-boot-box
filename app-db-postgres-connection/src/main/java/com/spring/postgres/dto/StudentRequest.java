package com.spring.postgres.dto;

import com.spring.postgres.model.Student;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request payload for creating or updating a student")
public class StudentRequest {

    @Schema(description = "Full name of the student", example = "John Doe")
    private String name;

    @Schema(description = "Email address of the student", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Age of the student", example = "25")
    private Integer age;

    public static Student toStudent(StudentRequest studentRequest) {
       Student student = new Student();
       student.setStudentName(studentRequest.getName());
       student.setEmail(studentRequest.getEmail());
       student.setAge(studentRequest.getAge());

       return student;
    }
}
