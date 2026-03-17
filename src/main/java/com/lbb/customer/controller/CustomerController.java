package com.lbb.customer.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private static final Logger logger = LogManager.getLogger(CustomerController.class);

    @GetMapping("/hello")
    public ResponseEntity<String> getFullPath() {
        // ສ້າງ Full Path ເພື່ອສົ່ງໃຫ້ Client ຫຼື ເອົາໄປໃຊ້ຕໍ່
        String fullPath  = "hello customer servcie ";
        return ResponseEntity.ok(fullPath);
    }

}
