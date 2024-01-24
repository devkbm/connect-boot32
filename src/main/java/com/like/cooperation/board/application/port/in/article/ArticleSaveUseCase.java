package com.like.cooperation.board.application.port.in.article;

import com.like.cooperation.board.application.port.dto.ArticleSaveDTO;
import com.like.cooperation.board.application.port.dto.ArticleSaveMultipartDTO;

public interface ArticleSaveUseCase {
	void save(ArticleSaveDTO dto);
	
	void save(ArticleSaveMultipartDTO dto);
		
}
