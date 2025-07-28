package com.saas.sales_setup.controller;

import com.saas.sales_setup.dto.AddSalesPriceRequestDto;
import com.saas.sales_setup.dto.SalesPriceDetailDto;
import com.saas.sales_setup.model.SalesItem;
import com.saas.sales_setup.service.SalesItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sales-items")
@Tag(name = "Sales Items", description = "APIs for managing sales items and their prices")
public class SalesItemController {

    private final SalesItemService salesItemService;

    public SalesItemController(SalesItemService salesItemService) {
        this.salesItemService = salesItemService;
    }

    @PostMapping
    @Operation(summary = "Create a new sales item")
    public ResponseEntity<SalesItem> createSalesItem(@Valid @RequestBody SalesItem salesItem) {
        SalesItem createdSalesItem = salesItemService.createSalesItem(salesItem);
        return new ResponseEntity<>(createdSalesItem, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all sales items for the current tenant")
    public ResponseEntity<List<SalesItem>> getAllSalesItems() {
        List<SalesItem> salesItems = salesItemService.getAllSalesItems();
        return ResponseEntity.ok(salesItems);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a sales item by ID")
    public ResponseEntity<SalesItem> getSalesItemById(@PathVariable String id) {
        return salesItemService.getSalesItemById(id)
                .map(salesItem -> ResponseEntity.ok(salesItem))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing sales item")
    public ResponseEntity<SalesItem> updateSalesItem(@PathVariable String id, @Valid @RequestBody SalesItem salesItemDetails) {
        return salesItemService.updateSalesItem(id, salesItemDetails)
                .map(updatedItem -> ResponseEntity.ok(updatedItem))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a sales item by ID")
    public ResponseEntity<Void> deleteSalesItem(@PathVariable String id) {
        salesItemService.deleteSalesItem(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/prices")
    @Operation(summary = "Add a new sales price to an existing item")
    public ResponseEntity<SalesPriceDetailDto> addSalesPrice(@RequestBody AddSalesPriceRequestDto request) {
        SalesPriceDetailDto newPriceDetail = salesItemService.addSalesPriceToItem(request);
        return ResponseEntity.ok(newPriceDetail);
    }
}