package com.like.cooperation.board.adapter.in.web.article;

import static com.like.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.like.cooperation.board.application.port.in.article.ArticleQueryByListUseCase;
import com.like.cooperation.board.dto.ArticleQueryDTO;
import com.like.cooperation.board.dto.ArticleResponseDTO;
import com.like.core.message.MessageUtil;

@Controller
public class ArticleQueryByListController {

	ArticleQueryByListUseCase useCase;
	
	ArticleQueryByListController(ArticleQueryByListUseCase useCase) {
		this.useCase = useCase;		
	}
	
	@GetMapping("/api/grw/board/article")
	public ResponseEntity<?> getArticleList(ArticleQueryDTO dto) {
																			  						
		List<ArticleResponseDTO> list = useCase.getList(dto);		
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}	
	
	/*
	@GetMapping("/api/grw/board/article")
	public ResponseEntity<?> getArticleList(ArticleDTO.Search condition) {
																			  						
		List<ResponseArticle> list = service.getAritlceList(condition)
										    .stream()
										    .map(e -> ResponseArticle.converDTO((e)))
										    .toList();		
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	
	@GetMapping("/api/grw/board/article_slice")
	public ResponseEntity<?> getArticleSlice(ArticleDTO.Search condition, Pageable pageable) {
																			  											
		Slice<ResponseArticle> list = service.getAritlceSlice(condition, pageable);		//
		
		return new ResponseEntity<Slice<ResponseArticle>>(list, HttpStatus.OK);		
	}
	
	
	@GetMapping("/api/grw/board/article2")
	public ResponseEntity<?> getArticleList2(ArticleDTO.Search condition) {
																			  						
		List<?> list = service.getArticleListByMapper(condition);		
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	*/
}
