package com.saas.sales_setup.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AddSalesPriceRequestDto {
    private String salesItemId;
    private BigDecimal salesPrice;
    private String taxTypeId;
    private LocalDate effectiveDate;
}