package com.like.system.dept.adapter.in.web;

import static com.like.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.system.dept.application.port.in.DeptDeleteUseCase;

@RestController
public class DeptDeleteController {
	
	private DeptDeleteUseCase useCase;	
	
	public DeptDeleteController(DeptDeleteUseCase useCase) {
		this.useCase = useCase;		
	}		
	
	@DeleteMapping("/api/system/dept/{deptCode}")
	public ResponseEntity<?> deleteDept(@RequestParam String companyCode, @PathVariable String deptCode) {				
												
		useCase.delete(companyCode, deptCode);							
		
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
	
}
