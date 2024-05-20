package com.like.cooperation.board.application.service.article;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.board.application.port.in.article.ArticleQueryBySliceUseCase;
import com.like.cooperation.board.application.port.out.ArticleQueryBySliceDbPort;
import com.like.cooperation.board.dto.ArticleListDTO;
import com.like.cooperation.board.dto.ArticleQueryDTO;

@Transactional(readOnly = true)
@Service
public class ArticleQueryBySliceService implements ArticleQueryBySliceUseCase {

	ArticleQueryBySliceDbPort dbPort;
	
	ArticleQueryBySliceService(ArticleQueryBySliceDbPort dbPort) {
		this.dbPort = dbPort;
	}
		
	@Override
	public Slice<ArticleListDTO> getAritlceSlice(String userId, ArticleQueryDTO dto, Pageable pageable) {
		return this.dbPort.getAritlceSlice(userId, dto, pageable);
	}

	
}
