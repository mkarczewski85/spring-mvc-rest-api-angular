package com.karczewski.manager.service;

import com.karczewski.manager.model.Employee;
import com.karczewski.manager.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee findEmployeeById(Integer id) {
        return employeeRepository.findOne(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> findEmployeesByLastName(String lastName) {
        return employeeRepository.findEmployeeByLastName(lastName);
    }

    @Override
    public List<Employee> findEmployeesByFirstName(String firstName) {
        return employeeRepository.findEmployeeByFirstName(firstName);
    }

    @Override
    public boolean saveOrUpdateEmployee(Employee employee) {
        try {
            employeeRepository.save(employee);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteEmployeeById(Integer id) {
        try {
            employeeRepository.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isEmployeeExists(Employee employee) {

        return employeeRepository.exists(employee.getId());

    }

}
