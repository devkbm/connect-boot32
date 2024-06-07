package com.like.cooperation.board.adapter.out.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.like.cooperation.board.adapter.out.persistence.jpa.repository.ArticleJpaRepository;
import com.like.cooperation.board.adapter.out.persistence.jpa.repository.BoardJpaRepository;
import com.like.cooperation.board.application.port.out.ArticleCommandDbPort;
import com.like.cooperation.board.domain.Article;

@Repository
public class ArticleDbAdapter implements ArticleCommandDbPort {
	ArticleJpaRepository repository;
	BoardJpaRepository boardRepository;	
	
	ArticleDbAdapter(ArticleJpaRepository repository
			        ,BoardJpaRepository boardRepository) {
		this.repository = repository;
		this.boardRepository = boardRepository;		
	}

	@Override
	public Optional<Article> select(Long id) {
		return this.repository.findById(id);
	}

	@Override
	public void save(Article entity) {
		this.repository.save(entity);
	}	
	
	@Override
	public void delete(Long id) {
		this.repository.deleteById(id);
		
	}	
	
}
