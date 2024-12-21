package com.spring.postgres.dto;

import com.spring.postgres.model.Student;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response object representing a student's details")
public class StudentResponse {

    @Schema(description = "Unique ID of the student", example = "123")
    private Integer id;

    @Schema(description = "Full name of the student", example = "John Doe")
    private String studentName;

    @Schema(description = "Email address of the student", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Age of the student", example = "25")
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
