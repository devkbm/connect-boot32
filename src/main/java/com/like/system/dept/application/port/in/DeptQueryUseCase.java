package com.like.system.dept.application.port.in;

import java.util.List;

import com.like.system.dept.dto.DeptQueryDTO;
import com.like.system.dept.dto.DeptSaveDTO;

public interface DeptQueryUseCase {
	List<DeptSaveDTO> select(DeptQueryDTO dto);
}
