package com.saas.sales_setup.mapper;

import com.saas.sales_setup.dto.LookupRequestDTO;
import com.saas.sales_setup.dto.LookupResponseDTO;
import com.saas.sales_setup.model.Lookup;
import java.util.List;

// Change 'class' to 'interface'
public interface LookupMapper {

    LookupResponseDTO toResponseDto(Lookup lookup);

    List<LookupResponseDTO> toResponseDtoList(List<Lookup> lookups);

    Lookup toEntity(LookupRequestDTO dto);
}