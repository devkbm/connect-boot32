package com.like.system.dept.application.port.out;

import java.util.List;
import java.util.Optional;

import com.like.system.dept.application.port.dto.DeptQueryDTO;
import com.like.system.dept.domain.Dept;

public interface DeptSelectPort {

	Optional<Dept> select(String organizationCode, String deptCode);	
	
	List<Dept> select(DeptQueryDTO dto);
}
