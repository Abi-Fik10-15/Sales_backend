package com.saas.sales_setup.mapper;

import com.saas.sales_setup.dto.LookupRequestDTO;
import com.saas.sales_setup.dto.LookupResponseDTO;
import com.saas.sales_setup.model.Lookup;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LookupMapperImpl implements LookupMapper {

    @Override
    public LookupResponseDTO toResponseDto(Lookup lookup) {
        if (lookup == null) {
            return null;
        }
        LookupResponseDTO dto = new LookupResponseDTO();
        dto.setKey(lookup.getKey());
        dto.setValue(lookup.getValue());
        return dto;
    }

    @Override
    public List<LookupResponseDTO> toResponseDtoList(List<Lookup> lookups) {
        return lookups.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public Lookup toEntity(LookupRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Lookup lookup = new Lookup();
        lookup.setType(dto.getType());
        lookup.setKey(dto.getKey());
        lookup.setValue(dto.getValue());
        lookup.setSortOrder(dto.getSortOrder());
        lookup.setActive(true);
        return lookup;
    }
}