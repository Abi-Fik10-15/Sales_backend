package com.saas.sales_setup.controller;

import com.saas.sales_setup.model.SalesItem;
import com.saas.sales_setup.service.SalesItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing sales item price registrations.
 */
@RestController
@RequestMapping("/api/sales-items")
@Tag(name = "Sales Item Price Setup", description = "APIs for managing sales item prices")
public class SalesItemController {

    private final SalesItemService salesItemService;

    @Autowired
    public SalesItemController(SalesItemService salesItemService) {
        this.salesItemService = salesItemService;
    }

    @PostMapping
    @Operation(summary = "Create a new sales item price registration")
    public ResponseEntity<SalesItem> createSalesItem(@Valid @RequestBody SalesItem salesItem) {
        SalesItem createdSalesItem = salesItemService.createSalesItem(salesItem);
        return new ResponseEntity<>(createdSalesItem, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all sales items for the current tenant")
    public ResponseEntity<List<SalesItem>> getAllSalesItems() {
        List<SalesItem> salesItems = salesItemService.getAllSalesItems();
        return new ResponseEntity<>(salesItems, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a sales item by ID")
    public ResponseEntity<SalesItem> getSalesItemById(@PathVariable String id) {
        return salesItemService.getSalesItemById(id)
                .map(salesItem -> new ResponseEntity<>(salesItem, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing sales item")
    public ResponseEntity<SalesItem> updateSalesItem(@PathVariable String id, @Valid @RequestBody SalesItem salesItemDetails) {
        SalesItem updatedSalesItem = salesItemService.updateSalesItem(id, salesItemDetails);
        if (updatedSalesItem != null) {
            return new ResponseEntity<>(updatedSalesItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a sales item")
    public ResponseEntity<HttpStatus> deleteSalesItem(@PathVariable String id) {
        salesItemService.deleteSalesItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
