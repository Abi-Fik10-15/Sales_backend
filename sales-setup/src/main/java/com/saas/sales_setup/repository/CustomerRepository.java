package com.saas.sales_setup.repository;

import com.saas.sales_setup.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Customer documents.
 * Provides CRUD operations and custom queries for the Customer entity.
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {

    /**
     * Finds all customers associated with a specific tenant.
     * Spring Data MongoDB automatically implements this method based on its name.
     * @param tenantId The ID of the tenant.
     * @return A list of customers for the given tenant.
     */
    List<Customer> findByTenantId(String tenantId);

    /**
     * Finds a customer by its ID and tenantId to ensure data isolation.
     * @param id The unique ID of the customer.
     * @param tenantId The ID of the tenant.
     * @return An Optional containing the customer if found, or an empty Optional otherwise.
     */
    Optional<Customer> findByIdAndTenantId(String id, String tenantId);

}