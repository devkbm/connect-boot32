package com.like.system.dept.application.port.in;

import java.util.List;

import com.like.system.dept.dto.DeptQueryDTO;

public interface DeptHierarchySelectUseCase {
	List<?> select(DeptQueryDTO dto);
}
