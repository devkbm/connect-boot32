package com.like.system.menu.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.like.system.menu.application.port.in.MenuHierarchySelectUseCase;
import com.like.system.menu.application.port.out.MenuHierarchySelectDbPort;
import com.like.system.menu.domain.MenuHierarchy;
import com.like.system.menu.domain.MenuHierarchyGenerator;
import com.like.system.menu.dto.MenuHierarchyNgZorro;

@Primary
@Service
public class MenuHierarchySelectService implements MenuHierarchySelectUseCase {

	MenuHierarchySelectDbPort dbPort;
	
	MenuHierarchySelectService(MenuHierarchySelectDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public List<MenuHierarchyNgZorro> select(String companyCode, String menuGroupCode) {
		
		MenuHierarchyGenerator generator = new MenuHierarchyGenerator(this.dbPort.getAllFlattenNodes(companyCode, menuGroupCode));
		
		List<MenuHierarchy> list = generator.convertTreeNodes();
				
		List<MenuHierarchyNgZorro> copy_list = new ArrayList<>();
		
		copyTreeNode(list, copy_list);
		
		return copy_list;
	}

	private void copyTreeNode(List<MenuHierarchy> original_list, List<MenuHierarchyNgZorro> copy_list) {
		MenuHierarchyNgZorro newNode = null;
		
		for (MenuHierarchy node: original_list) {
			newNode = convert(node);
			copyChildren(newNode, node);
			copy_list.add(newNode);
		}
	}	
	
	private void copyChildren(MenuHierarchyNgZorro parent, MenuHierarchy original) {
		MenuHierarchyNgZorro newNode = null;
		
		if (original.getChildren() == null) return;
		
		for (MenuHierarchy node: original.children()) {
			newNode = convert(node);
			if (parent.getChildren() == null) parent.setChildren(new ArrayList<>());
			
			parent.getChildren().add(newNode);
			copyChildren(newNode, node);
		}
	}
	
	private MenuHierarchyNgZorro convert(MenuHierarchy dto) {
		return MenuHierarchyNgZorro.build(dto); 
	}
}
