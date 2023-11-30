package com.like.system.dept.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.dept.adapter.out.persistence.jpa.repository.DeptJpaRepository;
import com.like.system.dept.application.port.dto.DeptQueryDTO;
import com.like.system.dept.application.port.out.DeptDeletePort;
import com.like.system.dept.application.port.out.DeptSavePort;
import com.like.system.dept.application.port.out.DeptSelectPort;
import com.like.system.dept.domain.Dept;
import com.like.system.dept.domain.DeptId;

@Repository
@Transactional
public class DeptDbAdapter implements DeptSelectPort, DeptSavePort, DeptDeletePort {

	DeptJpaRepository repository;
	
	public DeptDbAdapter(DeptJpaRepository repository) {
		this.repository = repository;
	}

	@Override
	public Dept select(String organizationCode, String deptCode) {		
		return this.repository.findById(new DeptId(organizationCode, deptCode)).orElse(null);
	}

	@Override
	public List<Dept> select(DeptQueryDTO dto) {
		return this.repository.findAll(dto.getCondition());
	}
	
	@Override
	public void save(Dept entity) {
		this.repository.save(entity);		
	}

	@Override
	public void delete(String organizationCode, String deptCode) {
		this.repository.deleteById(new DeptId(organizationCode, deptCode));		
	}

	
}
