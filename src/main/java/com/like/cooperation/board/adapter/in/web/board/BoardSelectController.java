package com.like.cooperation.board.adapter.in.web.board;

import static com.like.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.board.application.port.in.board.BoardSelectUseCase;
import com.like.cooperation.board.dto.BoardSaveDTO;
import com.like.core.message.MessageUtil;

@RestController
public class BoardSelectController {

	BoardSelectUseCase useCase;
	
	BoardSelectController(BoardSelectUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/api/grw/board/{id}")
	public ResponseEntity<?> getBoard(@PathVariable Long id) {				
								
		BoardSaveDTO dto = this.useCase.select(id);				
							
		return toOne(dto, MessageUtil.getQueryMessage(dto != null ? 1 : 0));
	}		
}
