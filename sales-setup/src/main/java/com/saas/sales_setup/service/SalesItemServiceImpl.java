package com.saas.sales_setup.service;

import com.saas.sales_setup.config.TenantContext;
import com.saas.sales_setup.model.SalesItem;
import com.saas.sales_setup.repository.SalesItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the SalesItemService interface.
 * Contains the core business logic for sales item management.
 */
@Service
public class SalesItemServiceImpl implements SalesItemService {

    private final SalesItemRepository salesItemRepository;

    @Autowired
    public SalesItemServiceImpl(SalesItemRepository salesItemRepository) {
        this.salesItemRepository = salesItemRepository;
    }

    @Override
    public SalesItem createSalesItem(SalesItem salesItem) {
        String tenantId = TenantContext.getCurrentTenant();
        salesItem.setTenantId(tenantId);
        return salesItemRepository.save(salesItem);
    }

    @Override
    public List<SalesItem> getAllSalesItems() {
        String tenantId = TenantContext.getCurrentTenant();
        return salesItemRepository.findByTenantId(tenantId);
    }

    @Override
    public Optional<SalesItem> getSalesItemById(String id) {
        String tenantId = TenantContext.getCurrentTenant();
        return salesItemRepository.findByIdAndTenantId(id, tenantId);
    }

    @Override
    public SalesItem updateSalesItem(String id, SalesItem salesItemDetails) {
        Optional<SalesItem> existingSalesItemOptional = getSalesItemById(id);

        if (existingSalesItemOptional.isPresent()) {
            SalesItem existingSalesItem = existingSalesItemOptional.get();
            existingSalesItem.setItemId(salesItemDetails.getItemId());
            existingSalesItem.setItemName(salesItemDetails.getItemName());
            existingSalesItem.setItemCategory(salesItemDetails.getItemCategory());
            existingSalesItem.setPurchasePrice(salesItemDetails.getPurchasePrice());
            existingSalesItem.setUom(salesItemDetails.getUom());
            existingSalesItem.setPriceDetails(salesItemDetails.getPriceDetails());

            return salesItemRepository.save(existingSalesItem);
        }
        return null; // Or throw a NotFoundException
    }

    @Override
    public void deleteSalesItem(String id) {
        Optional<SalesItem> salesItemOptional = getSalesItemById(id);
        salesItemOptional.ifPresent(salesItemRepository::delete);
    }
}
