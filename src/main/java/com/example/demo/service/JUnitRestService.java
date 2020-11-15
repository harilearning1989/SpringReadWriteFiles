package com.example.demo.service;

import java.time.LocalDate;

public interface JUnitRestService {
    public LocalDate getValidBirthday(String birthdayString);

    public String getBirthDOW(LocalDate birthday);

    public String getChineseZodiac(LocalDate birthday);

    public String getStarSign(LocalDate birthday);
}
