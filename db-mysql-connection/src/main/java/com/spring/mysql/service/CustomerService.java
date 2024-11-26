package com.spring.mysql.service;

import com.spring.mysql.dto.CustomerRequest;
import com.spring.mysql.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse createCustomerAccount(CustomerRequest customerRequest);
    List<CustomerResponse> findAllCustomer();
    CustomerResponse getCustomerById(Integer id);
    CustomerResponse updateCustomerById(Integer id, CustomerRequest customerRequest);
    void deleteCustomerById(Integer id);
}
