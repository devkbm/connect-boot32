package com.like.cooperation.board.adapter.in.web.board;

import static com.like.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.board.application.port.in.board.BoardQueryUseCase;
import com.like.cooperation.board.dto.BoardQueryDTO;
import com.like.cooperation.board.dto.BoardSaveDTO;
import com.like.cooperation.board.dto.BoardTypeDTO;
import com.like.core.dto.HtmlSelectOptionRecord;
import com.like.core.dto.HtmlSelectOptionable;
import com.like.core.message.MessageUtil;

@RestController
public class BoardQueryController {
	
	BoardQueryUseCase useCase;
	
	public BoardQueryController(BoardQueryUseCase useCase) {		
		this.useCase = useCase;
	}
	
	@GetMapping("/api/grw/board/boardType")
	public ResponseEntity<?> getMenuTypeList() {				
		
		List<HtmlSelectOptionRecord> list = HtmlSelectOptionable.fromEnum(BoardTypeDTO.class);			
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/grw/boardHierarchy")
	public ResponseEntity<?> getBoardHierarchyList() {
											
		List<?> list = useCase.selectHierarchy();				 			
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}	
	
	@GetMapping("/api/grw/board")
	public ResponseEntity<?> getBoardList(BoardQueryDTO dto) {
		List<BoardSaveDTO> dtoList = useCase.selectList(dto); 												
				
		return toList(dtoList, MessageUtil.getQueryMessage(dtoList.size()));
	}	
	

}
