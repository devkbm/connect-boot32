package com.like.system.menurole.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.menurole.application.port.in.MenuRoleHierarchySelect2UseCase;
import com.like.system.menurole.application.port.out.MenuRoleHierarchySelect2DbPort;
import com.like.system.menurole.domain.MenuRoleHierarchy;
import com.like.system.menurole.domain.MenuRoleHierarchyGenerator;
import com.like.system.menurole.dto.MenuRoleMappingHierarchyResponseDTO;

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

		List<MenuRoleMappingHierarchyResponseDTO> copy_list = new ArrayList<>();
		
		copyTreeNode(list, copy_list);
		
		return copy_list;
	}

	private void copyTreeNode(List<MenuRoleHierarchy> original_list, List<MenuRoleMappingHierarchyResponseDTO> copy_list) {
		MenuRoleMappingHierarchyResponseDTO newNode = null;
		
		for (MenuRoleHierarchy node: original_list) {
			newNode = convert(node);
			copyChildren(newNode, node);
			copy_list.add(newNode);
		}
	}
	
	private void copyChildren(MenuRoleMappingHierarchyResponseDTO parent, MenuRoleHierarchy original) {
		MenuRoleMappingHierarchyResponseDTO newNode = null;
		
		if (original.getChildren() == null) return;
		
		for (MenuRoleHierarchy node: original.getChildren()) {
			newNode = convert(node);
			if (parent.getChildren() == null) parent.setChildren(new ArrayList<>());
			
			parent.getChildren().add(newNode);
			copyChildren(newNode, node);
		}
	}
	
	private MenuRoleMappingHierarchyResponseDTO convert(MenuRoleHierarchy dto) {
		return MenuRoleMappingHierarchyResponseDTO.build(dto);
	}

}
