package com.leavemanagement.serviceimpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leavemanagement.dto.EmployeeLeaveDTO;
import com.leavemanagement.model.Employee;
import com.leavemanagement.model.EmployeeLeave;
import com.leavemanagement.repository.EmployeeRepository;
import com.leavemanagement.repository.LeaveRepository;
import com.leavemanagement.service.LeaveService;

@Service
public class LeaveServiceimpl implements LeaveService {

	@Autowired
	LeaveRepository leaveRepository;
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired(required = true)
	ModelMapper modelMapper;

	@Override
	public List<EmployeeLeave> getAll(EmployeeLeaveDTO leaveDTO) {
		Employee employee = employeeRepository.findByEmail(leaveDTO.getEmail().replace("\"", ""));
		System.out.println(employee);
		System.out.println(employee.getId());
		return leaveRepository.findByEmployeeId(employee.getId());
	}

	@Override
	public Boolean addLeave(EmployeeLeaveDTO leaveDTO) {
		try {
			Employee employee = employeeRepository.findByEmail(leaveDTO.getEmail().replace("\"", ""));
			EmployeeLeave employeeLeave = new EmployeeLeave();
			modelMapper.map(leaveDTO, employeeLeave);
			employeeLeave.setEmployee(employee);
			leaveRepository.save(employeeLeave);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public EmployeeLeave getLeave(int id) {
		return leaveRepository.findById(id).get();
	}

	@Override
	public Boolean deleteLeave(int id) {
		try {
			System.out.println(id);
			leaveRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public List<EmployeeLeave> getLeaveByName(String name) {
		Employee employee = employeeRepository.findByFirstName(name);
		List<EmployeeLeave> leave = leaveRepository.findByEmployeeId(employee.getId());
		return leave;
	}
	
	@Override
	public List<EmployeeLeave> getLeaveByEmail(String email) {
		Employee employee = employeeRepository.findByEmail(email.replace("\"", ""));
		List<EmployeeLeave> leave = leaveRepository.findByEmployeeId(employee.getId());
		return leave;
	}

	@Override
	public Boolean updateLeave(EmployeeLeave employeeLeave) {
		try {
			leaveRepository.save(employeeLeave);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

}
