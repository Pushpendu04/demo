package com.cts.outreach.controller;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cts.outreach.service.UserAuthenticationService;

public class UserAuthenticationControllerTest {
	
	
	@MockBean
	UserAuthenticationService userAuthenticationService;
	
	
	@InjectMocks
	UserAuthenticationController userAuthenticationController;
	
	@Autowired
	MockMvc mockmvc;
	
	
	@BeforeEach
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(userAuthenticationController).build();
		
		
		
		
	}

}
