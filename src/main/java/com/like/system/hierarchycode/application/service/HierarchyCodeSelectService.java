package com.like.system.hierarchycode.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.like.system.hierarchycode.application.dto.CodeHierarchy;
import com.like.system.hierarchycode.application.dto.HierarchyCodeQueryDTO;
import com.like.system.hierarchycode.domain.HierarchyCodeSelectRepository;


@Transactional(readOnly=true)
@Service
public class HierarchyCodeSelectService {

	HierarchyCodeSelectRepository repository;
	
	private List<CodeHierarchy> allList;
	
	HierarchyCodeSelectService(HierarchyCodeSelectRepository repository) {
		this.repository = repository;
	}
	
	public List<CodeHierarchy> getCodeHierarchyList(HierarchyCodeQueryDTO dto) {		
						
		allList = repository.getCodeHierarchyList(dto);
		
		List<CodeHierarchy> rootList = getRootList(); 					
		
		return addChildren(rootList);
	}
	
	private List<CodeHierarchy> getRootList() {
		return allList.stream().filter(e -> !StringUtils.hasText(e.getParentId())).toList();
	}
	
	private List<CodeHierarchy> getChildren(String parentId) {
		return allList.stream().filter(e -> parentId.equals(e.getParentId())).toList();
	}
	
	private List<CodeHierarchy> addChildren(List<CodeHierarchy> nodeList) {
		
		List<CodeHierarchy> children = null;
		
		for (CodeHierarchy node : nodeList) {					
			children = getChildren(node.getId());
			
			if (children.isEmpty()) {
				node.setLeaf(true);
				continue;
			} else { 					
				node.setLeaf(false);
				node.setChildren(children);
				
				addChildren(children);
			} 				
		}
		
		return nodeList;		
	}
	
	
}
