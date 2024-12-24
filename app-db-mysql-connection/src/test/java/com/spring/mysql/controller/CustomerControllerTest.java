package com.spring.mysql.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.mysql.dto.CustomerRequest;
import com.spring.mysql.dto.CustomerResponse;
import com.spring.mysql.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
@SuppressWarnings("unused")
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
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
        Integer customerId = 1;
        when(customerService.getCustomerById(customerId)).thenReturn(customerResponse);

        mockMvc.perform(get("/api/customers/{id}", customerId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.customerName").value("John Doe"))
                .andExpect(jsonPath("$.customerAddress").value("Somewhere"));
    }

    @Test
    public void update_by_should_return_updated_customer_and_status_200() throws Exception {
        Integer id = 1;
        CustomerRequest  updateCustomerRequest = new CustomerRequest("John Doe","john.doe20@springbox.com","9876542222", "Pune");
        CustomerResponse updatedCustomerResponse = new CustomerResponse(1, "John Doe", "john.doe20@springbox.com","9876542222","Pune");

        when(customerService.updateCustomerById(eq(id), any(CustomerRequest.class))).thenReturn(updatedCustomerResponse);

        mockMvc.perform(put("/api/customers/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updateCustomerRequest)))
                .andExpect(jsonPath("$.email").value("john.doe20@springbox.com"))
                .andExpect(jsonPath("$.phone").value("9876542222"))
                .andExpect(jsonPath("$.customerAddress").value("Pune"));

    }

    @Test
    public void delete_by_should_remove_customer_details_and_return_no_content_success_204() throws Exception {
       Integer id = 1;
       doNothing().when(customerService).deleteCustomerById(id);

       mockMvc.perform(delete("/api/customers/{id}", id))
               .andExpect(status().isNoContent());
    }
}
