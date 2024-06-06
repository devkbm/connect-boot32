package com.like.cooperation.board.application.port.in.article;

import com.like.cooperation.board.dto.ArticleSaveDTO;

public interface ArticleSaveByJsonUseCase {
	void save(ArticleSaveDTO dto);	
}
