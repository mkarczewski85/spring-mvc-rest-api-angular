package com.karczewski.manager.service;

import com.karczewski.manager.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee findEmployeeById(Integer id);
    List<Employee> getAllEmployees();
    List<Employee> findEmployeesByLastName(String lastName);
    List<Employee> findEmployeesByFirstName(String firstName);
    boolean saveOrUpdateEmployee(Employee employee);
    boolean deleteEmployeeById(Integer id);
    boolean isEmployeeExists(Employee employee);

}
