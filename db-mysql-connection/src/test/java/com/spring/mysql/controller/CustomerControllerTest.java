package com.spring.mysql.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.mysql.dto.CustomerRequest;
import com.spring.mysql.dto.CustomerResponse;
import com.spring.mysql.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private CustomerRequest customerRequest;
    private CustomerResponse customerResponse;

    @BeforeEach
    public void setup() {
        customerRequest = new CustomerRequest("John Doe","john.doe@springbox.com","9876543210", "Somewhere");
        customerResponse = new CustomerResponse(1, "John Doe", "john.doe@springbox.com","9876543210","Somewhere");
    }

    @Test
    public void create_customer_account_should_return_customer_response_and_201_created() throws Exception {
        when(customerService.createCustomerAccount(any(CustomerRequest.class))).thenReturn(customerResponse);

        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(customerRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customerName").value("John Doe"))
                .andExpect(jsonPath("$.phone").value("9876543210"))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void find_all_customer_should_return_list_of_customers() throws Exception {
        when(customerService.findAllCustomer()).thenReturn(List.of(customerResponse));

        mockMvc.perform(get("/api/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$.[0].customerName").value("John Doe"))
                .andExpect(jsonPath("$.[0].phone").value("9876543210"))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void get_customer_by_id_should_return_customer_and_status_isFound() throws Exception {
        when(customerService.getCustomerById(any(Integer.class))).thenReturn(customerResponse);

        mockMvc.perform(get("/api/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.customerName").value("John Doe"))
                .andExpect(jsonPath("$.customerAddress").value("Somewhere"));
    }
}