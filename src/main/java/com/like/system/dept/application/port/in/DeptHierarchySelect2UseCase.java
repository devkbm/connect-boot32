package com.like.system.dept.application.port.in;

import java.util.List;

import com.like.system.dept.domain.DeptHierarchy;
import com.like.system.dept.dto.DeptQueryDTO;

public interface DeptHierarchySelect2UseCase {
	List<?> select2(DeptQueryDTO dto);
}
