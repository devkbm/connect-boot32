package com.like.system.dept.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.dept.application.port.in.DeptHierarchySelect2UseCase;
import com.like.system.dept.domain.DeptHierarchy;
import com.like.system.dept.domain.DeptHierarchyGenerator;
import com.like.system.dept.domain.DeptHierarchyRepository;
import com.like.system.dept.dto.DeptQueryDTO;

@Transactional(readOnly = true)
@Service
public class DeptHierarchySelect2Service implements DeptHierarchySelect2UseCase {

	DeptHierarchyGenerator gen;
	
	public DeptHierarchySelect2Service(DeptHierarchyRepository repository) {
		this.gen = new DeptHierarchyGenerator(repository);
	}		

	@Override
	public List<DeptHierarchy> select2(DeptQueryDTO dto) {
		// TODO Auto-generated method stub
		return this.gen.getTreeNodes(dto.companyCode());
						//.stream().map(e -> new );
	}

}
