package com.xische.controller;

import com.xische.domain.BillDetails;
import com.xische.service.RetailStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/retail-store")
public class RetailStoreController {

    @Autowired
    private RetailStoreService retailStoreService;

    @PostMapping("/calculateTotalPayableAmount")
    public ResponseEntity<Double> calculateTotalPayableAmount(@RequestBody final BillDetails billDetails) {
         return new ResponseEntity<>(retailStoreService.calculateTotalPayableAmount(billDetails), HttpStatus.OK);
    }
}
