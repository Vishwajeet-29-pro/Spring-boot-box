package com.spring.mysql.service;

import com.spring.mysql.dto.CustomerRequest;
import com.spring.mysql.dto.CustomerResponse;
import com.spring.mysql.model.Customer;
import com.spring.mysql.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;
    private Customer customer;

    @BeforeEach
    public void setup() {
        customerService = new CustomerServiceImpl(customerRepository);
        customer = new Customer();
        customer.setId(1);
        customer.setCustomerName("John Wick");
        customer.setEmail("john.wick@springbox.com");
        customer.setPhone("9999222233");
        customer.setCustomerAddress("Some where in the world");
    }

    @Test
    public void create_customer_should_return_customer_response() {
        CustomerRequest customerRequest = new CustomerRequest("John Wick", "john.wick@springbox.com", "9999222233","Some where in the world");
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerResponse customerResponse = customerService.createCustomerAccount(customerRequest);
        assertNotNull(customerResponse);
        assertEquals("John Wick", customerResponse.getCustomerName());
    }
}