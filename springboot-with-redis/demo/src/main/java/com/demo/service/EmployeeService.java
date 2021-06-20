package com.demo.service;

import java.util.Map;

import com.demo.model.Employee;

public interface EmployeeService {

	void save(Employee employee);

	Employee findById(String id);

	Map<String, Employee> findAll();

	void delete(String id);
}
