package com.saas.sales_setup.service;

import com.saas.sales_setup.model.SalesItem;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing sales items.
 * Defines the business logic operations for the SalesItem entity.
 */
public interface SalesItemService {

    SalesItem createSalesItem(SalesItem salesItem);

    List<SalesItem> getAllSalesItems();

    Optional<SalesItem> getSalesItemById(String id);

    SalesItem updateSalesItem(String id, SalesItem salesItemDetails);

    void deleteSalesItem(String id);
}
