package com.like.cooperation.board.application.port.in.article;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.like.cooperation.board.application.port.dto.ArticleQueryDTO;
import com.like.cooperation.board.application.port.dto.ArticleResponseDTO;

public interface ArticleQueryUseCase {

	List<ArticleResponseDTO> getList(ArticleQueryDTO dto);
	
	Slice<ArticleResponseDTO> getAritlceSlice(ArticleQueryDTO dto, Pageable pageable);
}