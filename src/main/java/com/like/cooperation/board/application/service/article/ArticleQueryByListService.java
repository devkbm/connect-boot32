package com.like.cooperation.board.application.service.article;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.board.application.port.dto.ArticleQueryDTO;
import com.like.cooperation.board.application.port.dto.ArticleResponseDTO;
import com.like.cooperation.board.application.port.in.article.ArticleQueryByListUseCase;
import com.like.cooperation.board.application.port.out.ArticleQueryByListDbPort;

@Transactional(readOnly = true)
@Service
public class ArticleQueryByListService implements ArticleQueryByListUseCase {

	ArticleQueryByListDbPort port;
	
	ArticleQueryByListService(ArticleQueryByListDbPort port) {
		this.port = port;
	}
	
	@Override
	public List<ArticleResponseDTO> getList(ArticleQueryDTO dto) {
		return this.port.getList(dto);
	}
	
}
