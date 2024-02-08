package com.xische.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillDetails {

    private Long billNo;
    private User customerDetails;
    private LocalDateTime createdAt;
    private List<ProductDetails> productDetailsList;
    private String billType;
}
