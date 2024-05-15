package com.like.system.menu.adapter.in.web;

import jakarta.validation.Valid;

import static com.like.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.system.menu.application.port.dto.MenuSaveDTO;
import com.like.system.menu.application.port.in.MenuSaveUseCase;

@RestController
public class MenuSaveController {
	
	private MenuSaveUseCase useCase;		
			
	public MenuSaveController(MenuSaveUseCase useCase) {
		this.useCase = useCase;		
	}						
		
	@PostMapping("/api/system/menugroup/{menuGroupCode}/menu/{menuCode}")
	public ResponseEntity<?> saveMenu(@RequestBody @Valid MenuSaveDTO dto) throws Exception {												
									
		useCase.save(dto);																			
														 				
		return toList(null, MessageUtil.getSaveMessage(1));
	}		
}
