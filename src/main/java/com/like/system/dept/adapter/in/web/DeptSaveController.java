package com.like.system.dept.adapter.in.web;

import jakarta.validation.Valid;

import static com.like.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.system.dept.application.port.in.DeptSaveUseCase;
import com.like.system.dept.dto.DeptSaveDTO;

@RestController
public class DeptSaveController {
	
	private DeptSaveUseCase useCase;	
	
	public DeptSaveController(DeptSaveUseCase useCase) {
		this.useCase = useCase;		
	}
		
	@PostMapping("/api/system/dept")
	public ResponseEntity<?> saveDept(@Valid @RequestBody DeptSaveDTO dto) {			
																
		useCase.save(dto);		
											 				
		return toList(null, MessageUtil.getSaveMessage(1));
	}			
	
}
