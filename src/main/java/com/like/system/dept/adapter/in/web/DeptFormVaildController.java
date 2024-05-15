package com.like.system.dept.adapter.in.web;

import static com.like.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.dept.application.port.in.DeptSelectUseCase;

@RestController
public class DeptFormVaildController {
	
	private DeptSelectUseCase useCase;
	
	public DeptFormVaildController(DeptSelectUseCase useCase) {
		this.useCase = useCase;		
	}
	
	@GetMapping("/api/system/dept/{id}/valid")
	public ResponseEntity<?> getValidateDeptDuplication(@RequestParam String companyCode, @PathVariable String id) {
							
		Boolean exist = useCase.select(companyCode, id) == null ? false : true;  	
						
		return toOne(exist, exist ? "중복된 부서 코드가 있습니다." : "사용가능한 부서 코드입니다.");
	}
	
}
