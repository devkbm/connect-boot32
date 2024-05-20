package com.like.cooperation.board.application.port.in.board;

import com.like.cooperation.board.dto.BoardSaveDTO;

public interface BoardSaveUseCase {
	void save(BoardSaveDTO dto);
}
