package com.like.system.term.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.core.message.MessageUtil;
import com.like.system.term.application.port.in.TermDeleteUseCase;

@RestController
public class TermDeleteController {

	TermDeleteUseCase useCase;
	
	TermDeleteController(TermDeleteUseCase useCase) {
		this.useCase = useCase;
	}
	
	@DeleteMapping("/api/system/terms/{id}")
	public ResponseEntity<?> delTerm(@PathVariable String id) {
								
		useCase.delete(id);										
		
		return toList(null, MessageUtil.getDeleteMessage(1));
	}
}
