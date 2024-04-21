package com.like.cooperation.board.application.port.out;

import java.util.List;

import com.like.cooperation.board.application.port.dto.ArticleQueryDTO;
import com.like.cooperation.board.application.port.dto.ArticleResponseDTO;

public interface ArticleQueryByListDbPort {

	List<ArticleResponseDTO> getList(ArticleQueryDTO dto);		
}
