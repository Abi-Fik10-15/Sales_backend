package com.saas.sales_setup.service;

import com.saas.sales_setup.config.TenantContext;
import com.saas.sales_setup.model.Customer;
import com.saas.sales_setup.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the CustomerService interface.
 * Contains the core business logic for customer management.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        String tenantId = TenantContext.getCurrentTenant();
        customer.setTenantId(tenantId);
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        String tenantId = TenantContext.getCurrentTenant();
        return customerRepository.findByTenantId(tenantId);
    }

    @Override
    public Optional<Customer> getCustomerById(String id) {
        String tenantId = TenantContext.getCurrentTenant();
        return customerRepository.findByIdAndTenantId(id, tenantId);
    }

    @Override
    public Customer updateCustomer(String id, Customer customerDetails) {
        Optional<Customer> existingCustomerOptional = getCustomerById(id);

        if (existingCustomerOptional.isPresent()) {
            Customer existingCustomer = existingCustomerOptional.get();
            // Map all fields from the request to the existing customer object
            existingCustomer.setCustomerName(customerDetails.getCustomerName());
            existingCustomer.setTinNo(customerDetails.getTinNo());
            existingCustomer.setVatNo(customerDetails.getVatNo());
            existingCustomer.setCustomerType(customerDetails.getCustomerType());
            existingCustomer.setTradeName(customerDetails.getTradeName());
            existingCustomer.setTradeLicenseIssuedDate(customerDetails.getTradeLicenseIssuedDate());
            existingCustomer.setBusinessType(customerDetails.getBusinessType());
            existingCustomer.setTradeBusinessLicenseNo(customerDetails.getTradeBusinessLicenseNo());
            existingCustomer.setContactPerson(customerDetails.getContactPerson());
            existingCustomer.setRegistrationDate(customerDetails.getRegistrationDate());
            existingCustomer.setCustomerAddress(customerDetails.getCustomerAddress());
            existingCustomer.setTinLicenseAddress(customerDetails.getTinLicenseAddress());
            existingCustomer.setBusinessAddress(customerDetails.getBusinessAddress());
            existingCustomer.setAdditionalInfo(customerDetails.getAdditionalInfo());

            return customerRepository.save(existingCustomer);
        }
        return null; // Or throw a NotFoundException
    }

    @Override
    public void deleteCustomer(String id) {
        Optional<Customer> customerOptional = getCustomerById(id);
        customerOptional.ifPresent(customerRepository::delete);
    }
}
