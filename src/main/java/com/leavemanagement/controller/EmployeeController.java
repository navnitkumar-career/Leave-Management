package com.leavemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leavemanagement.model.Employee;
import com.leavemanagement.service.EmployeeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping(value = "")
	public List<Employee> getAll(){
		return employeeService.getAll();
	}
		
	@PostMapping(value = "add")
	public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
		System.out.println(employee);
		Boolean flag = employeeService.addEmployee(employee);
		if (flag) {
			return ResponseEntity.status(HttpStatus.OK).body("Added Successfully.");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Failed! Please Try Again...");
		}
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Employee> getEmp(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployee(id));
	}
	
	@DeleteMapping(value = "delete/{id}")
	public ResponseEntity<String> deleteEmp(@PathVariable int id) {

		 employeeService.deleteEmployee(id);
		return ResponseEntity.status(HttpStatus.OK).body("Employee Delete Successfully.");
	}
	
	@PutMapping(value = "update")
	public ResponseEntity<String> updateEmp( @RequestBody Employee employee) {

		Boolean flag = employeeService.updateEmployee(employee);
		if (flag) {
			return ResponseEntity.status(HttpStatus.OK).body("Updated Successfully.");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Failed! Please Try Again...");
		}
	}
	
	@GetMapping(path = "/get")
	public Employee getEmployeeByEmail(@RequestParam("email") String email) {
		return employeeService.getEmployeeByEmail(email);
		
	}
}
