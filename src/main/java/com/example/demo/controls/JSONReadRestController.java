package com.example.demo.controls;

import com.example.demo.data.IStaticData;
import com.example.demo.json.*;
import com.example.demo.utils.IDemoUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


@RestController
@RequestMapping("rest")
public class JSONReadRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSONReadRestController.class);
    private static final String JSON_FILE_LOCATION = "json/";

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String helloWorld() {
        LOGGER.info("Hello World Logger");
        return "Hello World";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User readUserJson() {
        User user = null;
        try {
            String fixture = IDemoUtils.readResource(JSON_FILE_LOCATION + "users.json", Charsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            user = objectMapper.readValue(fixture, User.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @RequestMapping(value = "/countryCurrency", method = RequestMethod.GET)
    public List<CountryCurrency> getCountryCurrency() {
        List<CountryCurrency> contryRegion = null;
        try {
            String fixture = IDemoUtils.readResource(JSON_FILE_LOCATION + "CountryCurrency.json", Charsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            contryRegion = objectMapper.readValue(fixture, objectMapper.getTypeFactory().constructCollectionType(List.class, CountryCurrency.class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return contryRegion;
    }

    @RequestMapping(value = "/countryCode", method = RequestMethod.GET)
    public List<CountriesCode> getCountriesCodes() {
        List<CountriesCode> contryRegion = null;
        try {
            String fixture = IDemoUtils.readResource(JSON_FILE_LOCATION + "CountryCode.json", Charsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            contryRegion = objectMapper.readValue(fixture, objectMapper.getTypeFactory().constructCollectionType(List.class, CountriesCode.class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return contryRegion;
    }

    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    public List<Countries> getCountries() {
        List<Countries> contryRegion = null;
        try {
            String fixture = IDemoUtils.readResource(JSON_FILE_LOCATION + "Countries.json", Charsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            contryRegion = objectMapper.readValue(fixture, objectMapper.getTypeFactory().constructCollectionType(List.class, Countries.class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return contryRegion;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public AllCountriesRegion GetAll() {
        AllCountriesRegion contryRegion = null;
        try {
            String fixture = IDemoUtils.readResource(JSON_FILE_LOCATION + "all.json", Charsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            contryRegion = objectMapper.readValue(fixture, AllCountriesRegion.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return contryRegion;
    }

    @RequestMapping(value = "/countryState", method = RequestMethod.GET)
    public List<CountryState> getTheCountry() {
        List<CountryState> contryRegion = null;
        try {
            String fixture = IDemoUtils.readResource(JSON_FILE_LOCATION + "CountryState.json", Charsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            contryRegion = objectMapper.readValue(fixture, objectMapper.getTypeFactory().constructCollectionType(List.class, CountryState.class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return contryRegion;
    }

    @RequestMapping(value = "/allRegionCountiesByRegion", method = RequestMethod.GET)
    public Set<String> getCountries(@RequestParam(value = "region") String region) {
        List<AllCountries> contryRegion = null;
        Set<String> countries = new TreeSet<>();
        try {
            String fixture = IDemoUtils.readResource(JSON_FILE_LOCATION + "allRegionCounties.json", Charsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            contryRegion = objectMapper.readValue(fixture, objectMapper.getTypeFactory().constructCollectionType(List.class, AllCountries.class));
            for (AllCountries all : contryRegion) {
                if (!all.getRegion().isEmpty() && region.equalsIgnoreCase(all.getRegion())) {
                    countries.add(all.getName());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return countries;
    }

    @RequestMapping(value = "/allRegionCountiesRegion", method = RequestMethod.GET)
    public Set<String> getTheRegions() {
        List<AllCountries> contryRegion = null;
        Set<String> regions = new TreeSet<>();
        try {
            String fixture = IDemoUtils.readResource(JSON_FILE_LOCATION + "allRegionCounties.json", Charsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            contryRegion = objectMapper.readValue(fixture, objectMapper.getTypeFactory().constructCollectionType(List.class, AllCountries.class));
            for (AllCountries all : contryRegion) {
                regions.add(all.getRegion());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return regions;
    }

    @RequestMapping(value = "/allRegionCounties", method = RequestMethod.GET)
    public List<AllCountries> allCountriesData() {
        List<AllCountries> contryRegion = null;
        try {
            String fixture = IDemoUtils.readResource(JSON_FILE_LOCATION + "allRegionCounties.json", Charsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            contryRegion = objectMapper.readValue(fixture, objectMapper.getTypeFactory().constructCollectionType(List.class, AllCountries.class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return contryRegion;
    }


    @RequestMapping(value = "/countryStates", method = RequestMethod.GET)
    public List<CountryStates> countryStates() {
        List<CountryStates> contryRegion = null;
        try {
            String fixture = IDemoUtils.readResource(JSON_FILE_LOCATION + "CountryStates.json", Charsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            contryRegion = objectMapper.readValue(fixture, objectMapper.getTypeFactory().constructCollectionType(List.class, CountryStates.class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return contryRegion;
    }

    @RequestMapping(value = "/covidData", method = RequestMethod.GET)
    public CovidData getCovidData() {
        CovidData covidData = null;
        try {
            String fixture = IDemoUtils.readResource(JSON_FILE_LOCATION + "CovidData.json", Charsets.UTF_8);

            ObjectMapper objectMapper = new ObjectMapper();
            covidData = objectMapper.readValue(fixture, CovidData.class);
            //ClassLoader classLoader = getClass().getClassLoader();
            //File file = new File(classLoader.getResource("CovidData.json").getFile());
            //byte[] mapData = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return covidData;
    }

    @RequestMapping(value = "/addr", method = RequestMethod.GET)
    public CompleteAddress getAddress() {
        ObjectMapper objectMapper = new ObjectMapper();
        CompleteAddress addr = null;
        try {
            addr = objectMapper.readValue(IStaticData.JSONDATA, CompleteAddress.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addr;
    }
}