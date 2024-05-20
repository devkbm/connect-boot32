package com.like.system.menurole.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.menu.dto.MenuHierarchyResponseDTO;
import com.like.system.menurole.application.port.in.SystemUserMenuHierarchySelectUseCase;
import com.like.system.menurole.application.port.out.SystemUserMenuHierarchySelectDbPort;
import com.like.system.user.application.port.in.SystemUserSelectUseCase;
import com.like.system.user.dto.SystemUserSaveDTO;

@Service
public class SystemUserMenuHierarchySelectService implements SystemUserMenuHierarchySelectUseCase {
	
	SystemUserMenuHierarchySelectDbPort dbPort;
	SystemUserSelectUseCase userSelectUseCase;
	
	SystemUserMenuHierarchySelectService(SystemUserMenuHierarchySelectDbPort dbPort,
										 SystemUserSelectUseCase userSelectUseCase) {
		this.dbPort = dbPort;
		this.userSelectUseCase = userSelectUseCase;
	}
		
	@Override
	public List<MenuHierarchyResponseDTO> select(String companyCode, String userId, String menuGroupCode) {
		SystemUserSaveDTO userDTO = userSelectUseCase.selectDTO(companyCode, userId);
					
		return this.dbPort.select(companyCode, menuGroupCode, userDTO.roleList());
	}

}
