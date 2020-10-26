package com.example.demo.controls;

import com.example.demo.excel.Product;
import com.example.demo.excel.UserExcel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("excel")
@Api(value = "ExcelReadRestController")
public class ExcelReadRestController {

    @ApiOperation(value = "Returns Student Details")
    @ApiResponses(value = {
            @ApiResponse(code = 100,message = "100 Message"),
            @ApiResponse(code = 200,message = "200 Success Message")
    })
    @PostMapping("/import")
    public List<UserExcel> mapReapExcelDatatoDB(@RequestParam("file") MultipartFile reapExcelDataFile) throws IOException {
        List<UserExcel> tempStudentList = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            UserExcel tempStudent = new UserExcel();
            XSSFRow row = worksheet.getRow(i);
            tempStudent.setId((int) row.getCell(0).getNumericCellValue());
            tempStudent.setContent(row.getCell(1).getStringCellValue());
            tempStudentList.add(tempStudent);
        }
        return tempStudentList;
    }

    @RequestMapping(value = "/import-excel", method = RequestMethod.POST)
    public ResponseEntity<List<Product>> importExcelFile(@RequestParam("file") MultipartFile files) throws IOException {
        HttpStatus status = HttpStatus.OK;
        List<Product> productList = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                Product product = new Product();
                XSSFRow row = worksheet.getRow(index);
                Integer id = (int) row.getCell(0).getNumericCellValue();
                product.setId(id.toString());
                product.setProductName(row.getCell(1).getStringCellValue());
                product.setPrice(row.getCell(2).getNumericCellValue());
                product.setCategory(row.getCell(3).getStringCellValue());
                productList.add(product);
            }
        }
        return new ResponseEntity<>(productList, status);
    }

}
