package com.cts.microservice.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.microservice.main.model.Profile;
import com.cts.microservice.main.repository.ProfileRepository;

@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	
	public Profile fetchByUserName(String name) {
		return profileRepository.findByUsername(name);
	}
}
