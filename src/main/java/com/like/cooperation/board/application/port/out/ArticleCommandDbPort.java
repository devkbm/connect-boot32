package com.like.cooperation.board.application.port.out;

import java.util.Optional;

import com.like.cooperation.board.domain.Article;

public interface ArticleCommandDbPort {
	Optional<Article> select(Long id);
	
	void save(Article entity);	
	
	void delete(Long id);
}
