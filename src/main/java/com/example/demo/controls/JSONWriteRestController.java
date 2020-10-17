package com.example.demo.controls;

import com.example.demo.json.CountryCurrency;
import com.example.demo.json.Customer;
import com.example.demo.utils.IDemoUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("json/write")
public class JSONWriteRestController {

    @PostMapping(path = "/save-cust")
    public String customerInformation(@RequestBody Customer cust) {
        return "Customer information saved successfully ::." + cust.getCustNo() + " " + cust.getName() + " " + cust.getCountry();
    }
}
