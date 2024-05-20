package com.like.system.user.application.service;

import org.springframework.stereotype.Service;

import com.like.system.user.application.port.in.SystemUserSelectUseCase;
import com.like.system.user.application.port.out.SystemUserCommandDbPort;
import com.like.system.user.dto.SystemUserSaveDTO;

@Service
public class SystemUserSelectService implements SystemUserSelectUseCase {

	SystemUserCommandDbPort dbPort;
	
	SystemUserSelectService(SystemUserCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public SystemUserSaveDTO selectDTO(String companyCode, String userId) {
		return SystemUserSaveDTO.toDTO(this.dbPort.select(companyCode, userId));
	}
}
