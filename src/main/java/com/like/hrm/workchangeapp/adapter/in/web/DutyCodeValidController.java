package com.like.hrm.workchangeapp.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.workchangeapp.adapter.out.persistence.jparepository.WorkChangeCodeRepository;

@RestController
public class DutyCodeValidController {

	private WorkChangeCodeRepository dutyCodeRepository;
	
	public DutyCodeValidController(WorkChangeCodeRepository dutyCodeRepository) {
		this.dutyCodeRepository = dutyCodeRepository;
	}
	
	@GetMapping("/api/hrm/dutycode/{id}/valid")
	public ResponseEntity<?> getDutyCode(@PathVariable(value="id") String id) {
		
		boolean exist = dutyCodeRepository.existsById(id);
					
		return toOne(exist, exist == true ? "사용가능한 근태 코드입니다." : "기존 근태 코드가 존재합니다.");
	}
	
}
