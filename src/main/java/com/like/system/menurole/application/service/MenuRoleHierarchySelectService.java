package com.like.system.menurole.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.menurole.application.port.in.MenuRoleHierarchySelectUseCase;
import com.like.system.menurole.application.port.out.MenuRoleHierarchySelect2DbPort;
import com.like.system.menurole.domain.MenuRoleHierarchy;
import com.like.system.menurole.domain.MenuRoleHierarchyGenerator;
import com.like.system.menurole.dto.MenuRoleHierarchyNgZorro;

@Service
public class MenuRoleHierarchySelectService implements MenuRoleHierarchySelectUseCase {

	MenuRoleHierarchySelect2DbPort dbPort;
	
	MenuRoleHierarchySelectService(MenuRoleHierarchySelect2DbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public List<?> select(String companyCode, String menuGroupCode, String roleCode) {
		MenuRoleHierarchyGenerator generator = new MenuRoleHierarchyGenerator(this.dbPort.select(companyCode, menuGroupCode, roleCode));
		
		List<MenuRoleHierarchy> list = generator.convertTreeNodes();

		List<MenuRoleHierarchyNgZorro> copy_list = new ArrayList<>();
		
		copyTreeNode(list, copy_list);
		
		for ( MenuRoleHierarchyNgZorro dto : copy_list ) {
			MenuRoleHierarchyHalfChecker.setHalfChecked(dto);
		}
		
		return copy_list;
	}

	private void copyTreeNode(List<MenuRoleHierarchy> original_list, List<MenuRoleHierarchyNgZorro> copy_list) {
		MenuRoleHierarchyNgZorro newNode = null;
		
		for (MenuRoleHierarchy node: original_list) {
			newNode = convert(node);
			copyChildren(newNode, node);
			copy_list.add(newNode);
		}
	}
	
	private void copyChildren(MenuRoleHierarchyNgZorro parent, MenuRoleHierarchy original) {
		MenuRoleHierarchyNgZorro newNode = null;
		
		if (original.getChildren() == null) return;
		
		for (MenuRoleHierarchy node: original.getChildren()) {
			newNode = convert(node);
			if (parent.getChildren() == null) parent.setChildren(new ArrayList<>());
			
			parent.getChildren().add(newNode);
			copyChildren(newNode, node);
		}
	}
	
	private MenuRoleHierarchyNgZorro convert(MenuRoleHierarchy dto) {
		return MenuRoleHierarchyNgZorro.build(dto);
	}

}
