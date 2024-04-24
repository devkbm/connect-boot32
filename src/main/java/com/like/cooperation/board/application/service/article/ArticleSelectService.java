package com.like.cooperation.board.application.service.article;

import org.springframework.stereotype.Service;

import com.like.cooperation.board.application.port.dto.ArticleResponseDTO;
import com.like.cooperation.board.application.port.in.article.ArticleSelectUseCase;
import com.like.cooperation.board.application.port.out.ArticleCommandDbPort;
import com.like.cooperation.board.application.port.out.ArticleUserHitCountDbPort;
import com.like.cooperation.board.domain.Article;

@Service
public class ArticleSelectService implements ArticleSelectUseCase {

	ArticleCommandDbPort dbPort;
	ArticleUserHitCountDbPort userHitCountPort;
	
	ArticleSelectService(ArticleCommandDbPort dbPort, ArticleUserHitCountDbPort userHitCountPort) {
		this.dbPort = dbPort;
		this.userHitCountPort = userHitCountPort;
	}
	
	@Override
	public ArticleResponseDTO select(Long id) {
		return ArticleResponseDTO.toDTO(this.dbPort.select(id).orElse(null));
	}

	@Override
	public void plusHitCount(Long articleId, String userId) {
		Article article = this.dbPort.select(articleId)
				  					 .orElseThrow(() -> new IllegalArgumentException("존재 하지 않은 게시글입니다."));

		article.updateHitCnt();
		
		this.userHitCountPort.plusHitCount(article, userId);
		
	}

}
