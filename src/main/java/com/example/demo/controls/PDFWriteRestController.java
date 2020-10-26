package com.example.demo.controls;

import com.example.demo.json.CountryCurrency;
import com.example.demo.pdf.GeneratePdfReport;
import com.example.demo.utils.IDemoUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("pdfWrite")
@Api(value = "PDFWriteRestController")
public class PDFWriteRestController {

    private static final String JSON_FILE_LOCATION = "json/";

    @ApiOperation(value = "citiesReport")
    @ApiResponses(value = {
            @ApiResponse(code = 100,message = "100 Message"),
            @ApiResponse(code = 200,message = "200 Success Message")
    })
    @RequestMapping(value = "/pdfreport", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> citiesReport() {

        List<CountryCurrency> contryRegion = null;
        try {
            String fixture = IDemoUtils.readResource(JSON_FILE_LOCATION + "CountryCurrency.json", Charsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            contryRegion = objectMapper.readValue(fixture, objectMapper.getTypeFactory().constructCollectionType(List.class, CountryCurrency.class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ByteArrayInputStream bis = GeneratePdfReport.citiesReport(contryRegion);

        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=currencyReport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
