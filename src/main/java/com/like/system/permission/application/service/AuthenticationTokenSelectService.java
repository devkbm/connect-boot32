package com.like.system.permission.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.menu.dto.MenuGroupSaveDTO;
import com.like.system.menurole.application.port.in.external.MenuGroupByUserSelectUseCase;
import com.like.system.permission.application.port.in.AuthenticationTokenSelectUseCase;
import com.like.system.permission.domain.AuthenticationToken;
import com.like.system.user.application.port.in.external.SystemUserCommonSelectUseCase;
import com.like.system.user.domain.SystemUser;

@Transactional
@Service
public class AuthenticationTokenSelectService implements AuthenticationTokenSelectUseCase {

	SystemUserCommonSelectUseCase userSelectUseCase;
	MenuGroupByUserSelectUseCase menuGroupSelectUseCase;
	
	AuthenticationTokenSelectService(SystemUserCommonSelectUseCase userSelectUseCase, 
									 MenuGroupByUserSelectUseCase menuGroupSelectUseCase) {
		this.userSelectUseCase = userSelectUseCase;
		this.menuGroupSelectUseCase = menuGroupSelectUseCase;
	}
	
	@Override
	public AuthenticationToken select(String companyCode, String userId, String sessionId, String ipAddress) {
		SystemUser user = userSelectUseCase.findUser(companyCode, userId);
		
		List<MenuGroupSaveDTO> menuGroupList = menuGroupSelectUseCase.select(companyCode, user.getStaffId().getStaffNo());
		
		return AuthenticationToken.of(user, menuGroupList, ipAddress, sessionId);
	}

}
