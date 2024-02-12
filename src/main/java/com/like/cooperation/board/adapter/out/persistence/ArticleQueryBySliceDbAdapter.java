package com.like.cooperation.board.adapter.out.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import com.like.cooperation.board.adapter.out.persistence.jpa.repository.ArticleJpaRepository;
import com.like.cooperation.board.adapter.out.persistence.mybatis.BoardMapper;
import com.like.cooperation.board.application.port.dto.ArticleQueryDTO;
import com.like.cooperation.board.application.port.dto.ArticleResponseDTO;
import com.like.cooperation.board.application.port.out.ArticleQueryBySliceDbPort;

@Repository
public class ArticleQueryBySliceDbAdapter implements ArticleQueryBySliceDbPort {

	ArticleJpaRepository repository;
	BoardMapper boardMapper;

	ArticleQueryBySliceDbAdapter(ArticleJpaRepository repository
            ,BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}
	
	@Override
	public Slice<ArticleResponseDTO> getAritlceSlice(ArticleQueryDTO dto, Pageable pageable) {
		Map<String, Object> params = new HashMap<>();	
		params.put("data", dto);
		params.put("pageable", pageable);
		
		List<ArticleResponseDTO> contents = boardMapper.getArticleList(params);
		
		// 첨부파일 추가
		for (ArticleResponseDTO content : contents) {
			content.addFileResponseDTO(repository);
		}			
		
		// 마지막 데이터인지 여부를 확인하기 위해 +1개를 조회한후 데이터 제거
		boolean hasNext = false;
		if (contents.size() > pageable.getPageSize()) {
			contents.remove(pageable.getPageSize());
			hasNext = true;
		}			
		return new SliceImpl<>(contents, pageable, hasNext);
	}
	
	
}
