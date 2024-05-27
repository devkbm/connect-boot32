package com.like.system.menu.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.menu.application.port.in.MenuHierarchySelectUseCase;
import com.like.system.menu.application.port.out.MenuHierarchySelectDbPort;
import com.like.system.menu.dto.MenuHierarchyNgZorro;

@Service
public class MenuHierarchySelectService implements MenuHierarchySelectUseCase {

	MenuHierarchySelectDbPort port;
	
	MenuHierarchySelectService(MenuHierarchySelectDbPort port) {
		this.port = port;
	}
	@Override
	public List<MenuHierarchyNgZorro> select(String companyCode, String menuGroupCode) {
		return this.port.select(companyCode, menuGroupCode);
	}
	
	

}
