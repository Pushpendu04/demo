package com.cts.outreach.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cts.outreach.model.User;

@Repository
public interface UserAuthenticationRepository extends JpaRepository<User, String> {

	User findByUserIdAndUserPassword(String userId, String userPassword);
}
