package com.springboot.mongo.cache.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.springboot.mongo.cache.rest.dao.Mydaorepository;
import com.springboot.mongo.cache.rest.model.Employee;

@Service
public class Myserviceimpl implements Myservice {

	@Autowired
	Mydaorepository dao;

	@Cacheable(value = "users")
	@Override
	public List<Employee> getEmployees() {
		System.out.println("Get All Employyes Method executing");
		return dao.findAll();
	}
	@Cacheable(value = "users", keyGenerator = "customKeyGenerator")
	@Override
	public Optional<Employee> getEmployeeById(int empid) {
		return dao.findById(empid);
	}
	@Override
	public Employee addNewEmployee(Employee emp) {
		return dao.save(emp);
	}
	@Override
	public Employee updateEmployee(Employee emp) {
		return dao.save(emp);
	}
	@Override
	public void deleteEmployeeById(int empid) {
		dao.deleteById(empid);
	}
	@Override
	public void deleteAllEmployees() {
		dao.deleteAll();
	}
}