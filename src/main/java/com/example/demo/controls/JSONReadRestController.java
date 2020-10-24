package com.example.demo.controls;

import com.example.demo.data.IStaticData;
import com.example.demo.json.*;
import com.example.demo.utils.IDemoUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@RestController
@RequestMapping("rest")
public class JSONReadRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSONReadRestController.class);
    private static final String JSON_FILE_LOCATION = "json/";

    @GetMapping(value = "/hello")
    public String helloWorld() {
        LOGGER.info("Hello World Logger");
        return "Hello World";
    }

    @PostMapping(value = "/countryCurrency")
    public List<CountryCurrency> postCountryCurrency(@RequestParam("file") MultipartFile file) {
        List<CountryCurrency> countryRegion = null;
        String content = IDemoUtils.getBytesFromMultipartFile(file);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            countryRegion = objectMapper.readValue(content,
                    objectMapper.getTypeFactory()
                            .constructCollectionType(List.class, CountryCurrency.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return countryRegion;
    }

    @GetMapping(value = "/user")
    public User readUserJson() {
        User user = null;
        try {
            String fixture = IDemoUtils.readResource(JSON_FILE_LOCATION + "users.json", StandardCharsets.UTF_8);
            //String fixture = IDemoUtils.readResource(JSON_FILE_LOCATION + "users.json", Charsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            user = objectMapper.readValue(fixture, User.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @GetMapping(value = "/countryCurrency")
    public List<CountryCurrency> getCountryCurrency() {
        List<CountryCurrency> countryRegion = null;
        try {
            String fixture = IDemoUtils.readResource(JSON_FILE_LOCATION + "CountryCurrency.json", Charsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            countryRegion = objectMapper.readValue(fixture, objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, CountryCurrency.class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return countryRegion;
    }

    @GetMapping(value = "/countryCode")
    public List<CountriesCode> getCountriesCodes() {
        List<CountriesCode> countryRegion = null;
        try {
            String fixture = IDemoUtils.readResource(JSON_FILE_LOCATION + "CountryCode.json", Charsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            countryRegion = objectMapper.readValue(fixture, objectMapper.getTypeFactory().constructCollectionType(List.class, CountriesCode.class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return countryRegion;
    }

    @GetMapping(value = "/countries")
    public List<Countries> getCountries() {
        List<Countries> countryRegion = null;
        try {
            String fixture = IDemoUtils.readResource(JSON_FILE_LOCATION + "Countries.json", Charsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            countryRegion = objectMapper.readValue(fixture, objectMapper.getTypeFactory().constructCollectionType(List.class, Countries.class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return countryRegion;
    }

    @GetMapping(value = "/all")
    public AllCountriesRegion GetAllRegions() {
        AllCountriesRegion countryRegion = null;
        try {
            String fixture = IDemoUtils.readResource(JSON_FILE_LOCATION + "all.json", Charsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            countryRegion = objectMapper.readValue(fixture, AllCountriesRegion.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return countryRegion;
    }

    @GetMapping(value = "/countryState")
    public List<CountryState> getTheCountry() {
        List<CountryState> countryRegion = null;
        try {
            String fixture = IDemoUtils.readResource(JSON_FILE_LOCATION + "CountryState.json", Charsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            countryRegion = objectMapper.readValue(fixture, objectMapper.getTypeFactory().constructCollectionType(List.class, CountryState.class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return countryRegion;
    }

    @GetMapping(value = "/allRegionCountiesByRegion")
    public Set<String> getCountries(@RequestParam(value = "region") String region) {
        List<AllCountries> countryRegion = null;
        Set<String> countries = new TreeSet<>();
        try {
            String fixture = IDemoUtils.readResource(JSON_FILE_LOCATION + "allRegionCounties.json", Charsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            countryRegion = objectMapper.readValue(fixture, objectMapper.getTypeFactory().constructCollectionType(List.class, AllCountries.class));
            for (AllCountries all : countryRegion) {
                if (!all.getRegion().isEmpty() && region.equalsIgnoreCase(all.getRegion())) {
                    countries.add(all.getName());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return countries;
    }

    @GetMapping(value = "/allRegionCountiesRegion")
    public Set<String> getTheRegions() {
        List<AllCountries> countryRegion = null;
        Set<String> regions = new TreeSet<>();
        try {
            String fixture = IDemoUtils.readResource(JSON_FILE_LOCATION + "allRegionCounties.json", Charsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            countryRegion = objectMapper.readValue(fixture, objectMapper.getTypeFactory().constructCollectionType(List.class, AllCountries.class));
            for (AllCountries all : countryRegion) {
                regions.add(all.getRegion());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return regions;
    }

    @GetMapping(value = "/allRegionCounties")
    public List<AllCountries> allCountriesData() {
        List<AllCountries> countryRegion = null;
        try {
            String fixture = IDemoUtils.readResource(JSON_FILE_LOCATION + "allRegionCounties.json", Charsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            countryRegion = objectMapper.readValue(fixture, objectMapper.getTypeFactory().constructCollectionType(List.class, AllCountries.class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return countryRegion;
    }


    @GetMapping(value = "/countryStates")
    public List<CountryStates> countryStates() {
        List<CountryStates> countryRegion = null;
        try {
            String fixture = IDemoUtils.readResource(JSON_FILE_LOCATION + "CountryStates.json", Charsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            countryRegion = objectMapper.readValue(fixture, objectMapper.getTypeFactory().constructCollectionType(List.class, CountryStates.class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return countryRegion;
    }

    @GetMapping(value = "/covidData")
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

    @GetMapping(value = "/address")
    public CompleteAddress getAddress() {
        ObjectMapper objectMapper = new ObjectMapper();
        CompleteAddress address = null;
        try {
            address = objectMapper.readValue(IStaticData.JSONDATA, CompleteAddress.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
    }
}