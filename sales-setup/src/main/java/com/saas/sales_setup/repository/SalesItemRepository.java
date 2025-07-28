package com.saas.sales_setup.repository;

import com.saas.sales_setup.model.SalesItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalesItemRepository extends MongoRepository<SalesItem, String> {

    /**
     * Finds all SalesItems belonging to a specific tenant.
     */
    List<SalesItem> findByTenantId(String tenantId);

    /**
     * Finds a single SalesItem by its ID, ensuring it belongs to the specified tenant.
     */
    Optional<SalesItem> findByIdAndTenantId(String id, String tenantId);

    /**
     * Checks if a SalesItem exists by its ID and tenant ID.
     * This is more efficient than fetching the whole document just to check existence.
     * ✅ ADD THIS METHOD
     */
    boolean existsByIdAndTenantId(String id, String tenantId);
}