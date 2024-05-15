package com.like.system.menurole.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.menurole.application.port.dto.MenuRoleMappingHierarchyResponseDTO;
import com.like.system.menurole.application.port.in.MenuRoleHierarchySelectUseCase;
import com.like.system.menurole.application.port.out.MenuRoleHierarchySelectDbPort;

@Service
public class MenuRoleHierarchySelectService implements MenuRoleHierarchySelectUseCase {

	MenuRoleHierarchySelectDbPort dbPort;
	
	MenuRoleHierarchySelectService(MenuRoleHierarchySelectDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public List<MenuRoleMappingHierarchyResponseDTO> select(
			String companyCode, 
			String menuGroupCode,
			String roleCode) {
		
		return this.dbPort.select(companyCode, menuGroupCode, roleCode);
	}

}
