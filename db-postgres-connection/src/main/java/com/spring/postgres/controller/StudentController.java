package com.spring.postgres.controller;

import com.spring.postgres.dto.StudentRequest;
import com.spring.postgres.dto.StudentResponse;
import com.spring.postgres.model.Student;
import com.spring.postgres.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @Operation(summary = "Create a new Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentRequest.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided") })
    @PostMapping
    public ResponseEntity<StudentResponse> saveStudent(@RequestBody StudentRequest studentRequest) {
        return new ResponseEntity<>(studentService.saveStudent(studentRequest), HttpStatus.CREATED);
    }

    @Operation(summary = "Retrieve all Students")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all students retrieved successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentResponse.class)) }),
            @ApiResponse(responseCode = "204", description = "No students found",
                    content = @Content) })
    @GetMapping
    public ResponseEntity<List<StudentResponse>> findAllStudents() {
        return ResponseEntity.ok(studentService.findAllStudents());
    }

    @Operation(summary = "Get a Student by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Student",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Student not found",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> findStudentById(@PathVariable("id") Integer studentId) {
        return ResponseEntity.ok(studentService.findStudentById(studentId));
    }

    @Operation(summary = "Update an existing Student by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Student not found",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudentById(
            @PathVariable("id") Integer studentId,
            @RequestBody StudentRequest studentRequest
    ) {
        return ResponseEntity.ok(studentService.updateStudentById(studentId, studentRequest));
    }

    @Operation(summary = "Delete a Student by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Student deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Student not found",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable("id") Integer studentId) {
       studentService.deleteStudentById(studentId);
       return ResponseEntity.noContent().build();
    }
}
