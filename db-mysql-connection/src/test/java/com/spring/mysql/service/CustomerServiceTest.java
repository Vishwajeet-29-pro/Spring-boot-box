package com.spring.mysql.service;

import com.spring.mysql.model.Customer;
import com.spring.mysql.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

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
        customer.setCustomerAddress("John Wick");
        customer.setEmail("john.wick@springbox.com");
        customer.setPhone("9999222233");
        customer.setCustomerAddress("Some where in the world");
    }

}