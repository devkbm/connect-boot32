package com.like.system.dept.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.dept.application.port.in.DeptQueryUseCase;
import com.like.system.dept.application.port.out.DeptQueryDbPort;
import com.like.system.dept.dto.DeptQueryDTO;
import com.like.system.dept.dto.DeptSaveDTO;

@Transactional(readOnly = true)
@Service
public class DeptQueryService implements DeptQueryUseCase {

	DeptQueryDbPort port;
	
	DeptQueryService(DeptQueryDbPort port) {
		this.port = port;
	}
	
	@Override
	public List<DeptSaveDTO> select(DeptQueryDTO dto) {
		return this.port.select(dto)
						.stream()
						.map(e -> DeptSaveDTO.toDTO(e))
						.toList();
	}
}
