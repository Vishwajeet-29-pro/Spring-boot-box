package com.spring.mysql.service;

import com.spring.mysql.dto.CustomerRequest;
import com.spring.mysql.dto.CustomerResponse;
import com.spring.mysql.exception.CustomerNotFoundException;
import com.spring.mysql.model.Customer;
import com.spring.mysql.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @Test
    public void get_all_customer_should_return_list_of_customers() {
        when(customerRepository.findAll()).thenReturn(List.of(customer));
        List<CustomerResponse> customerResponseList = customerService.findAllCustomer();

        assertNotNull(customerResponseList);
        assertEquals(1, customerResponseList.size());
        assertEquals("John Wick", customerResponseList.getFirst().getCustomerName());
    }

    @Test
    public void get_customer_by_id_should_return_customer_detail() {
        when(customerRepository.findById(any(Integer.class))).thenReturn(Optional.of(customer));

        CustomerResponse customerResponse = customerService.getCustomerById(customer.getId());

        assertNotNull(customerResponse);
        assertEquals("John Wick", customerResponse.getCustomerName());
        assertEquals("Some where in the world", customerResponse.getCustomerAddress());
    }

    @Test
    public void get_customer_by_not_found_should_throw_CustomerNotFoundException() {
        Integer id = 22;
        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(CustomerNotFoundException.class,
                () -> customerService.getCustomerById(id));

        assertEquals("Customer with id: 22 not found", exception.getMessage());
    }

    @Test
    public void update_by_id_should_update_customer_details_and_return_customer_details() {
        CustomerRequest customerRequest = new CustomerRequest("John Wick","john.wick20@springbox.com","8833224455","New York");
        when(customerRepository.getReferenceById(any(Integer.class))).thenReturn(customer);
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerResponse customerResponse = customerService.updateCustomerById(customer.getId(), customerRequest);

        assertNotNull(customerResponse);
        assertEquals("john.wick20@springbox.com", customerResponse.getEmail());
        assertEquals("8833224455", customerResponse.getPhone());
        assertEquals("New York", customerResponse.getCustomerAddress());
    }

    @Test
    public void when_id_not_found_should_throw_exception() {
        Integer id = 22;
        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(CustomerNotFoundException.class,
                () -> customerService.updateCustomerById(id, null));
        assertEquals("Customer with id: 22 not found", exception.getMessage());
    }

    @Test
    public void delete_by_id_should_delete_customer_details_returns_nothing() {
        Integer id = 1;
        when(customerRepository.existsById(id)).thenReturn(true);
        customerService.deleteCustomerById(id);

        verify(customerRepository, times(1)).deleteById(id);
    }

    @Test
    public void delete_by_id_not_found_should_throw_exception() {
        Integer id = 22;
        when(customerRepository.existsById(id)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> customerService.deleteCustomerById(id));
        assertEquals("Customer not found", exception.getMessage());
    }
}