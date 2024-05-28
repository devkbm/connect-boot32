package com.like.system.menurole.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.menurole.application.port.in.MenuRoleHierarchySelect2UseCase;
import com.like.system.menurole.application.port.out.MenuRoleHierarchySelect2DbPort;
import com.like.system.menurole.domain.MenuRoleHierarchy;
import com.like.system.menurole.domain.MenuRoleHierarchyGenerator;

@Service
public class MenuRoleHierarchySelect2Service implements MenuRoleHierarchySelect2UseCase {

	MenuRoleHierarchySelect2DbPort dbPort;
	
	MenuRoleHierarchySelect2Service(MenuRoleHierarchySelect2DbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public List<?> select(String companyCode, String menuGroupCode, String roleCode) {
		MenuRoleHierarchyGenerator generator = new MenuRoleHierarchyGenerator(this.dbPort.select(companyCode, menuGroupCode, roleCode));
		
		List<MenuRoleHierarchy> list = generator.convertTreeNodes();
		
		return null;
	}

}
