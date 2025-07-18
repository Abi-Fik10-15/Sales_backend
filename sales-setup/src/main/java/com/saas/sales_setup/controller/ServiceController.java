package com.saas.sales_setup.controller;

import com.saas.sales_setup.model.Service;
import com.saas.sales_setup.service.ServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing services.
 * This class defines the API endpoints and delegates business logic to the ServiceService.
 */
@RestController
@RequestMapping("/api/services")
@Tag(name = "Service Registration", description = "APIs for managing services")
public class ServiceController {

    private final ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    /**
     * Endpoint to create a new service.
     * @param service The service data from the request body.
     * @return The created service with a 201 status code.
     */
    @PostMapping
    @Operation(summary = "Create a new service")
    public ResponseEntity<Service> createService(@Valid @RequestBody Service service) {
        Service createdService = serviceService.createService(service);
        return new ResponseEntity<>(createdService, HttpStatus.CREATED);
    }

    /**
     * Endpoint to get all services for the current tenant.
     * @return A list of services with a 200 status code.
     */
    @GetMapping
    @Operation(summary = "Get all services for the current tenant")
    public ResponseEntity<List<Service>> getAllServices() {
        List<Service> services = serviceService.getAllServices();
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    /**
     * Endpoint to get a specific service by its ID.
     * @param id The ID of the service.
     * @return The service if found (200 OK), or a 404 Not Found response.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a service by ID")
    public ResponseEntity<Service> getServiceById(@PathVariable String id) {
        return serviceService.getServiceById(id)
                .map(service -> new ResponseEntity<>(service, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Endpoint to update an existing service.
     * @param id The ID of the service to update.
     * @param serviceDetails The updated service data.
     * @return The updated service (200 OK), or a 404 Not Found response.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update a service")
    public ResponseEntity<Service> updateService(@PathVariable String id, @Valid @RequestBody Service serviceDetails) {
        Service updatedService = serviceService.updateService(id, serviceDetails);
        if (updatedService != null) {
            return new ResponseEntity<>(updatedService, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint to delete a service.
     * @param id The ID of the service to delete.
     * @return A 204 No Content response.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a service")
    public ResponseEntity<HttpStatus> deleteService(@PathVariable String id) {
        // The service layer handles the logic of checking tenant ownership before deleting
        serviceService.deleteService(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
