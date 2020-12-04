package com.cts.outreach.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cts.outreach.exception.UserAlreadyExistsException;
import com.cts.outreach.exception.UserNotFoundException;
import com.cts.outreach.model.User;
import com.cts.outreach.repository.UserAuthenticationRepository;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

	UserAuthenticationRepository userAuthenticationRepository;

	public UserAuthenticationServiceImpl(
			UserAuthenticationRepository userAuthenticationRepository) {
		this.userAuthenticationRepository = userAuthenticationRepository;
	}

	@Override
	public User findByUserIdAndUserPassword(String userId, String password)
			throws UserNotFoundException {

		User user = userAuthenticationRepository.findByUserIdAndUserPassword(userId, password);
		if(user == null) {
			throw new UserNotFoundException("User is not found :" + userId);
		}
		return user;
	}

	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException {
		Optional<User> optionalUser = userAuthenticationRepository.findById(user.getUserId());
		if(optionalUser.isPresent()) {
			throw new UserAlreadyExistsException("User already exists :" + user);
		}
		userAuthenticationRepository.save(user);
		return true;
	}
}
