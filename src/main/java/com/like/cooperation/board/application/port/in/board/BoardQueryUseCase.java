package com.like.cooperation.board.application.port.in.board;

import java.util.List;

import com.like.cooperation.board.application.port.dto.BoardHierarchy;
import com.like.cooperation.board.application.port.dto.BoardQueryDTO;
import com.like.cooperation.board.application.port.dto.BoardSaveDTO;

public interface BoardQueryUseCase {	
	
	List<BoardSaveDTO> selectList(BoardQueryDTO dto);
	
	List<BoardHierarchy> selectHierarchy();
}
