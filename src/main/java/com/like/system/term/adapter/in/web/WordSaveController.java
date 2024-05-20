package com.like.system.term.adapter.in.web;

import static com.like.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.system.term.application.port.in.WordSaveUseCase;
import com.like.system.term.dto.WordSaveDTO;

import jakarta.validation.Valid;

@RestController
public class WordSaveController {

	WordSaveUseCase useCase;
	
	WordSaveController(WordSaveUseCase useCase) {
		this.useCase = useCase;
	}
	
	@PostMapping("/api/system/word")
	public ResponseEntity<?> saveWord(@Valid @RequestBody WordSaveDTO dto) {
														
		useCase.save(dto);										
		
		return toList(null, MessageUtil.getSaveMessage(1));
	
	}
}
