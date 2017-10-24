package com.karczewski.manager.repositories;

import com.karczewski.manager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findEmployeeByFirstName(String firstName);
    List<Employee> findEmployeeByLastName(String lastName);

}
