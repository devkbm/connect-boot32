package com.like.system.user.application.service;

import org.springframework.stereotype.Service;

import com.like.system.user.application.port.dto.SystemUserProfileDTO;
import com.like.system.user.application.port.in.SystemUserProfileSelectUseCase;
import com.like.system.user.application.port.out.SystemUserCommandDbPort;
import com.like.system.user.domain.SystemUser;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class SystemUserProfileSelectService implements SystemUserProfileSelectUseCase {

	SystemUserCommandDbPort dbPort;	
	
	SystemUserProfileSelectService(SystemUserCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public SystemUserProfileDTO select(String companyCode, String userId, HttpServletRequest request) {
		
		SystemUser entity = dbPort.select(companyCode, userId);
								
		return SystemUserProfileDTO.toDTO(entity, request);
	}

}
