package com.like.hrm.hrmcode.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.hrmcode.application.port.dto.HrmRelationCodeDTO;
import com.like.hrm.hrmcode.application.service.HrmRelationCodeQueryService;
import com.like.system.core.message.MessageUtil;

@RestController
public class HrmRelationCodeQueryController {

	private HrmRelationCodeQueryService service;
	
	public HrmRelationCodeQueryController(HrmRelationCodeQueryService service) {
		this.service = service;
	}
	
	@GetMapping("/api/hrm/hrmrelation")
	public ResponseEntity<?> getHrmRelationCode(HrmRelationCodeDTO.SearchHrmRelationCode dto) {
						
		List<?> list = service.getHrmRelationCodeList(dto);
					
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
}
