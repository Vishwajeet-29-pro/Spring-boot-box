package com.spring.mysql.dto;

import com.spring.mysql.model.Customer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for creating a new customer")
public class CustomerRequest {

    @Schema(description = "Name of the customer", example = "John Doe")
    private String customerName;

    @Schema(description = "Email address of the customer", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Phone number of the customer", example = "+1234567890")
    private String phone;

    @Schema(description = "Address of the customer", example = "123 Main St, Springfield, USA")
    private String customerAddress;

    public static Customer toCustomer(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setCustomerName(customerRequest.getCustomerName());
        customer.setEmail(customerRequest.getEmail());
        customer.setPhone(customerRequest.getPhone());
        customer.setCustomerAddress(customerRequest.getCustomerAddress());
        return customer;
    }
}
