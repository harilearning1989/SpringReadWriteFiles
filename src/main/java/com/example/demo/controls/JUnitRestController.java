package com.example.demo.controls;

import java.time.LocalDate;

import com.example.demo.service.JUnitRestService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/junit")
@Api(value = "JUnitRestController")
public class JUnitRestController {

    @Autowired
    private JUnitRestService jUnitRestService;

    @PostMapping("/dayOfWeek")
    public String getDayOfWeek(@RequestBody String birthdayString) {
        LocalDate birthday = jUnitRestService.getValidBirthday(birthdayString);
        String dow = jUnitRestService.getBirthDOW(birthday);
        return dow;
    }

    @PostMapping("/chineseZodiac")
    public String getChineseZodiac(@RequestBody String birthdayString) {
        LocalDate birthday = jUnitRestService.getValidBirthday(birthdayString);
        String sign = jUnitRestService.getChineseZodiac(birthday);
        return sign;
    }

    @PostMapping("/starSign")
    public String getStarSign(@RequestBody String birthdayString) {
        LocalDate birthday = jUnitRestService.getValidBirthday(birthdayString);
        String sign = jUnitRestService.getStarSign(birthday);
        return sign;
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
        return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
