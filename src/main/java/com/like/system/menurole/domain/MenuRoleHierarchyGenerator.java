package com.like.system.menurole.domain;

import java.util.List;

import org.springframework.util.StringUtils;

public class MenuRoleHierarchyGenerator {

	private List<MenuRoleHierarchy> allFlattenNodes;
	
	public MenuRoleHierarchyGenerator(List<MenuRoleHierarchy> allFlattenNodes) {
		this.allFlattenNodes = allFlattenNodes;
	}
	
	public List<MenuRoleHierarchy> convertTreeNodes() {
		List<MenuRoleHierarchy> rootNodeList = getRootList();
		
		return addChildren(rootNodeList);			
	}
	
	private List<MenuRoleHierarchy> addChildren(List<MenuRoleHierarchy> nodes) {
		List<MenuRoleHierarchy> children = null;
		
		for ( MenuRoleHierarchy node : nodes) {
			children = getChildren(node.menuCode());
			
			if (children.isEmpty()) {
				node.isLeaf(true);
				continue;
			} else {							
				node.setChildren(children);
				node.isLeaf(false);
								
				this.addChildren(children); 	//recursive call
			}
		}
		
		return nodes;
	}
	
	private List<MenuRoleHierarchy> getRootList() {
		return this.allFlattenNodes
				.stream()
				.filter(e -> !StringUtils.hasText(e.parentMenuCode()))
				.toList();
	}
	
	private List<MenuRoleHierarchy> getChildren(String menuCode) {
		return this.allFlattenNodes
				.stream()
				.filter(e -> menuCode != null && menuCode.equals(e.parentMenuCode()))
				.toList();
	}
}
