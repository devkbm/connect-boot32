package com.like.system.user.external;

import org.springframework.stereotype.Service;

import com.like.system.user.application.port.out.SystemUserCommandDbPort;

@Service
public class SystemUserLoginDTOSelectService implements SystemUserLoginDTOSelectUseCase {

	SystemUserCommandDbPort dbPort;
	
	SystemUserLoginDTOSelectService(SystemUserCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public SystemUserLoginDTO get(String companyCode, String staffNo) {
		return SystemUserLoginDTO.toDTO(dbPort.select(companyCode, staffNo));
	}

}
