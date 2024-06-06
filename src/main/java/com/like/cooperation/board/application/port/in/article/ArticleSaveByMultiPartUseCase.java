package com.like.cooperation.board.application.port.in.article;

import com.like.cooperation.board.dto.ArticleSaveMultipartDTO;

public interface ArticleSaveByMultiPartUseCase {	
	
	void save(ArticleSaveMultipartDTO dto);
		
}
