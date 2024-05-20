package com.like.system.term.adapter.in.web;

import static com.like.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import com.like.core.dto.HtmlSelectOptionRecord;
import com.like.core.dto.HtmlSelectOptionable;
import com.like.core.message.MessageUtil;
import com.like.system.term.application.port.in.DataDomainQueryUseCase;
import com.like.system.term.domain.Database;
import com.like.system.term.dto.DataDomainSaveDTO;

@RestController
public class DataDomainQueryController {

	DataDomainQueryUseCase useCase;
	
	public DataDomainQueryController(DataDomainQueryUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/api/system/datadomin/database")
	public ResponseEntity<?> getMenuTypeList() {				
		
		List<HtmlSelectOptionRecord> list = HtmlSelectOptionable.fromEnum(Database.class);			
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/system/datadomin")
	public ResponseEntity<?> getList() {
		
		List<DataDomainSaveDTO> list = this.useCase.selectList();
			
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
											
}
