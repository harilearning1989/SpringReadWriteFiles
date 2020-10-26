package com.example.demo.controls;

import com.example.demo.xml.Country;
import com.example.demo.xml.Student;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("xml")
@Api(value = "XMLReadRestController")
public class XMLReadRestController {

    @ApiOperation(value = "readUserXML")
    @ApiResponses(value = {
            @ApiResponse(code = 100,message = "100 Message"),
            @ApiResponse(code = 200,message = "200 Success Message")
    })
    @RequestMapping(value = "userXML", method = RequestMethod.GET)
    public List<Student> readUserXML() {
        List<Student> studentList = new ArrayList<>();
        try {
            //File file = new File("D:\\Workspace\\IntelliJ\\SpringReadWriteFiles\\src\\main\\resources\\xml\\users.xml");
            File file = new File(getClass().getResource("/xml/users.xml").getFile());
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("student");
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    System.out.println("Student id: " + eElement.getElementsByTagName("id").item(0).getTextContent());
                    System.out.println("First Name: " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
                    System.out.println("Last Name: " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
                    System.out.println("Subject: " + eElement.getElementsByTagName("subject").item(0).getTextContent());
                    System.out.println("Marks: " + eElement.getElementsByTagName("marks").item(0).getTextContent());
                    studentList.add(new Student(eElement.getElementsByTagName("id").item(0).getTextContent(),
                            eElement.getElementsByTagName("firstname").item(0).getTextContent(),
                            eElement.getElementsByTagName("lastname").item(0).getTextContent(),
                            eElement.getElementsByTagName("subject").item(0).getTextContent(),
                            eElement.getElementsByTagName("marks").item(0).getTextContent()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    @ApiOperation(value = "readXML")
    @ApiResponses(value = {
            @ApiResponse(code = 100,message = "100 Message"),
            @ApiResponse(code = 200,message = "200 Success Message")
    })
    @RequestMapping(value = "readXml", method = RequestMethod.GET)
    public void readXML() throws JsonParseException, JsonMappingException, IOException {
        InputStream is = JacksonXmlText.class.getResourceAsStream("/countries.xml");
        XmlMapper xmlMapper = new XmlMapper();
        List<Country> countries = xmlMapper.readValue(is, new TypeReference<List<Country>>() {
        });
        Map<String, Country> nameToCountry = countries.stream()
                .collect(Collectors.toMap(Country::getName, Function.identity()));
        System.out.println(nameToCountry.get("England").getRegion());
    }
}
