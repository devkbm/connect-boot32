package com.like.system.hierarchycode.adapter.in.web;

import static com.like.core.web.util.ResponseEntityUtil.toList;
import static com.like.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.system.hierarchycode.application.dto.HierarchySaveDTO;
import com.like.system.hierarchycode.application.service.HierarchyCodeCommandService;
import com.like.system.hierarchycode.domain.Code;

@RestController
public class HierarchyCodeController {
	
	private HierarchyCodeCommandService service;			
		
	public HierarchyCodeController(HierarchyCodeCommandService service) {
		this.service = service;		
	}	
	
	@GetMapping("/api/system/code/{systemtype}/{codeId}") 
	public ResponseEntity<?> getCode(@PathVariable String systemtype
									,@PathVariable String codeId) {
								  						 					
		Code entity = service.getCode(systemtype, codeId);
		
		HierarchySaveDTO dto = HierarchySaveDTO.convertDTO(entity);
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}
			
	@PostMapping("/api/system/code")
	public ResponseEntity<?> saveCode(@RequestBody HierarchySaveDTO dto) {					
		
		service.saveCode(dto);		
											 				
		return toList(null, MessageUtil.getSaveMessage(1));
	}	
		
	@DeleteMapping("/api/system/code/{systemtype}/{codeId}")
	public ResponseEntity<?> delCode(@PathVariable String systemtype
									,@PathVariable String codeId) {						
												
		service.deleteCode(systemtype, codeId);
								 						
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
	
	
}
