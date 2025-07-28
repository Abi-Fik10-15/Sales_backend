package com.saas.sales_setup.service;

import com.saas.sales_setup.dto.LookupRequestDTO;
import com.saas.sales_setup.dto.LookupResponseDTO;
import com.saas.sales_setup.model.Lookup;
import com.saas.sales_setup.model.LookupType;
import java.util.List;

/**
 * Service interface for managing lookup data (dropdown options).
 */
public interface LookupService {

    /**
     * Retrieves a list of active lookup options for a given type and tenant.
     *
     * @param type The type of lookup to fetch (e.g., CUSTOMER_TYPE).
     * @return A list of {@link LookupResponseDTO} objects.
     */
    List<LookupResponseDTO> getLookupsByType(LookupType type);

    /**
     * Creates a new lookup entry.
     *
     * @param dto The DTO containing the data for the new lookup.
     * @return The created {@link Lookup} entity.
     */
    Lookup createLookup(LookupRequestDTO dto);
}