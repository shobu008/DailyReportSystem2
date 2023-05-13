package com.techacademy.service;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import com.techacademy.entity.Employee;
import com.techacademy.repository.EmployeeRepository;


@Service
public class EmployeeService {
    private final EmployeeRepository employeerepository;

    public EmployeeService(EmployeeRepository repository) {
        this.employeerepository = repository;
    }

    public List<Employee> getEmployeeList() {
        // リポジトリのfindAllメソッドを呼び出す
        return employeerepository.findAll();
    }

    public Employee getEmployee(Integer id) {
        return employeerepository.findById(id).get();
    }

    @Transactional
    public Employee saveEmployee(Employee employee) {
        return employeerepository.save(employee);
    }




}
