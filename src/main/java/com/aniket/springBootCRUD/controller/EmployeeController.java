package com.aniket.springBootCRUD.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aniket.springBootCRUD.model.Employee;
import com.aniket.springBootCRUD.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	// create employee REST API
	@PostMapping()
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}

	// get all employee rest API
	@GetMapping()
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	// get all employee rest API
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long emp_id) {
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(emp_id), HttpStatus.OK);
	}

	// update employee rest API
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long emp_id, @RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, emp_id), HttpStatus.OK);
	}

	// delete employee rest API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long emp_id) {
		employeeService.deleteEmployee(emp_id);
		return new ResponseEntity<String>("Employee deleted Sucessfully", HttpStatus.OK);
	}
}
