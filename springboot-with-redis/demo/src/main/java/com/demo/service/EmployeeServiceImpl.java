package com.demo.service;

import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.demo.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final String EMPLOYEE_CACHE = "EMPLOYEE";

	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	private HashOperations<String, String, Employee> hashOperations;

	// This annotation makes sure that the method needs to be executed after
	// dependency injection is done to perform any initialization.
	@PostConstruct
	private void intializeHashOperations() {
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public void save(Employee employee) {
		hashOperations.put(EMPLOYEE_CACHE, employee.getId(), employee);

	}

	@Override
	public Employee findById(String id) {
		return hashOperations.get(EMPLOYEE_CACHE, id);
	}

	@Override
	public Map<String, Employee> findAll() {
		return hashOperations.entries(EMPLOYEE_CACHE);
	}

	@Override
	public void delete(String id) {
		hashOperations.delete(EMPLOYEE_CACHE, id);

	}

}
