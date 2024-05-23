package com.like.system.dept.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.like.system.dept.application.port.in.DeptHierarchySelectUseCase;
import com.like.system.dept.application.port.out.DeptHierarchySelectPort;
import com.like.system.dept.dto.DeptHierarchyNgZorro;
import com.like.system.dept.dto.DeptQueryDTO;

@Service
public class DeptHierarchySelectService implements DeptHierarchySelectUseCase {

	DeptHierarchySelectPort port;
	
	private List<DeptHierarchyNgZorro> nodeList;
		
	DeptHierarchySelectService(DeptHierarchySelectPort port) {
		this.port = port;
	}	
	
	@Override
	public List<DeptHierarchyNgZorro> select(DeptQueryDTO dto) {		
		this.nodeList = port.select(dto);
		
		List<DeptHierarchyNgZorro> rootNodeList = nodeList.stream().filter(e -> !StringUtils.hasText(e.getParentDeptCode())).toList();
		
		List<DeptHierarchyNgZorro> result = this.addDeptChildNodeList(dto.companyCode(), rootNodeList);
		
		return result;
	}
	
	private List<DeptHierarchyNgZorro> addDeptChildNodeList(String companyCode, List<DeptHierarchyNgZorro> list) {
		List<DeptHierarchyNgZorro> children = null;
		
		for ( DeptHierarchyNgZorro node : list) {
			
			children = getChildren(companyCode, node.getDeptCode());
			
			if (children.isEmpty()) {
				node.setLeaf(true);
				continue;
			} else {
				node.setChildren(children);
				node.setLeaf(false);
				
				// 재귀 호출
				this.addDeptChildNodeList(companyCode, children);
			}			
		}
		
		return list;
	}	
  	
  	private List<DeptHierarchyNgZorro> getChildren(String companyCode, String parentDeptCode) {
  		return this.nodeList.stream().filter(e -> companyCode.equals(e.getCompanyCode()) && parentDeptCode.equals(e.getParentDeptCode())).toList();
  	}
	
}
