package com.like.system.dept.domain;

import java.util.List;

import org.springframework.util.StringUtils;

public class DeptHierarchyGenerator {
	
	private List<DeptHierarchy> allNodes;
	
	public DeptHierarchyGenerator(List<DeptHierarchy> allNodes) {
		this.allNodes = allNodes;
	}
		
	public List<DeptHierarchy> getTreeNodes() {			
		List<DeptHierarchy> rootNodeList = getRootList();
						
		return addChildren(rootNodeList);
	}
	
	private List<DeptHierarchy> addChildren(List<DeptHierarchy> nodes) {
		List<DeptHierarchy> children = null;
		
		for ( DeptHierarchy node : nodes ) {
			children = getChildren(node.getDeptCode());
			
			if (children.isEmpty()) {
				node.setLeaf(true);
				continue;
			} else {							
				node.setChildren(children);
				node.setLeaf(false);
								
				this.addChildren(children); 	//recursive call
			}
		}
				
		return nodes;
	}
	
	private List<DeptHierarchy> getRootList() {
		return this.allNodes.stream()
							.filter(e -> !StringUtils.hasText(e.parentDeptCode()))
							.toList();
	}	
	
	private List<DeptHierarchy> getChildren(String deptCode) {
		return this.allNodes.stream()
							.filter(e -> deptCode != null && deptCode.equals(e.parentDeptCode()))
							.toList();
	}
}
