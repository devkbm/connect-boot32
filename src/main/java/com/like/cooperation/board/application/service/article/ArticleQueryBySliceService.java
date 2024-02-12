package com.like.cooperation.board.application.service.article;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.board.application.port.dto.ArticleQueryDTO;
import com.like.cooperation.board.application.port.dto.ArticleResponseDTO;
import com.like.cooperation.board.application.port.in.article.ArticleQueryBySliceUseCase;
import com.like.cooperation.board.application.port.out.ArticleQueryBySliceDbPort;

@Transactional(readOnly = true)
@Service
public class ArticleQueryBySliceService implements ArticleQueryBySliceUseCase {

	ArticleQueryBySliceDbPort dbPort;
	
	ArticleQueryBySliceService(ArticleQueryBySliceDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public Slice<ArticleResponseDTO> getAritlceSlice(ArticleQueryDTO dto, Pageable pageable) { 
		return this.dbPort.getAritlceSlice(dto, pageable);
	}

	
}
