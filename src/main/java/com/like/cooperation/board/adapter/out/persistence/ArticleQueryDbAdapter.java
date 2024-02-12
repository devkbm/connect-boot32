package com.like.cooperation.board.adapter.out.persistence;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.like.cooperation.board.adapter.out.persistence.jpa.repository.ArticleJpaRepository;
import com.like.cooperation.board.adapter.out.persistence.mybatis.BoardMapper;
import com.like.cooperation.board.application.port.dto.ArticleQueryDTO;
import com.like.cooperation.board.application.port.dto.ArticleResponseDTO;
import com.like.cooperation.board.application.port.out.ArticleQueryByListDbPort;

@Repository
public class ArticleQueryDbAdapter implements ArticleQueryByListDbPort {
	
	ArticleJpaRepository repository;
	BoardMapper boardMapper;	
	
	ArticleQueryDbAdapter(ArticleJpaRepository repository
			             ,BoardMapper boardMapper) {
		this.repository = repository;
		this.boardMapper = boardMapper;
	}
	
	@Override
	public List<ArticleResponseDTO> getList(ArticleQueryDTO dto) {

		
		return this.repository.findAll(dto.getBooleanBuilder(), Sort.by(Sort.Direction.DESC, "articleId"))
							  .stream()
							  .map(e -> ArticleResponseDTO.toDTO(e))
							  .toList();
	}

}
