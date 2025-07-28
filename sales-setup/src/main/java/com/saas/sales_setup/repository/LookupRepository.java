package com.saas.sales_setup.repository;

import com.saas.sales_setup.model.Lookup;
import com.saas.sales_setup.model.LookupType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LookupRepository extends MongoRepository<Lookup, String> {

    /**
     * ✅ ADD THIS METHOD
     * Finds all active lookups for a given tenant and type, ordered by sortOrder.
     */
    List<Lookup> findByTenantIdAndTypeAndActiveTrueOrderBySortOrderAsc(String tenantId, LookupType type);

    // This helper method is for the DataSeeder
    void deleteByTenantId(String tenantId);
}