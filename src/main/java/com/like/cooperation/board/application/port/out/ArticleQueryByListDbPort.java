package com.like.cooperation.board.application.port.out;

import java.util.List;

import com.like.cooperation.board.dto.ArticleQueryDTO;
import com.like.cooperation.board.dto.ArticleResponseDTO;

public interface ArticleQueryByListDbPort {

	List<ArticleResponseDTO> getList(ArticleQueryDTO dto);		
}
