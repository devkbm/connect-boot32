package com.like.cooperation.board.application.port.in.article;

import com.like.cooperation.board.application.port.dto.ArticleResponseDTO;

public interface ArticleSelectUseCase {
	ArticleResponseDTO select(Long id);
}
