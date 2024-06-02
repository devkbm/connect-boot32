package com.like.cooperation.board.adapter.in.web.article;

import jakarta.servlet.http.HttpSession;

import static com.like.core.web.util.ResponseEntityUtil.toOne;

import java.util.Base64;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.like.cooperation.board.application.port.in.article.ArticleSelectUseCase;
import com.like.cooperation.board.dto.ArticleResponseDTO;
import com.like.core.message.MessageUtil;

@Controller
public class ArticleSelectController {	
			
	ArticleSelectUseCase useCase;
	
	public ArticleSelectController(ArticleSelectUseCase useCase) {		
		this.useCase = useCase;
	}		
	
	@GetMapping("/api/grw/board/article/{id}")
	public ResponseEntity<?> getArticle(@PathVariable String id, HttpSession session) {						
					
		Long articleId = Long.parseLong(new String(Base64.getDecoder().decode(id))); 
		ArticleResponseDTO response =  useCase.select(articleId);				
		
		return toOne(response, MessageUtil.getQueryMessage(response == null ? 0 : 1));
	}
	
	@GetMapping("/api/grw/board/article/hitcnt")
	public ResponseEntity<?> updateArticleHitCnt(@RequestParam Long id,
												 @RequestParam String userId) {								
				
		useCase.plusHitCount(id, userId);			
										
		return toOne(null, String.format("%d건 업데이트 하였습니다.", 1));
	}	
	
}
