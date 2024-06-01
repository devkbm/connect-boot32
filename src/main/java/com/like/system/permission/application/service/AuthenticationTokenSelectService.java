package com.like.system.permission.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.menurole.external.MenuGroupDTO;
import com.like.system.menurole.external.MenuGroupByUserSelectUseCase;
import com.like.system.permission.application.port.in.AuthenticationTokenSelectUseCase;
import com.like.system.permission.domain.AuthenticationToken;
import com.like.system.user.external.SystemUserDTO;
import com.like.system.user.external.SystemUserDTOSelectUseCase;

@Transactional
@Service
public class AuthenticationTokenSelectService implements AuthenticationTokenSelectUseCase {

	SystemUserDTOSelectUseCase userSelectUseCase;
	MenuGroupByUserSelectUseCase menuGroupSelectUseCase;
	
	AuthenticationTokenSelectService(SystemUserDTOSelectUseCase userSelectUseCase, 
									 MenuGroupByUserSelectUseCase menuGroupSelectUseCase) {
		this.userSelectUseCase = userSelectUseCase;
		this.menuGroupSelectUseCase = menuGroupSelectUseCase;
	}
	
	@Override
	public AuthenticationToken select(String companyCode, String userId, String sessionId, String ipAddress) {
		SystemUserDTO user = userSelectUseCase.findUser(companyCode, userId);
		
		List<MenuGroupDTO> menuGroupList = menuGroupSelectUseCase.select(companyCode, user.staffNo());
		
		return AuthenticationToken.of(user, menuGroupList, ipAddress, sessionId);
	}

}
