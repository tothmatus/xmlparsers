package com.example.xmlparsers.parsers;

import com.example.xmlparsers.handlers.EmployeeHandler;
import com.example.xmlparsers.model.EmployeeService;
import com.example.xmlparsers.model.Employees;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

@Controller
@RequiredArgsConstructor
public class SaxParser {
    private final EmployeeService employeeService;

    @EventListener(ApplicationReadyEvent.class)
    public void saxParser() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            EmployeeHandler employeeHandler = new EmployeeHandler();

            saxParser.parse("src/main/java/com/example/xmlparsers/exampleData/employees.xml", employeeHandler);

            Employees result = employeeHandler.getEmployees();
            employeeService.saveAll(result.getEmployeeList());

        }catch (Exception e){throw new RuntimeException(e);}


    }
}
