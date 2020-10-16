package com.example.demo.controls;

import com.example.demo.constants.IConstants;
import com.example.demo.csv.CSVUser;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("csv")
public class CSVReadRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVReadRestController.class);

    @Value("${csv.read.readCsv}")
    private String load;

    @RequestMapping(value = "load", method = RequestMethod.GET)
    public String readCsv() {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(IConstants.SAMPLE_CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("The Line===" + line);
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return load;
    }

    @RequestMapping(value = "openCSV", method = RequestMethod.GET)
    public String readOpenCSV() {
        //CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();  //skip Header Row
        List<List<String>> records = new ArrayList<List<String>>();
        try (CSVReader csvReader = new CSVReader(new FileReader(IConstants.SAMPLE_CSV_FILE_PATH));) {
            String[] line = null;
            while ((line = csvReader.readNext()) != null) {
                System.out.println("Line is===" + line);
                records.add(Arrays.asList(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return "readOpenCSV";
    }

    @RequestMapping(value = "userCsv", method = RequestMethod.GET)
    public String readUserCsv() {
        try (Reader reader = Files.newBufferedReader(Paths.get(IConstants.USERS_WITH_HEADER));
             CSVReader csvReader = new CSVReader(reader);) {
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                System.out.println("Line ==="+line);
                System.out.println("Name : " + line[0]);
                System.out.println("Email : " + line[1]);
                System.out.println("Phone : " + line[2]);
                System.out.println("Country : " + line[3]);
                System.out.println("==========================");
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return "readUserCsv";
    }

    @RequestMapping(value = "readCSVToMode", method = RequestMethod.GET)
    public String readCSVToMode() {
        try (Reader reader = Files.newBufferedReader(Paths.get(IConstants.USERS_WITH_HEADER));) {
            CsvToBean<CSVUser> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CSVUser.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<CSVUser> csvUserIterator = csvToBean.iterator();

            while (csvUserIterator.hasNext()) {
                CSVUser csvUser = csvUserIterator.next();
                System.out.println("Name : " + csvUser.getName());
                System.out.println("Email : " + csvUser.getEmail());
                System.out.println("PhoneNo : " + csvUser.getPhoneNo());
                System.out.println("Country : " + csvUser.getCountry());
                System.out.println("==========================");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "readCSVToMode";
    }

}
