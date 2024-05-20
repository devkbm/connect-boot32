package com.like.cooperation.board.application.port.in.article;

import java.util.List;

import com.like.cooperation.board.dto.ArticleQueryDTO;
import com.like.cooperation.board.dto.ArticleResponseDTO;

public interface ArticleQueryByListUseCase {

	List<ArticleResponseDTO> getList(ArticleQueryDTO dto);		
}
