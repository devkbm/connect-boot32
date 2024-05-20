package com.like.system.hierarchycode.adapter.in.web;

import static com.like.core.web.util.ResponseEntityUtil.toList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.dto.HtmlSelectOptionRecord;
import com.like.core.message.MessageUtil;
import com.like.system.hierarchycode.application.dto.HierarchyCodeQueryDTO;
import com.like.system.hierarchycode.application.dto.HierarchySaveDTO;
import com.like.system.hierarchycode.application.service.HierarchyCodeQueryService;
import com.like.system.hierarchycode.domain.Code;
import com.like.system.hierarchycode.domain.SystemType;

@RestController
public class HierarchyCodeQueryController {

	private HierarchyCodeQueryService service;
	
	public HierarchyCodeQueryController(HierarchyCodeQueryService service) {
		this.service = service;
	}
	
	@GetMapping("/api/system/code/systemtype")
	public ResponseEntity<?> getWebResourceTypeList() {				
		
		List<HtmlSelectOptionRecord> list = new ArrayList<HtmlSelectOptionRecord>();
		
		for (SystemType type : SystemType.values()) {			
			list.add(new HtmlSelectOptionRecord(type.getDescription(), type.toString()));
		}
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}	
	
	@GetMapping("/api/system/code") 
	public ResponseEntity<?> getCodeList(@ModelAttribute HierarchyCodeQueryDTO searchCondition) {
							
		List<Code> list = service.getCodeList(searchCondition);  						 						
		
		List<HierarchySaveDTO> dtoList = list.stream()
										 .map(e -> HierarchySaveDTO.convertDTO(e))
										 .toList();
		
		return toList(dtoList, MessageUtil.getQueryMessage(dtoList.size()));
	}
}
