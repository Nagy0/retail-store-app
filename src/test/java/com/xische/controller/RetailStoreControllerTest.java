package com.xische.controller;

import com.xische.domain.BillDetails;
import com.xische.domain.ProductDetails;
import com.xische.domain.User;
import com.xische.domain.UserType;
import com.xische.service.RetailStoreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RetailStoreControllerTest {

    private static final String GROCERIES_BILL_TYPE = "groceries";
    private static final String ELECTRONICS_BILL_TYPE = "electronics";

    @Mock
    private RetailStoreService retailStoreServiceMock;

    @InjectMocks
    private RetailStoreController retailStoreControllerMock;

    @Test
    public void shouldCalculateTotalBillAmountForNewCustomer() {
        final User user = User.builder().userType(UserType.NEW).storeJoinDate(LocalDateTime.now()).build();
        final ProductDetails productDetails = ProductDetails.builder()
                .quantity(1)
                .unitPrice(50.0)
                .totalAmount(50.0)
                .build();

        final List<ProductDetails> productDetailsList = new ArrayList<>();
        productDetailsList.add(productDetails);

        final BillDetails billDetails = BillDetails.builder()
                .billNo(12578L)
                .billType(ELECTRONICS_BILL_TYPE)
                .createdAt(LocalDateTime.now())
                .customerDetails(user)
                .productDetailsList(productDetailsList)
                .build();

        when(retailStoreServiceMock.calculateTotalPayableAmount(billDetails)).thenReturn(50.0);

        final ResponseEntity<Double> responseEntity = retailStoreControllerMock.calculateTotalPayableAmount(billDetails);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(responseEntity.getBody(), 50.0);
    }
}
