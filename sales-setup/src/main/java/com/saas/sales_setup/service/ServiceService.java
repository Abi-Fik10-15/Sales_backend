package com.saas.sales_setup.service;

import com.saas.sales_setup.model.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing services, located in the 'service' package.
 * It defines the contract for the business logic operations related to services.
 */
public interface ServiceService {

    /**
     * Creates and saves a new service.
     * @param service The service object to create.
     * @return The saved service entity.
     */
    Service createService(Service service);

    /**
     * Retrieves all services for the current tenant.
     * @return A list of services.
     */
    List<Service> getAllServices();

    /**
     * Retrieves a service by its ID, ensuring it belongs to the current tenant.
     * @param id The ID of the service to retrieve.
     * @return An Optional containing the service if found, otherwise empty.
     */
    Optional<Service> getServiceById(String id);

    /**
     * Updates an existing service.
     * @param id The ID of the service to update.
     * @param serviceDetails The service object with updated details.
     * @return The updated service, or null if not found.
     */
    Service updateService(String id, Service serviceDetails);

    /**
     * Deletes a service by its ID.
     * @param id The ID of the service to delete.
     */
    void deleteService(String id);
}
