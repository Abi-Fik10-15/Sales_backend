package com.saas.sales_setup.repository;

import com.saas.sales_setup.model.Service;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Service documents, located in the 'repository' package.
 * It extends MongoRepository, which provides standard CRUD (Create, Read, Update, Delete)
 * operations and the ability to define custom query methods.
 */
public interface ServiceRepository extends MongoRepository<Service, String> {

    /**
     * Finds all services associated with a specific tenant.
     * Spring Data MongoDB automatically implements this method based on its name.
     * @param tenantId The ID of the tenant.
     * @return A list of services for the given tenant.
     */
    List<Service> findByTenantId(String tenantId);

    /**
     * Finds a service by its ID and tenantId. This ensures that a user from one tenant
     * cannot accidentally access data from another tenant.
     * @param id The unique ID of the service.
     * @param tenantId The ID of the tenant.
     * @return An Optional containing the service if found, or an empty Optional otherwise.
     */
    Optional<Service> findByIdAndTenantId(String id, String tenantId);

}
