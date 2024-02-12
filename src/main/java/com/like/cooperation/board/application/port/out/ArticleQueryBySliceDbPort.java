package com.like.cooperation.board.application.port.out;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.like.cooperation.board.application.port.dto.ArticleQueryDTO;
import com.like.cooperation.board.application.port.dto.ArticleResponseDTO;

public interface ArticleQueryBySliceDbPort {
	Slice<ArticleResponseDTO> getAritlceSlice(ArticleQueryDTO dto, Pageable pageable);
}
