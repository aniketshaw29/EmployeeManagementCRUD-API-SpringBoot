package com.aniket.springBootCRUD.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aniket.springBootCRUD.exception.ResourceNotFoundException;
import com.aniket.springBootCRUD.model.Employee;
import com.aniket.springBootCRUD.repository.EmployeeRepository;
import com.aniket.springBootCRUD.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isPresent()) {
			return employee.get();
		} else {
			throw new ResourceNotFoundException("Employee", "Id", id);
		}
		// if done with lambda can be done in single line
		/*
		 * return employeeRepository.findById(id).orElseThrow(() -> new
		 * ResourceNotFoundException("Employee", "Id", id));
		 */
	}

	@Override
	public Employee updateEmployee(Employee employee, Long id) {
		// lambda expression
		Employee existingEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		// now save existing employee to DB
		employeeRepository.save(existingEmployee);
		return existingEmployee;
	}

	@Override
	public void deleteEmployee(Long id) {
		employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
		employeeRepository.deleteById(id);
		// another way
//		public String deleteEmployee(Long id) {
//		Optional<Employee> employee = employeeRepository.findById(id);
//		if (employee.isPresent()) { // checking employee id exists in db
//			employeeRepository.delete(employee.get());
		// employeeRepository.deleteById(id);
//			return "Employee Deleted";
//		} else {
//			throw new ResourceNotFoundException("Employee", "Id", id);
//		}
	}

}
