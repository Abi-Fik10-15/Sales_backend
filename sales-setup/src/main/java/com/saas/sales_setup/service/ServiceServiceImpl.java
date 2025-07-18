package com.saas.sales_setup.service;

import com.saas.sales_setup.config.TenantContext;
import com.saas.sales_setup.model.Service;
import com.saas.sales_setup.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service; // Removed to avoid ambiguity

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the ServiceService interface.
 * This class contains the core business logic for service management,
 * including tenant isolation and interaction with the repository.
 */
@org.springframework.stereotype.Service // Using fully qualified name to be explicit
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Service createService(Service service) {
        // Retrieve the current tenant's ID from the TenantContext
        String tenantId = TenantContext.getCurrentTenant();
        // Set the tenantId on the service object before saving
        service.setTenantId(tenantId);
        return serviceRepository.save(service);
    }

    @Override
    public List<Service> getAllServices() {
        // Retrieve services only for the current tenant
        String tenantId = TenantContext.getCurrentTenant();
        return serviceRepository.findByTenantId(tenantId);
    }

    @Override
    public Optional<Service> getServiceById(String id) {
        // Use the combined findByIdAndTenantId method for security
        String tenantId = TenantContext.getCurrentTenant();
        return serviceRepository.findByIdAndTenantId(id, tenantId);
    }

    @Override
    public Service updateService(String id, Service serviceDetails) {
        // First, find the existing service to ensure it belongs to the tenant
        Optional<Service> existingServiceOptional = getServiceById(id);

        if (existingServiceOptional.isPresent()) {
            Service existingService = existingServiceOptional.get();
            // Update the fields from the request
            existingService.setServiceName(serviceDetails.getServiceName());
            existingService.setServiceCategory(serviceDetails.getServiceCategory());
            existingService.setUom(serviceDetails.getUom());
            existingService.setTaxType(serviceDetails.getTaxType());
            existingService.setServicePriceDetails(serviceDetails.getServicePriceDetails());
            // The tenantId and id remain unchanged
            return serviceRepository.save(existingService);
        }
        // Return null or throw a custom "NotFoundException"
        return null;
    }

    @Override
    public void deleteService(String id) {
        // Find the service by ID for the current tenant before deleting
        Optional<Service> serviceOptional = getServiceById(id);
        // If it exists, delete it. This prevents deleting data from other tenants.
        serviceOptional.ifPresent(serviceRepository::delete);
    }
}
