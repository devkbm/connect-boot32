package com.like.cooperation.board.application.service.article;

import org.springframework.stereotype.Service;

import com.like.cooperation.board.application.port.in.article.ArticleDeleteUseCase;
import com.like.cooperation.board.application.port.out.ArticleCommandDbPort;

@Service
public class ArticleDeleteService implements ArticleDeleteUseCase {

	ArticleCommandDbPort port;
	
	ArticleDeleteService(ArticleCommandDbPort port) {
		this.port = port;
	}
	
	@Override
	public void delete(Long articleId) {
		this.port.delete(articleId);		
	}

}
