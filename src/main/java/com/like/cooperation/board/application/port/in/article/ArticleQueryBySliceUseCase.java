package com.like.cooperation.board.application.port.in.article;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.like.cooperation.board.application.port.dto.ArticleQueryDTO;
import com.like.cooperation.board.application.port.dto.ArticleResponseDTO;

public interface ArticleQueryBySliceUseCase {

	Slice<ArticleResponseDTO> getAritlceSlice(ArticleQueryDTO dto, Pageable pageable);
}
