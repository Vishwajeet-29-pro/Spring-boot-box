package com.spring.postgres.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.postgres.dto.StudentRequest;
import com.spring.postgres.dto.StudentResponse;
import com.spring.postgres.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    private StudentRequest studentRequest;
    private StudentResponse studentResponse;

    @BeforeEach
    public void setup() {
        studentRequest = new StudentRequest("John Wick", "john.wick@springbox.com",22);
        studentResponse = new StudentResponse(1, "John Wick", "john.wick@springbox.com", 22);
    }

    @Test
    public void create_student_should_return_student_response_dto_and_201_status_code() throws Exception {
        when(studentService.saveStudent(any(StudentRequest.class))).thenReturn(studentResponse);

        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(studentRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.studentName").value("John Wick"))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void find_all_should_return_list_of_student_and_return_200_status_code() throws Exception {
        when(studentService.findAllStudents()).thenReturn(List.of(studentResponse));

        mockMvc.perform(get("/students")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$.[0].studentName").value("John Wick"))
                .andExpect(jsonPath("$.[0].age").value(22))
                .andReturn().getResponse().getContentAsString();
    }
}
