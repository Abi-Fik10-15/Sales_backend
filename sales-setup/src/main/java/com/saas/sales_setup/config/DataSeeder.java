package com.saas.sales_setup.config;

import com.saas.sales_setup.model.Lookup;
import com.saas.sales_setup.model.LookupType;
import com.saas.sales_setup.repository.LookupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

/**
 * Populates the database with initial lookup data on application startup.
 * This is useful for development and ensuring essential dropdowns have values.
 */
@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private LookupRepository lookupRepository;

    @Override
    public void run(String... args) throws Exception {
        // Define a sample tenant ID for the seeded data.
        String tenantId = "tenant-1";

        // To prevent creating duplicate entries every time the app restarts,
        // we first delete any existing data for this sample tenant.
        if (lookupRepository.count() > 0) {
            lookupRepository.deleteByTenantId(tenantId);
            System.out.println("--- Cleared existing lookup data for tenant: " + tenantId + " ---");
        }

        System.out.println("--- Seeding lookup data for tenant: " + tenantId + " ---");

        // A single list containing all lookup values to be added.
        List<Lookup> lookupsToSeed = Arrays.asList(
                /* * ==========================
                 * Customer Registration Lookups
                 * ==========================
                 */
                // Customer Types
                new Lookup(null, tenantId, LookupType.CUSTOMER_TYPE, "PLC", "PLC", 10, true),
                new Lookup(null, tenantId, LookupType.CUSTOMER_TYPE, "Private", "PRIVATE", 20, true),
                new Lookup(null, tenantId, LookupType.CUSTOMER_TYPE, "Public Organization", "PUBLIC_ORG", 30, true),
                new Lookup(null, tenantId, LookupType.CUSTOMER_TYPE, "Person", "PERSON", 40, true),

                // Business Types
                new Lookup(null, tenantId, LookupType.BUSINESS_TYPE, "Business Type 1", "TYPE_1", 10, true),
                new Lookup(null, tenantId, LookupType.BUSINESS_TYPE, "Business Type 2", "TYPE_2", 20, true),
                new Lookup(null, tenantId, LookupType.BUSINESS_TYPE, "BusinessT", "TYPE_T", 30, true),
                new Lookup(null, tenantId, LookupType.BUSINESS_TYPE, "BusinessTy4", "TYPE_Y4", 40, true),

                // Additional Information Types
                new Lookup(null, tenantId, LookupType.ADDITIONAL_INFO_TYPE, "Tax Center", "TAX_CENTER", 10, true),
                new Lookup(null, tenantId, LookupType.ADDITIONAL_INFO_TYPE, "Agreement Document", "AGREE_DOC", 20, true),
                new Lookup(null, tenantId, LookupType.ADDITIONAL_INFO_TYPE, "COC No", "COC_NO", 30, true),
                new Lookup(null, tenantId, LookupType.ADDITIONAL_INFO_TYPE, "Letter No", "LETTER_NO", 40, true),

                /* * ==========================
                 * Service Registration Lookups
                 * ==========================
                 */
                // Service Categories
                new Lookup(null, tenantId, LookupType.SERVICE_CATEGORY, "Category 3", "CAT_3", 10, true),
                new Lookup(null, tenantId, LookupType.SERVICE_CATEGORY, "category 4", "CAT_4", 20, true),
                new Lookup(null, tenantId, LookupType.SERVICE_CATEGORY, "Category 5", "CAT_5", 30, true),

                // Tax Types
                new Lookup(null, tenantId, LookupType.TAX_TYPE, "WithHold 2%", "WH_2", 10, true),
                new Lookup(null, tenantId, LookupType.TAX_TYPE, "With Hold 30%", "WH_30", 20, true),
                new Lookup(null, tenantId, LookupType.TAX_TYPE, "Vat 15%", "VAT_15", 30, true),
                new Lookup(null, tenantId, LookupType.TAX_TYPE, "Vat and With Hold 17%", "VAT_WH_17", 40, true),
                new Lookup(null, tenantId, LookupType.TAX_TYPE, "vta 1%", "VTA_1", 50, true),

                // Unit of Measure (UOM)
                new Lookup(null, tenantId, LookupType.UOM, "BAG 15", "BAG_15", 10, true),
                new Lookup(null, tenantId, LookupType.UOM, "BAG 25", "BAG_25", 20, true),
                new Lookup(null, tenantId, LookupType.UOM, "BAG 50", "BAG_50", 30, true),
                new Lookup(null, tenantId, LookupType.UOM, "Barrel - Liquid Measure", "BARREL_LIQUID", 40, true),
                new Lookup(null, tenantId, LookupType.UOM, "Box", "BOX", 50, true),
                new Lookup(null, tenantId, LookupType.UOM, "BARREL", "BARREL", 60, true),
                new Lookup(null, tenantId, LookupType.UOM, "Bushel - Dry Measure", "BUSHEL_DRY", 70, true)
        );

        // Save all the defined lookups to the database in a single operation.
        lookupRepository.saveAll(lookupsToSeed);

        System.out.println("✅ Successfully seeded " + lookupsToSeed.size() + " lookup items.");
    }
}