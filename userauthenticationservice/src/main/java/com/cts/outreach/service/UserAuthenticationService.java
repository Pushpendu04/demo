package com.cts.outreach.service;

import com.cts.outreach.exception.UserAlreadyExistsException;
import com.cts.outreach.exception.UserNotFoundException;
import com.cts.outreach.model.User;

public interface UserAuthenticationService {

    public User findByUserIdAndUserPassword(String userId, String password) throws UserNotFoundException;

    boolean saveUser(User user) throws UserAlreadyExistsException;
}
