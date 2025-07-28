package com.saas.sales_setup.controller;

import com.saas.sales_setup.dto.LookupRequestDTO;
import com.saas.sales_setup.dto.LookupResponseDTO;
import com.saas.sales_setup.mapper.LookupMapper;
import com.saas.sales_setup.model.Lookup;
import com.saas.sales_setup.model.LookupType;
import com.saas.sales_setup.service.LookupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing and fetching generic lookup data (dropdown options).
 */
@RestController
@RequestMapping("/api/lookups")
@Tag(name = "Lookup Management", description = "APIs for fetching dropdown options")
public class LookupController {

    @Autowired
    private LookupService lookupService;

    @Autowired
    private LookupMapper lookupMapper;

    /**
     * Retrieves a list of active lookup options for a given type.
     * This endpoint is used to populate all dropdowns in the frontend.
     *
     * @param type The type of lookup to fetch as a string.
     * @return A ResponseEntity containing a list of LookupResponseDTOs.
     */
    @GetMapping
    @Operation(summary = "Get lookup values by type")
    @Parameter(
            name = "type",
            description = "The type of lookup to fetch.",
            required = true,
            // This schema explicitly lists the values to ensure the Swagger UI dropdown works correctly.
            schema = @Schema(
                    type = "string",
                    allowableValues = {
                            "CUSTOMER_TYPE",
                            "BUSINESS_TYPE",
                            "ADDITIONAL_INFO_TYPE",
                            "SERVICE_CATEGORY",
                            "TAX_TYPE",
                            "UOM"
                    }
            )
    )
    public ResponseEntity<List<LookupResponseDTO>> getLookupsByType(@RequestParam("type") String type) {
        // Convert the incoming String parameter to the corresponding enum value.
        LookupType lookupType = LookupType.valueOf(type.toUpperCase());
        List<LookupResponseDTO> lookups = lookupService.getLookupsByType(lookupType);
        return ResponseEntity.ok(lookups);
    }

    /**
     * Creates a new lookup entry.
     * This is typically used by an admin interface to add new dropdown options.
     *
     * @param dto The request body containing the details of the new lookup.
     * @return A ResponseEntity containing the created lookup item as a LookupResponseDTO.
     */
    @PostMapping
    @Operation(summary = "Create a new lookup value")
    public ResponseEntity<LookupResponseDTO> createLookup(@RequestBody LookupRequestDTO dto) {
        Lookup createdLookup = lookupService.createLookup(dto);
        // Convert the created entity back to a response DTO before sending it to the client.
        return ResponseEntity.status(HttpStatus.CREATED).body(lookupMapper.toResponseDto(createdLookup));
    }
}