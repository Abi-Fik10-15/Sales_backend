package com.saas.sales_setup.service;

import com.saas.sales_setup.dto.AddSalesPriceRequestDto;
import com.saas.sales_setup.dto.SalesPriceDetailDto;
import com.saas.sales_setup.model.SalesItem;
import java.util.List;
import java.util.Optional;

public interface SalesItemService {
    SalesItem createSalesItem(SalesItem salesItem);
    Optional<SalesItem> getSalesItemById(String id);
    List<SalesItem> getAllSalesItems();
    SalesPriceDetailDto addSalesPriceToItem(AddSalesPriceRequestDto request);
    Optional<SalesItem> updateSalesItem(String id, SalesItem itemDetails);
    void deleteSalesItem(String id);
}