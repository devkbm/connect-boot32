package com.like.system.user.adapter.in.web;

import static com.like.core.web.util.ResponseEntityUtil.toOne;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.system.user.application.port.dto.SystemUserSaveDTO;
import com.like.system.user.application.port.in.SystemUserSelectUseCase;


@RestController
public class SystemUserSelectController {		
				
	private SystemUserSelectUseCase useCase;
		
	public SystemUserSelectController(SystemUserSelectUseCase useCase) {
		this.useCase = useCase;
	}
	
	
	@GetMapping("/api/system/user/{userId}")
	public ResponseEntity<?> getUser(@RequestParam String companyCode, @PathVariable String userId) throws FileNotFoundException, IOException {
												
		SystemUserSaveDTO dto = useCase.selectDTO(companyCode, userId);					
		
		return toOne(dto, MessageUtil.getQueryMessage(1));
	}			
			
}
