package com.example.demo;

import com.example.demo.service.JUnitRestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class JUnitServiceTest {

    @Autowired
    private JUnitRestService jUnitRestService;

    @Test
    void testGetBirthdayDOW() {
        String dow = jUnitRestService.getBirthDOW(LocalDate.of(1979, 7, 14));
        assertEquals("SATURDAY", dow);
        dow = jUnitRestService.getBirthDOW(LocalDate.of(2018, 1, 23));
        assertEquals("TUESDAY", dow);
        dow = jUnitRestService.getBirthDOW(LocalDate.of(1972, 3, 17));
        assertEquals("FRIDAY", dow);
        dow = jUnitRestService.getBirthDOW(LocalDate.of(1945, 12, 2));
        assertEquals("SUNDAY", dow);
        dow = jUnitRestService.getBirthDOW(LocalDate.of(2003, 8, 4));
        assertEquals("MONDAY", dow);
    }

    @Test
    void testGetBirthdayChineseSign() {
        String dow = jUnitRestService.getChineseZodiac(LocalDate.of(1979, 7, 14));
        assertEquals("Sheep", dow);
        dow = jUnitRestService.getChineseZodiac(LocalDate.of(2018, 1, 23));
        assertEquals("Dog", dow);
        dow = jUnitRestService.getChineseZodiac(LocalDate.of(1972, 3, 17));
        assertEquals("Rat", dow);
        dow = jUnitRestService.getChineseZodiac(LocalDate.of(1945, 12, 2));
        assertEquals("Rooster", dow);
        dow = jUnitRestService.getChineseZodiac(LocalDate.of(2003, 8, 4));
        assertEquals("Sheep", dow);
    }

    @Test
    void testGetBirthdayStarSign() {
        String dow = jUnitRestService.getStarSign(LocalDate.of(1979, 7, 14));
        assertEquals("Cancer", dow);
        dow = jUnitRestService.getStarSign(LocalDate.of(2018, 1, 23));
        assertEquals("Aquarius", dow);
        dow = jUnitRestService.getStarSign(LocalDate.of(1972, 3, 17));
        assertEquals("Pisces", dow);
        dow = jUnitRestService.getStarSign(LocalDate.of(1945, 12, 2));
        assertEquals("Sagittarius", dow);
        dow = jUnitRestService.getStarSign(LocalDate.of(2003, 8, 4));
        assertEquals("Leo", dow);
    }
}
