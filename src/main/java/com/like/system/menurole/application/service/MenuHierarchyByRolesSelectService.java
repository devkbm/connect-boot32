package com.like.system.menurole.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.menu.domain.MenuHierarchy;
import com.like.system.menu.domain.MenuHierarchyGenerator;
import com.like.system.menurole.application.port.out.MenuHierarchyByRolesSelectDbPort;
import com.like.system.menurole.export.MenuHierarchyByRolesSelectUseCase;
import com.like.system.menurole.export.MenuHierarchyNgZorroDTO;

@Service
public class MenuHierarchyByRolesSelectService implements MenuHierarchyByRolesSelectUseCase {

	MenuHierarchyByRolesSelectDbPort dbPort;
	
	MenuHierarchyByRolesSelectService(MenuHierarchyByRolesSelectDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public List<MenuHierarchyNgZorroDTO> select(String companyCode, String menuGroupCode, List<String> roleCodes) {
		MenuHierarchyGenerator generator = new MenuHierarchyGenerator(dbPort.select(companyCode, menuGroupCode, roleCodes));
		
		List<MenuHierarchy> list = generator.convertTreeNodes();

		List<MenuHierarchyNgZorroDTO> copy_list = new ArrayList<>();
		
		copyTreeNode(list, copy_list);
		
		return copy_list;
	}
	
	private void copyTreeNode(List<MenuHierarchy> original_list, List<MenuHierarchyNgZorroDTO> copy_list) {
		MenuHierarchyNgZorroDTO newNode = null;
		
		for (MenuHierarchy node: original_list) {
			newNode = convert(node);
			copyChildren(newNode, node);
			copy_list.add(newNode);
		}
	}	
	
	private void copyChildren(MenuHierarchyNgZorroDTO parent, MenuHierarchy original) {
		MenuHierarchyNgZorroDTO newNode = null;
		
		if (original.getChildren() == null) return;
		
		for (MenuHierarchy node: original.children()) {
			newNode = convert(node);
			if (parent.getChildren() == null) parent.setChildren(new ArrayList<>());
			
			parent.getChildren().add(newNode);
			copyChildren(newNode, node);
		}
	}
	
	private MenuHierarchyNgZorroDTO convert(MenuHierarchy dto) {
		return MenuHierarchyNgZorroDTO.build(dto); 
	}

}
