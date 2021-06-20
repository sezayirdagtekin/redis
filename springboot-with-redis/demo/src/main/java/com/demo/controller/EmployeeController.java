package com.demo.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Employee;
import com.demo.service.EmployeeService;



@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeService service;

	@PostMapping("/save")
	public String save(@RequestBody final Employee employee) {
		log.info("Saving the new employee to the redis...");
		service.save(employee);
		return "Successfully added. Employee with id= " + employee.getId();
	}

	@GetMapping("/all")
	public Map<String, Employee> findAll() {
		log.info("Fetching all employees from the redis...");
		final Map<String, Employee> employeeMap = service.findAll();
		return employeeMap;
	}

	@GetMapping("/get/{id}")
	public Employee findById(@PathVariable("id") final String id) {
		log.info("Fetching employee with id= " + id);
		return service.findById(id);
	}


	@DeleteMapping("/delete/{id}")
	public Map<String, Employee> delete(@PathVariable("id") final String id) {
		log.info("Deleting employee with id= " + id);
		service.delete(id);
		// Returning the all employees (post the deleted one).
		return findAll();
	}
}
