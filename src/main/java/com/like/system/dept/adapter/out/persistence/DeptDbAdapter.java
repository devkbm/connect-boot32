package com.like.system.dept.adapter.out.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.like.system.dept.adapter.out.persistence.jpa.repository.DeptJpaRepository;
import com.like.system.dept.application.port.out.DeptCommandDbPort;
import com.like.system.dept.domain.Dept;
import com.like.system.dept.domain.DeptId;

@Repository
public class DeptDbAdapter implements DeptCommandDbPort {

	DeptJpaRepository repository;
	
	public DeptDbAdapter(DeptJpaRepository repository) {
		this.repository = repository;
	}

	@Override
	public Optional<Dept> select(String organizationCode, String deptCode) {		
		return this.repository.findById(new DeptId(organizationCode, deptCode));
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
