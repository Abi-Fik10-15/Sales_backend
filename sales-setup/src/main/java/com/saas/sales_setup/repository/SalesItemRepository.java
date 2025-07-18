package com.saas.sales_setup.repository;

import com.saas.sales_setup.model.SalesItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for SalesItem documents.
 * Provides CRUD operations and custom queries for the SalesItem entity.
 */
public interface SalesItemRepository extends MongoRepository<SalesItem, String> {

    /**
     * Finds all sales items associated with a specific tenant.
     * @param tenantId The ID of the tenant.
     * @return A list of sales items for the given tenant.
     */
    List<SalesItem> findByTenantId(String tenantId);

    /**
     * Finds a sales item by its ID and tenantId to ensure data isolation.
     * @param id The unique ID of the sales item.
     * @param tenantId The ID of the tenant.
     * @return An Optional containing the sales item if found, or an empty Optional otherwise.
     */
    Optional<SalesItem> findByIdAndTenantId(String id, String tenantId);

}