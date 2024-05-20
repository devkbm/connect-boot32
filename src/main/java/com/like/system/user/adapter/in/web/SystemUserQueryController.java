package com.like.system.user.adapter.in.web;

import static com.like.core.web.util.ResponseEntityUtil.toList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.system.user.application.port.in.SystemUserQueryUseCase;
import com.like.system.user.dto.SystemUserQueryDTO;
import com.like.system.user.dto.SystemUserSaveDTO;

@RestController
public class SystemUserQueryController {

	private SystemUserQueryUseCase useCase;
	
	public SystemUserQueryController(SystemUserQueryUseCase useCase) {
		this.useCase = useCase;
	}
		
	@GetMapping("/api/system/user")
	public ResponseEntity<?> getUserList(SystemUserQueryDTO dto) throws FileNotFoundException, IOException {
												
		List<SystemUserSaveDTO> dtoList = useCase.selectList(dto);			
		
		return toList(dtoList, MessageUtil.getQueryMessage(dtoList.size()));
	}
}
