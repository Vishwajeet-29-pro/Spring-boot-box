package com.spring.mysql.controller;

import com.spring.mysql.dto.CustomerRequest;
import com.spring.mysql.dto.CustomerResponse;
import com.spring.mysql.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomerAccount(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerService.createCustomerAccount(customerRequest);
        return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAllCustomers() {
        return ResponseEntity.ok(customerService.findAllCustomer());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findCustomerDetailsById(@PathVariable("id") Integer customerId) {
        CustomerResponse customerResponse = customerService.getCustomerById(customerId);
        return new ResponseEntity<>(customerResponse, HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomerById(
            @PathVariable("id") Integer customerId,
            @RequestBody CustomerRequest customerRequest
    ) {
        CustomerResponse customerResponse = customerService.updateCustomerById(customerId, customerRequest);
        return ResponseEntity.ok(customerResponse);
    }
}
