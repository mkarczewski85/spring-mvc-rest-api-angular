package com.karczewski.manager.controller;


import com.karczewski.manager.model.Employee;
import com.karczewski.manager.service.EmployeeService;
import com.karczewski.manager.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {

    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    EmployeeService employeeService;

    // ------------------- Retrieve all employees -------------------------------------------------
    @RequestMapping(value = "/employee/", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> listAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        if (employees.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    // ------------------- Retrieve single employee by id ------------------------------------------
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") int id) {
        logger.info("Fetching User with id: {}", id);
        Employee employee = employeeService.findEmployeeById(id);
        if (employee == null) {
            logger.error("Employee with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Employee with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    // ------------------- Retrieve list of employees by first name-----------------------------------
    @RequestMapping(value = "/employee/{firstName}", method = RequestMethod.GET)
    public ResponseEntity<?> getEmployeeByFirstName(@PathVariable("firstName") String firstName) {
        logger.info("Fetching employees with name: {}", firstName);
        List<Employee> employees = employeeService.findEmployeesByFirstName(firstName);
        if (employees.isEmpty()) {
            logger.error("Employee with name {} not found.", firstName);
            return new ResponseEntity(new CustomErrorType("Employee with name " + firstName
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    // ------------------- Retrieve list of employees by last name------------------------------------
    @RequestMapping(value = "/employee/{lastName}", method = RequestMethod.GET)
    public ResponseEntity<?> getEmployeeByLastName(@PathVariable("firstName") String lastName) {
        logger.info("Fetching employees with last name: {}", lastName);
        List<Employee> employees = employeeService.findEmployeesByLastName(lastName);
        if (employees.isEmpty()) {
            logger.error("Employee with last name {} not found.", lastName);
            return new ResponseEntity(new CustomErrorType("Employee with last name " + lastName
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    // ------------------- Create and save employee---------------------------------------------------
    @RequestMapping(value = "/employee/", method = RequestMethod.POST)
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee, UriComponentsBuilder ucBuilder) {
        logger.info("Creating employee : {}", employee);

        if (employeeService.isEmployeeExists(employee)) {
            logger.error("Unable to create. An employee with name {} already exist", employee.getLastName());
            return new ResponseEntity(new CustomErrorType("Unable to create. An employee with name " +
                    employee.getLastName() + " already exist."), HttpStatus.CONFLICT);
        }
        employeeService.saveOrUpdateEmployee(employee);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/employee/{id}").buildAndExpand(employee.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update employee -----------------------------------------------------------
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee) {
        logger.info("Updating User with id {}", id);

        Employee currentEmployee = employeeService.findEmployeeById(id);

        if (currentEmployee == null) {
            logger.error("Unable to update. Employee with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. Employee with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentEmployee.setFirstName(employee.getFirstName());
        currentEmployee.setLastName(employee.getLastName());
        currentEmployee.setPosition(employee.getPosition());
        currentEmployee.setSalary(employee.getSalary());
        currentEmployee.setBirthDate(employee.getBirthDate());
        currentEmployee.setEmploymentDate(employee.getEmploymentDate());
        currentEmployee.setAddress(employee.getAddress());
        currentEmployee.setEmail(employee.getEmail());
        currentEmployee.setPhoto(employee.getPhoto());

        employeeService.saveOrUpdateEmployee(currentEmployee);

        return new ResponseEntity<Employee>(currentEmployee, HttpStatus.OK);
    }

    // ------------------- Delete employee -----------------------------------------------------------
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") int id) {
        logger.info("Fetching & Deleting employee with id {}", id);

        Employee employee = employeeService.findEmployeeById(id);
        if (employee == null) {
            logger.error("Unable to delete. Employee with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Employee with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
    }

}
