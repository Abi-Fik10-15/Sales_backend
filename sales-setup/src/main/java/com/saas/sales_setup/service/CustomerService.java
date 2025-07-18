package com.saas.sales_setup.service;

import com.saas.sales_setup.model.Customer;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing customers.
 * Defines the business logic operations for the Customer entity.
 */
public interface CustomerService {

    /**
     * Creates and saves a new customer.
     * @param customer The customer object to create.
     * @return The saved customer entity.
     */
    Customer createCustomer(Customer customer);

    /**
     * Retrieves all customers for the current tenant.
     * @return A list of customers.
     */
    List<Customer> getAllCustomers();

    /**
     * Retrieves a customer by its ID, ensuring it belongs to the current tenant.
     * @param id The ID of the customer to retrieve.
     * @return An Optional containing the customer if found, otherwise empty.
     */
    Optional<Customer> getCustomerById(String id);

    /**
     * Updates an existing customer.
     * @param id The ID of the customer to update.
     * @param customerDetails The customer object with updated details.
     * @return The updated customer, or null if not found.
     */
    Customer updateCustomer(String id, Customer customerDetails);

    /**
     * Deletes a customer by its ID.
     * @param id The ID of the customer to delete.
     */
    void deleteCustomer(String id);
}
