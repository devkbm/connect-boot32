package com.like.hrm.hrmcode.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.hrmcode.application.port.dto.HrmCodeTypeSaveDTO;
import com.like.hrm.hrmcode.application.port.dto.HrmCodeTypeQueryDTO;
import com.like.hrm.hrmcode.application.service.HrmTypeQueryService;
import com.like.system.core.message.MessageUtil;

@RestController
public class HrmTypeQueryContoller {

	private HrmTypeQueryService service;
	
	public HrmTypeQueryContoller(HrmTypeQueryService service) {
		this.service = service;
	}	
	
	@GetMapping("/api/hrm/hrmtype")
	public ResponseEntity<?> getHrmTypeList(HrmCodeTypeQueryDTO dto) {														
		
		List<HrmCodeTypeSaveDTO> list = service.getHrmTypeList(dto)
										    .stream()
										    .map(e -> HrmCodeTypeSaveDTO.convert(e))
										    .toList();							
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
}
