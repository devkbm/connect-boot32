package com.like.system.user.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.menu.dto.MenuHierarchyNgZorro;
import com.like.system.menurole.application.port.in.external.SystemUserMenuHierarchySelectUseCase;

import com.like.system.user.application.port.in.SystemUserMenuHierarchyQueryUseCase;
import com.like.system.user.application.port.out.SystemUserCommandDbPort;
import com.like.system.user.domain.SystemUser;

@Service
public class SystemUserMenuHierarchyQueryService implements SystemUserMenuHierarchyQueryUseCase {
	
	SystemUserMenuHierarchySelectUseCase menuHierarchySelectUseCase;
	SystemUserCommandDbPort dbPort;
	
	SystemUserMenuHierarchyQueryService(SystemUserCommandDbPort dbPort,
			SystemUserMenuHierarchySelectUseCase menuHierarchySelectUseCase) {
		this.dbPort = dbPort;
		this.menuHierarchySelectUseCase = menuHierarchySelectUseCase;		
	}
		
	@Override
	public List<MenuHierarchyNgZorro> select(String companyCode, String userId, String menuGroupCode) {
		SystemUser userDTO = dbPort.select(companyCode, userId);

		List<String> roleList = userDTO.getRoleList().stream().map(e -> e.getRoleCode()).toList();
		
		return this.menuHierarchySelectUseCase.select(companyCode, menuGroupCode, roleList);
	}

}
