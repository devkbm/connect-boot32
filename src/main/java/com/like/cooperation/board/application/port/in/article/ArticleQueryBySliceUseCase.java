package com.like.cooperation.board.application.port.in.article;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.like.cooperation.board.application.port.dto.ArticleListDTO;
import com.like.cooperation.board.application.port.dto.ArticleQueryDTO;

public interface ArticleQueryBySliceUseCase {
	
	Slice<ArticleListDTO> getAritlceSlice(String userId, ArticleQueryDTO dto, Pageable pageable);
}
