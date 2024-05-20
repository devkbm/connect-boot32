package com.like.system.dept.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.like.system.dept.application.port.in.DeptHierarchySelectUseCase;
import com.like.system.dept.application.port.out.DeptHierarchySelectPort;
import com.like.system.dept.dto.DeptHierarchyResponse;
import com.like.system.dept.dto.DeptQueryDTO;

@Service
public class DeptHierarchySelectService implements DeptHierarchySelectUseCase {

	DeptHierarchySelectPort port;
	
	private List<DeptHierarchyResponse> nodeList;
		
	DeptHierarchySelectService(DeptHierarchySelectPort port) {
		this.port = port;
	}	
	
	@Override
	public List<DeptHierarchyResponse> select(DeptQueryDTO dto) {		
		this.nodeList = port.select(dto);
		
		List<DeptHierarchyResponse> rootNodeList = nodeList.stream().filter(e -> !StringUtils.hasText(e.getParentDeptCode())).toList();
		
		List<DeptHierarchyResponse> result = this.addDeptChildNodeList(dto.companyCode(), rootNodeList);
		
		return result;
	}
	
	private List<DeptHierarchyResponse> addDeptChildNodeList(String companyCode, List<DeptHierarchyResponse> list) {
		List<DeptHierarchyResponse> children = null;
		
		for ( DeptHierarchyResponse node : list) {
			
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
  	
  	private List<DeptHierarchyResponse> getChildren(String companyCode, String parentDeptCode) {
  		return this.nodeList.stream().filter(e -> companyCode.equals(e.getCompanyCode()) && parentDeptCode.equals(e.getParentDeptCode())).toList();
  	}
	
}
