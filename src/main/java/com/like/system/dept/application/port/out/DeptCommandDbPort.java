package com.like.system.dept.application.port.out;

import java.util.Optional;

import com.like.system.dept.domain.Dept;

public interface DeptCommandDbPort {

	Optional<Dept> select(String organizationCode, String deptCode);
	
	void save(Dept entity);
	
	void delete(String organizationCode, String deptCode);
}
