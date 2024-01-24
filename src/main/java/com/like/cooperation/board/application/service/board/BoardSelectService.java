package com.like.cooperation.board.application.service.board;

import org.springframework.stereotype.Service;

import com.like.cooperation.board.application.port.dto.BoardSaveDTO;
import com.like.cooperation.board.application.port.in.board.BoardSelectUseCase;
import com.like.cooperation.board.application.port.out.BoardCommandDbPort;

@Service
public class BoardSelectService implements BoardSelectUseCase {

	BoardCommandDbPort dbPort;
	
	BoardSelectService(BoardCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public BoardSaveDTO select(Long boardId) {
		return BoardSaveDTO.toDTO(this.dbPort.select(boardId).orElse(null));
	}

}
