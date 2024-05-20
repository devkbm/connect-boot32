package com.like.system.systemcode.adapter.in.web;

import static com.like.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.system.systemcode.application.service.BizCodeQueryService;
import com.like.system.systemcode.dto.BizCodeDTO;

@RestController
public class BizCodeQueryController {
	
	private BizCodeQueryService service;
	
	public BizCodeQueryController(BizCodeQueryService service) {
		this.service = service;
	}

	@GetMapping("/api/system/bizcodetype/{typeId}/bizcode")
	public ResponseEntity<?> getBizCodeList(@RequestParam String companyCode
			   							   ,@PathVariable String typeId) {
		
		List<BizCodeDTO.Form> list = service.getBizCodeAllList(companyCode, typeId)
											.stream()
											.map(e -> BizCodeDTO.Form.convert(e))
											.toList();											   
					
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
}
