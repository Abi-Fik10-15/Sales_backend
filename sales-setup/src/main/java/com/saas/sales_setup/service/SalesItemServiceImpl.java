package com.saas.sales_setup.service;

import com.saas.sales_setup.config.TenantContext;
import com.saas.sales_setup.dto.AddSalesPriceRequestDto;
import com.saas.sales_setup.dto.SalesPriceDetailDto;
import com.saas.sales_setup.dto.TaxCalculationResultDTO;
import com.saas.sales_setup.model.ItemPriceDetail;
import com.saas.sales_setup.model.SalesItem;
import com.saas.sales_setup.repository.SalesItemRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalesItemServiceImpl implements SalesItemService {

    private final SalesItemRepository salesItemRepository;
    private final TaxService taxService;

    public SalesItemServiceImpl(SalesItemRepository salesItemRepository, TaxService taxService) {
        this.salesItemRepository = salesItemRepository;
        this.taxService = taxService;
    }

    @Override
    public SalesItem createSalesItem(SalesItem salesItem) {
        salesItem.setTenantId(TenantContext.getCurrentTenant());
        return salesItemRepository.save(salesItem);
    }

    @Override
    public Optional<SalesItem> getSalesItemById(String id) {
        return salesItemRepository.findByIdAndTenantId(id, TenantContext.getCurrentTenant());
    }

    @Override
    public List<SalesItem> getAllSalesItems() {
        return salesItemRepository.findByTenantId(TenantContext.getCurrentTenant());
    }

    @Override
    public SalesPriceDetailDto addSalesPriceToItem(AddSalesPriceRequestDto request) {
        SalesItem salesItem = getSalesItemById(request.getSalesItemId())
                .orElseThrow(() -> new RuntimeException("SalesItem not found with ID: " + request.getSalesItemId()));
        TaxCalculationResultDTO taxResult = taxService.calculate(request.getSalesPrice(), request.getTaxTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Tax ID: " + request.getTaxTypeId()));
        ItemPriceDetail newPriceDetail = new ItemPriceDetail();
        newPriceDetail.setSalesUnitPrice(taxResult.getBasePrice());
        newPriceDetail.setDate(request.getEffectiveDate());
        newPriceDetail.setTaxTypeId(request.getTaxTypeId());
        if (salesItem.getPriceDetails() == null) {
            salesItem.setPriceDetails(new ArrayList<>());
        }
        salesItem.getPriceDetails().add(newPriceDetail);
        salesItemRepository.save(salesItem);
        return SalesPriceDetailDto.builder()
                .basePrice(taxResult.getBasePrice())
                .taxAmount(taxResult.getTaxAmount())
                .totalPriceWithTax(taxResult.getTotalPrice())
                .effectiveDate(request.getEffectiveDate())
                .build();
    }

    @Override
    public Optional<SalesItem> updateSalesItem(String id, SalesItem itemDetails) {
        return getSalesItemById(id).map(existingItem -> {
            existingItem.setItemName(itemDetails.getItemName());
            existingItem.setItemId(itemDetails.getItemId()); // Corrected from getItemCode()
            existingItem.setPurchasePrice(itemDetails.getPurchasePrice());
            existingItem.setItemCategory(itemDetails.getItemCategory());
            existingItem.setUom(itemDetails.getUom());
            return salesItemRepository.save(existingItem);
        });
    }

    @Override
    public void deleteSalesItem(String id) {
        if (salesItemRepository.existsByIdAndTenantId(id, TenantContext.getCurrentTenant())) {
            salesItemRepository.deleteById(id);
        }
    }
}