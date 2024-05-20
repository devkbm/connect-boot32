package com.like.system.hierarchycode.adapter.in.web;

import static com.like.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.system.hierarchycode.application.dto.CodeHierarchy;
import com.like.system.hierarchycode.application.dto.HierarchyCodeQueryDTO;
import com.like.system.hierarchycode.application.service.HierarchyCodeSelectService;

@RestController
public class HierarchyCodeSelectController {

	private HierarchyCodeSelectService service;
	
	HierarchyCodeSelectController(HierarchyCodeSelectService service) {
		this.service = service;
	}
	
	@GetMapping("/api/system/codetree") 
	public ResponseEntity<?> getCodeHierarchyList(@ModelAttribute HierarchyCodeQueryDTO searchCondition) {
							
		List<CodeHierarchy> list = service.getCodeHierarchyList(searchCondition);  						 						
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
}
