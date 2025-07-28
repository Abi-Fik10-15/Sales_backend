package com.saas.sales_setup.service;

import com.saas.sales_setup.config.TenantContext;
import com.saas.sales_setup.dto.LookupRequestDTO;
import com.saas.sales_setup.dto.LookupResponseDTO;
import com.saas.sales_setup.mapper.LookupMapper;
import com.saas.sales_setup.model.Lookup;
import com.saas.sales_setup.model.LookupType;
import com.saas.sales_setup.repository.LookupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LookupServiceImpl implements LookupService {

    @Autowired
    private LookupRepository lookupRepository;

    @Autowired
    private LookupMapper lookupMapper;

    @Override
    public List<LookupResponseDTO> getLookupsByType(LookupType type) {
        String tenantId = TenantContext.getCurrentTenant();
        if (tenantId == null) {
            throw new IllegalStateException("Tenant ID is missing from the request header (X-Tenant-ID).");
        }
        List<Lookup> lookups = lookupRepository.findByTenantIdAndTypeAndActiveTrueOrderBySortOrderAsc(tenantId, type);
        return lookupMapper.toResponseDtoList(lookups);
    }

    @Override
    public Lookup createLookup(LookupRequestDTO dto) {
        Lookup lookup = lookupMapper.toEntity(dto);
        lookup.setTenantId(TenantContext.getCurrentTenant());
        return lookupRepository.save(lookup);
    }
}