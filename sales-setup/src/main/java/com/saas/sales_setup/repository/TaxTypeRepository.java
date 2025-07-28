package com.saas.sales_setup.repository;

import com.saas.sales_setup.model.TaxType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository for accessing TaxType data in MongoDB.
 */
@Repository
public interface TaxTypeRepository extends MongoRepository<TaxType, String> {

    /**
     * Finds a tax type by its ID, ensuring it belongs to the correct tenant.
     * This is crucial for multi-tenancy.
     */
    Optional<TaxType> findByIdAndTenantId(String id, String tenantId);
}