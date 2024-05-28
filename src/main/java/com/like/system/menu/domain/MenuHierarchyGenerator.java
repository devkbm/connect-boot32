package com.like.system.menu.domain;

import java.util.List;

import org.springframework.util.StringUtils;

public class MenuHierarchyGenerator {

	private List<MenuHierarchy> allFlattenNodes;
	
	public MenuHierarchyGenerator(List<MenuHierarchy> allFlattenNodes) {
		this.allFlattenNodes = allFlattenNodes;
	}
	
	public List<MenuHierarchy> convertTreeNodes() {
		List<MenuHierarchy> rootNodeList = getRootList();
		
		return addChildren(rootNodeList);
	}
	
	private List<MenuHierarchy> addChildren(List<MenuHierarchy> nodes) {
		List<MenuHierarchy> children = null;
		
		for ( MenuHierarchy node : nodes ) {
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
	
	private List<MenuHierarchy> getRootList() {
		return this.allFlattenNodes
				.stream()
				.filter(e -> !StringUtils.hasText(e.parentMenuCode()))
				.toList();			
	}
	
	private List<MenuHierarchy> getChildren(String menuCode) {
		return this.allFlattenNodes
				.stream()
				.filter(e -> menuCode != null && menuCode.equals(e.parentMenuCode()))
				.toList();
	}	
	
}
