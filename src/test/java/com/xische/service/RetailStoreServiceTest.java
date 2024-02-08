package com.xische.service;


import com.xische.domain.BillDetails;
import com.xische.domain.ProductDetails;
import com.xische.domain.User;
import com.xische.domain.UserType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RetailStoreServiceTest {

    private static final String GROCERIES_BILL_TYPE = "groceries";
    private static final String ELECTRONICS_BILL_TYPE = "electronics";

    @Autowired
    private RetailStoreService retailStoreService;

    @Test
    @DisplayName("Should calculate total bill amount for a new customer.")
    public void shouldCalculateTotalPayableAmountForNewCustomer() {
        final User user = User.builder().userType(UserType.NEW).storeJoinDate(LocalDateTime.now()).build();
        final ProductDetails productDetails = ProductDetails.builder()
                .quantity(2)
                .unitPrice(100.0)
                .totalAmount(200.0)
                .build();

        final List<ProductDetails> productDetailsList = new ArrayList<>();
        productDetailsList.add(productDetails);

        final BillDetails billDetails = BillDetails.builder()
                .billNo(123L)
                .billType(GROCERIES_BILL_TYPE)
                .createdAt(LocalDateTime.now())
                .customerDetails(user)
                .productDetailsList(productDetailsList)
                .build();

        final Double totalPayableAmount = retailStoreService.calculateTotalPayableAmount(billDetails);

        assertEquals(totalPayableAmount, 200.0);
    }

    @Test
    @DisplayName("Should calculate total amount for a new customer with bill amount less than 100$.")
    public void shouldCalculateTotalBillAmountForNewCustomerWithBillLessThan100() {
        final User user = User.builder().userType(UserType.NEW).storeJoinDate(LocalDateTime.now()).build();
        final ProductDetails productDetails = ProductDetails.builder()
                .quantity(1)
                .unitPrice(50.0)
                .totalAmount(50.0)
                .build();

        final List<ProductDetails> productDetailsList = new ArrayList<>();
        productDetailsList.add(productDetails);

        final BillDetails billDetails = BillDetails.builder()
                .billNo(65165189L)
                .billType(ELECTRONICS_BILL_TYPE)
                .createdAt(LocalDateTime.now())
                .customerDetails(user)
                .productDetailsList(productDetailsList)
                .build();

        final Double totalPayableAmount = retailStoreService.calculateTotalPayableAmount(billDetails);

        assertEquals(totalPayableAmount, 50.0);
    }
    @Test
    @DisplayName("Should calculate total bill amount for an employee of the retails store.")
    public void shouldCalculateTotalBillAmountForEmployeeOfTheStore() {
        final User user = User.builder().userType(UserType.EMPLOYEE)
                .storeJoinDate(LocalDateTime.of(2023, 10, 15, 8, 00))
                .build();
        final ProductDetails productDetails1 = ProductDetails.builder()
                .quantity(2)
                .unitPrice(100.0)
                .totalAmount(200.0)
                .build();

        final ProductDetails productDetails2 = ProductDetails.builder()
                .quantity(3)
                .unitPrice(100.0)
                .totalAmount(300.0)
                .build();

        final List<ProductDetails> productDetailsList = new ArrayList<>();
        productDetailsList.add(productDetails1);
        productDetailsList.add(productDetails2);


        final BillDetails billDetails = BillDetails.builder()
                .billNo(456L)
                .billType(ELECTRONICS_BILL_TYPE)
                .createdAt(LocalDateTime.now())
                .customerDetails(user)
                .productDetailsList(productDetailsList)
                .build();

        final Double totalPayableAmount = retailStoreService.calculateTotalPayableAmount(billDetails);

        assertEquals(totalPayableAmount, 350.0);
    }

    @Test
    public void shouldCalculateTotalBillAmountForAffiliateUser() {
        final User user = User.builder().userType(UserType.AFFILIATE)
                .storeJoinDate(LocalDateTime.of(2023, 5, 15, 8, 00))
                .build();
        final ProductDetails productDetails1 = ProductDetails.builder()
                .quantity(2)
                .unitPrice(100.0)
                .totalAmount(200.0)
                .build();

        final ProductDetails productDetails2 = ProductDetails.builder()
                .quantity(3)
                .unitPrice(100.0)
                .totalAmount(300.0)
                .build();

        final List<ProductDetails> productDetailsList = new ArrayList<>();
        productDetailsList.add(productDetails1);
        productDetailsList.add(productDetails2);


        final BillDetails billDetails = BillDetails.builder()
                .billNo(59595L)
                .billType(ELECTRONICS_BILL_TYPE)
                .createdAt(LocalDateTime.now())
                .customerDetails(user)
                .productDetailsList(productDetailsList)
                .build();

        final Double totalPayableAmount = retailStoreService.calculateTotalPayableAmount(billDetails);

        assertEquals(totalPayableAmount, 450.0);
    }

    @Test
    @DisplayName("Should calculate total amount for a customer with join date over 2 years.")
    public void shouldCalculateTotalBillAmountForCustomerWithJoiningDateOverTwoYears() {
        final User user = User.builder().userType(UserType.CUSTOMER)
                .storeJoinDate(LocalDateTime.of(2021, 5, 15, 8, 00))
                .build();
        final ProductDetails productDetails1 = ProductDetails.builder()
                .quantity(2)
                .unitPrice(100.0)
                .totalAmount(200.0)
                .build();

        final ProductDetails productDetails2 = ProductDetails.builder()
                .quantity(3)
                .unitPrice(100.0)
                .totalAmount(300.0)
                .build();

        final List<ProductDetails> productDetailsList = new ArrayList<>();
        productDetailsList.add(productDetails1);
        productDetailsList.add(productDetails2);

        final BillDetails billDetails = BillDetails.builder()
                .billNo(9995L)
                .billType(ELECTRONICS_BILL_TYPE)
                .createdAt(LocalDateTime.now())
                .customerDetails(user)
                .productDetailsList(productDetailsList)
                .build();

        final Double totalPayableAmount = retailStoreService.calculateTotalPayableAmount(billDetails);

        assertEquals(totalPayableAmount, 475.0);
    }

    @Test
    @DisplayName("Should calculate total amount for a customer with join date less than 2 years.")
    public void shouldCalculateTotalBillAmountForCustomerWithJoinDateLessThanTwoYears() {
        final User user = User.builder().userType(UserType.CUSTOMER)
                .storeJoinDate(LocalDateTime.of(2023, 5, 15, 8, 00))
                .build();

        final ProductDetails productDetails2 = ProductDetails.builder()
                .quantity(3)
                .unitPrice(100.0)
                .totalAmount(300.0)
                .build();

        final List<ProductDetails> productDetailsList = new ArrayList<>();
        productDetailsList.add(productDetails2);

        final BillDetails billDetails = BillDetails.builder()
                .billNo(6518L)
                .billType(ELECTRONICS_BILL_TYPE)
                .createdAt(LocalDateTime.now())
                .customerDetails(user)
                .productDetailsList(productDetailsList)
                .build();

        final Double totalPayableAmount = retailStoreService.calculateTotalPayableAmount(billDetails);

        assertEquals(totalPayableAmount, 285.0);
    }



}
