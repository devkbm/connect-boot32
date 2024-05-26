package com.like.system.dept.domain;

import java.util.List;

import org.springframework.util.StringUtils;

public class DeptHierarchyGenerator {

	private final DeptHierarchyRepository repository;
	private List<DeptHierarchy> allNodes;
	
	public DeptHierarchyGenerator(DeptHierarchyRepository repository) {
		this.repository = repository;
	}
		
	public List<DeptHierarchy> getTreeNodes(String companyCode) {
		this.allNodes = this.repository.getAllNodes(companyCode);
		
		List<DeptHierarchy> rootNodeList = getRootList();
						
		return addChildren(rootNodeList);
	}
	
	private List<DeptHierarchy> addChildren(List<DeptHierarchy> nodes) {
		List<DeptHierarchy> children = null;
		
		for ( DeptHierarchy node : nodes ) {
			children = getChildren(node.getDeptCode());
			
			if (!children.isEmpty()) {
				node.setChildren(children);
				node.setLeaf(false);
								
				this.addChildren(children); 	//recursive call
			} else {
				node.setLeaf(true);
				continue;
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
