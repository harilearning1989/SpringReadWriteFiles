package com.example.demo.controls;

import com.example.demo.constants.IConstants;
import com.example.demo.csv.CSVHelper;
import com.example.demo.csv.CSVUser;
import com.example.demo.csv.Tutorial;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("csv")
@Api(value = "CSVReadRestController")
public class CSVReadRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVReadRestController.class);

    @Value("${csv.read.readCsv}")
    private String load;

    @ApiOperation(value = "uploadCSV")
    @ApiResponses(value = {
            @ApiResponse(code = 100,message = "100 Message"),
            @ApiResponse(code = 200,message = "200 Success Message")
    })
    @PostMapping(value = "uploadCSV")
    public List<Tutorial> uploadCSV(@RequestParam("file") MultipartFile file) {
        List<Tutorial> tutorials = null;
        try {
            tutorials = CSVHelper.csvToTutorials(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tutorials;
    }

    @ApiOperation(value = "readCsv")
    @ApiResponses(value = {
            @ApiResponse(code = 100,message = "100 Message"),
            @ApiResponse(code = 200,message = "200 Success Message")
    })
    @GetMapping(value = "load")
    public List<List<String>> readCsv() {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(IConstants.SAMPLE_CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                LOGGER.info(String.format("The Line===%1$s" , line));
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    @ApiOperation(value = "readOpenCSV")
    @ApiResponses(value = {
            @ApiResponse(code = 100,message = "100 Message"),
            @ApiResponse(code = 200,message = "200 Success Message")
    })
    @GetMapping(value = "openCSV")
    public String readOpenCSV() {
        //CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();  //skip Header Row
        List<List<String>> records = new ArrayList<List<String>>();
        try (CSVReader csvReader = new CSVReader(new FileReader(IConstants.SAMPLE_CSV_FILE_PATH));) {
            String[] line = null;
            while ((line = csvReader.readNext()) != null) {
                LOGGER.info(String.format("Line is===%1$s" , line));
                records.add(Arrays.asList(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return "readOpenCSV";
    }

    @ApiOperation(value = "readUserCsv")
    @ApiResponses(value = {
            @ApiResponse(code = 100,message = "100 Message"),
            @ApiResponse(code = 200,message = "200 Success Message")
    })
    @GetMapping(value = "userCsv")
    public String readUserCsv() {
        try (Reader reader = Files.newBufferedReader(Paths.get(IConstants.USERS_WITH_HEADER));
             CSVReader csvReader = new CSVReader(reader);) {
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                LOGGER.info(String.format("Line ===%1$s" , line));
                LOGGER.info(String.format("Name : %1$s" , line[0]));
                LOGGER.info(String.format("Email : %1$s" , line[1]));
                LOGGER.info(String.format("Phone : %1$s" , line[2]));
                LOGGER.info(String.format("Country : %1$s" , line[3]));
                LOGGER.info("==========================");
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return "readUserCsv";
    }

    @ApiOperation(value = "readCSVToMode")
    @ApiResponses(value = {
            @ApiResponse(code = 100,message = "100 Message"),
            @ApiResponse(code = 200,message = "200 Success Message")
    })
    @GetMapping(value = "readCSVToMode")
    public List<CSVUser> readCSVToMode() {
        Iterator<CSVUser> csvUserIterator = null;
        List<CSVUser> usersList = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(IConstants.USERS_WITH_HEADER));) {
            CsvToBean<CSVUser> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CSVUser.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            csvUserIterator = csvToBean.iterator();

            while (csvUserIterator.hasNext()) {
                CSVUser csvUser = csvUserIterator.next();
                LOGGER.info(String.format("Name : %1$s" , csvUser.getName()));
                LOGGER.info(String.format("Email : %1$s" , csvUser.getEmail()));
                LOGGER.info(String.format("PhoneNo : %1$s" , csvUser.getPhoneNo()));
                LOGGER.info(String.format("Country : %1$s" , csvUser.getCountry()));
                LOGGER.info("==========================");
                usersList.add(new CSVUser(csvUser.getName(),csvUser.getEmail(),csvUser.getPhoneNo(),csvUser.getCountry()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usersList;
    }

}
