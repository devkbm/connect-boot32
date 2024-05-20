package com.like.system.systemcode.adapter.in.web;

import static com.like.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.dto.HtmlSelectOptionRecord;
import com.like.core.dto.HtmlSelectOptionable;
import com.like.core.message.MessageUtil;
import com.like.system.systemcode.adapter.out.persistence.jpa.entity.JpaBizTypeEnum;
import com.like.system.systemcode.application.port.in.BizCodeTypeSelectAllUseCase;
import com.like.system.systemcode.dto.BizCodeTypeSaveDTO;

@RestController
public class BizCodeTypeQueryController {

	private BizCodeTypeSelectAllUseCase service;
	
	public BizCodeTypeQueryController(BizCodeTypeSelectAllUseCase service) {
		this.service = service;
	}
	
	@GetMapping("/api/system/bizcodetype/system")
	public ResponseEntity<?> getSystemList() {				
		
		List<HtmlSelectOptionRecord> list = HtmlSelectOptionable.fromEnum(JpaBizTypeEnum.class);			
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/system/bizcodetype")
	public ResponseEntity<?> getBizCodeTypeList() {			
		List<BizCodeTypeSaveDTO> list = service.select("001");											   
					
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
}
