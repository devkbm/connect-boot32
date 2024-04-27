package com.like.cooperation.board.adapter.in.web.article;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.like.cooperation.board.application.port.dto.ArticleListDTO;
import com.like.cooperation.board.application.port.dto.ArticleQueryDTO;
import com.like.cooperation.board.application.port.in.article.ArticleQueryBySliceUseCase;
import com.like.system.core.util.SessionUtil;

@Controller
public class ArticleQueryBySliceController {

	ArticleQueryBySliceUseCase useCase;
	
	ArticleQueryBySliceController(ArticleQueryBySliceUseCase useCase) {
		this.useCase = useCase;
	}	
	
	@GetMapping("/api/grw/board/article_slice")
	public ResponseEntity<?> getArticleSlice(ArticleQueryDTO dto, Pageable pageable) {
																			  											
		Slice<ArticleListDTO> list = useCase.getAritlceSlice(SessionUtil.getUserId(), dto, pageable);
		
		return new ResponseEntity<Slice<ArticleListDTO>>(list, HttpStatus.OK);		
	}
}
