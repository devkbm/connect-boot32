package com.like.cooperation.board.adapter.out.persistence;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.like.cooperation.board.adapter.out.persistence.jpa.repository.ArticleJpaRepository;
import com.like.cooperation.board.adapter.out.persistence.mybatis.BoardMapper;
import com.like.cooperation.board.application.port.out.ArticleQueryByListDbPort;
import com.like.cooperation.board.dto.ArticleQueryDTO;
import com.like.cooperation.board.dto.ArticleResponseDTO;
import com.like.system.file.export.FileInfoDTOSelectUseCase;

@Repository
public class ArticleQueryDbAdapter implements ArticleQueryByListDbPort {
	
	ArticleJpaRepository repository;
	BoardMapper boardMapper;	
	FileInfoDTOSelectUseCase fileSelectUseCse;
	
	ArticleQueryDbAdapter(ArticleJpaRepository repository
			             ,BoardMapper boardMapper
			             ,FileInfoDTOSelectUseCase fileSelectUseCse) {
		this.repository = repository;
		this.boardMapper = boardMapper;
		this.fileSelectUseCse = fileSelectUseCse;
	}
	
	@Override
	public List<ArticleResponseDTO> getList(ArticleQueryDTO dto) {
		
		return this.repository.findAll(dto.getBooleanBuilder(), Sort.by(Sort.Direction.DESC, "articleId"))
							  .stream()
							  .map(e -> ArticleResponseDTO.toDTO(e, fileSelectUseCse.select(e.getFileIds())))
							  .toList();
	}

}
