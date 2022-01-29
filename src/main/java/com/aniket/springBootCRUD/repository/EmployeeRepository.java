package com.aniket.springBootCRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aniket.springBootCRUD.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
