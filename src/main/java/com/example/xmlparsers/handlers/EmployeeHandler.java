package com.example.xmlparsers.handlers;

import com.example.xmlparsers.model.Employee;
import com.example.xmlparsers.model.Employees;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class EmployeeHandler extends DefaultHandler {
    private static final String EMPLOYEES = "Employees";
    private static final String EMPLOYEE = "Employee";
    private static final String FIRSTNAME = "Firstname";
    private static final String LASTNAME = "Lastname";
    private static final String AGE = "Age";
    private static final String SALARY = "Salary";

    private Employees employees;
    private StringBuilder elementValue;


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (elementValue == null) {
            elementValue = new StringBuilder();
        } else {
            elementValue.append(ch, start, length);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        employees = new Employees();
    }

    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr) throws SAXException {
        switch (qName) {
            case EMPLOYEES:
                employees.setEmployeeList(new ArrayList<>());
                break;
                case EMPLOYEE:
                employees.getEmployeeList().add(new Employee());
                break;
            case SALARY,FIRSTNAME, LASTNAME, AGE:
                elementValue = new StringBuilder();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case SALARY:
                latestEmployee().setSalary(elementValue.toString());
                break;
            case FIRSTNAME:
                latestEmployee().setFirstname(elementValue.toString());
                break;
             case LASTNAME:
                latestEmployee().setLastname(elementValue.toString());
                break;
             case AGE:
                latestEmployee().setAge(elementValue.toString());
                break;

        }
    }

    private Employee latestEmployee() {
        List<Employee> employeeList = employees.getEmployeeList();
        int latestEmployeeIndex = employeeList.size() - 1;
        return employeeList.get(latestEmployeeIndex);
    }

    public Employees getEmployees() {
        return employees;
    }

}
