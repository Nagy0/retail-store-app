package com.xische.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetails {

    private String description;
    private Integer quantity;
    private Double unitPrice;
    private Double totalAmount;
}
