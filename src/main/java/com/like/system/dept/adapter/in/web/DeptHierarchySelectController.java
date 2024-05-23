package com.like.system.dept.adapter.in.web;

import static com.like.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.dept.application.port.in.DeptHierarchySelectUseCase;
import com.like.system.dept.dto.DeptHierarchyNgZorro;
import com.like.system.dept.dto.DeptQueryDTO;


@RestController
public class DeptHierarchySelectController {

	private DeptHierarchySelectUseCase useCase;
	
	public DeptHierarchySelectController(DeptHierarchySelectUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/api/system/depttree")
	public ResponseEntity<?> getDeptHierarchyList(@ModelAttribute @Valid DeptQueryDTO dto) {
							
		List<DeptHierarchyNgZorro> list = useCase.select(dto);  						 						
		
		return toList(list, String.format("%d 건 조회되었습니다.", list.size()));
	}
	
	
}
