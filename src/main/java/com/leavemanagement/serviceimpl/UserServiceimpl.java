package com.leavemanagement.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leavemanagement.model.Employee;
import com.leavemanagement.model.User;
import com.leavemanagement.repository.EmployeeRepository;
import com.leavemanagement.repository.UserRepository;
import com.leavemanagement.service.UserService;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	EmployeeRepository empRepository;

	@Override
	public Boolean register(User user) {

		User email = userRepository.findByEmail(user.getEmail());
		Employee emp = empRepository.findByEmail(user.getEmail());
		if (email == null) {
			if (user.getRole().contentEquals("USER_ROLE") && emp == null) {
				return false;
			} else {
				userRepository.save(user);
				return true;
			}
		}
		return false;
	}

	@Override
	public Boolean login(User user) {
		try {
			User existingUser = userRepository.findByEmail(user.getEmail());
			if (existingUser != null && user.getPassword().contentEquals(existingUser.getPassword())) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
