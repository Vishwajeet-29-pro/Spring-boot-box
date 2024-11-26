package com.spring.mysql.dto;

import com.spring.mysql.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private Integer id;
    private String customerName;
    private String email;
    private String phone;
    private String customerAddress;

    public static CustomerResponse toCustomerResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getCustomerName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getCustomerAddress()
        );
    }
}
