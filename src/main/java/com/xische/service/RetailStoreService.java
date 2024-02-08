package com.xische.service;

import com.xische.domain.BillDetails;
import com.xische.domain.ProductDetails;
import com.xische.domain.UserType;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class RetailStoreService {

    private static final String GROCERIES = "groceries";

    public Double calculateTotalPayableAmount(final BillDetails billDetails) {
        if (GROCERIES.equalsIgnoreCase(billDetails.getBillType())) {
            return getTotalPayableAmount(billDetails);
        } else {
            final UserType userType = billDetails.getCustomerDetails().getUserType();
            Double totalPayableAmount = getTotalPayableAmount(billDetails);
            if (UserType.EMPLOYEE == userType) {
                return calculateDiscount(30, totalPayableAmount);
            } else if (UserType.AFFILIATE == userType) {
                return calculateDiscount(10, totalPayableAmount);
            } else if (UserType.CUSTOMER == userType) {
                final LocalDateTime storeJoinDate = billDetails.getCustomerDetails().getStoreJoinDate();
                long years = ChronoUnit.YEARS.between(storeJoinDate, LocalDateTime.now());
                if (years >= 2) {
                    return calculateDiscount(5, totalPayableAmount);
                }
            }
            if (totalPayableAmount > 100) {
               return calculateDiscountForEveryOneHundred(totalPayableAmount);
            }
            return totalPayableAmount;
        }
    }

    private Double getTotalPayableAmount(final BillDetails billDetails) {
        Double totalPayableAmount = 0.0;
        for (final ProductDetails productDetails : billDetails.getProductDetailsList()) {
            totalPayableAmount += productDetails.getTotalAmount();
        }
        return totalPayableAmount;
    }

    private Double calculateDiscount(final int percentage, final Double totalAmount) {
        Double discount =  (percentage * totalAmount) / 100;
        return totalAmount - discount;
    }

    private Double calculateDiscountForEveryOneHundred(Double totalAmount) {
        int discount = (int) (totalAmount / 100);
        totalAmount -= discount * 5;
        return totalAmount;
    }
}
