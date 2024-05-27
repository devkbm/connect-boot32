package com.like.system.dept.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.dept.application.port.in.DeptHierarchySelect2UseCase;
import com.like.system.dept.domain.DeptHierarchy;
import com.like.system.dept.domain.DeptHierarchyGenerator;
import com.like.system.dept.domain.DeptHierarchyRepository;
import com.like.system.dept.dto.DeptHierarchyNgZorro;
import com.like.system.dept.dto.DeptQueryDTO;

@Transactional(readOnly = true)
@Service
public class DeptHierarchySelect2Service implements DeptHierarchySelect2UseCase {

	DeptHierarchyGenerator gen;
	
	public DeptHierarchySelect2Service(DeptHierarchyRepository repository) {
		this.gen = new DeptHierarchyGenerator(repository);
	}		

	@Override
	public List<?> select2(DeptQueryDTO dto) {

		List<DeptHierarchy> list = this.gen.getTreeNodes(dto.companyCode());		
		
		List<DeptHierarchyNgZorro> after_list = new ArrayList<>();
		
		return addChildren(list, after_list);
		
		//return list;
	}
	
	private List<DeptHierarchyNgZorro> addChildren(List<DeptHierarchy> beforeNodes, List<DeptHierarchyNgZorro> afterNodes) {			
		List<DeptHierarchy> children = null;
		
		for (DeptHierarchy node : beforeNodes ) {
			DeptHierarchyNgZorro afterNode = convert(node);
			
			children = node.getChildren();
					
			if (children == null || children.isEmpty()) {				
				afterNodes.add(afterNode);
				continue;
			} else {
				List<DeptHierarchyNgZorro> chilrenNodes = children.stream().map(e -> convert(e)).toList();
				afterNode.setChildren(chilrenNodes);
				
				addChildren(children, chilrenNodes);
			}		
		}
		
		return afterNodes;
	}
	
	private DeptHierarchyNgZorro convert(DeptHierarchy dto) {
		return DeptHierarchyNgZorro.build(dto);
	}
	
}
