package com.like.cooperation.board.adapter.in.web;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.like.cooperation.board.application.port.dto.ArticleQueryDTO;
import com.like.cooperation.board.application.port.dto.ArticleResponseDTO;
import com.like.cooperation.board.application.service.ArticleQueryService;
import com.like.system.core.message.MessageUtil;

@Controller
public class ArticleQueryController {

	private ArticleQueryService service;
	
	public ArticleQueryController(ArticleQueryService service) {
		this.service = service;		
	}
	
	@GetMapping("/api/grw/board/article")
	public ResponseEntity<?> getArticleList(ArticleQueryDTO dto) {
																			  						
		List<ArticleResponseDTO> list = service.getList(dto);		
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	
	@GetMapping("/api/grw/board/article_slice")
	public ResponseEntity<?> getArticleSlice(ArticleQueryDTO dto, Pageable pageable) {
																			  											
		Slice<ArticleResponseDTO> list = service.getAritlceSlice(dto, pageable);		//
		
		return new ResponseEntity<Slice<ArticleResponseDTO>>(list, HttpStatus.OK);		
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
