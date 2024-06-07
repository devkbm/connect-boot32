package com.like.cooperation.board.adapter.in.web.article;

import static com.like.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.like.cooperation.board.application.port.in.article.ArticleDeleteUseCase;
import com.like.cooperation.board.util.Base64Util;
import com.like.core.message.MessageUtil;

@Controller
public class ArticleDeleteController {	
			
	ArticleDeleteUseCase useCase;
		
	public ArticleDeleteController(ArticleDeleteUseCase useCase) {		
		this.useCase = useCase;
	}	
		
	@DeleteMapping("/api/grw/board/article/{id}")
	public ResponseEntity<?> deleteArticle(@PathVariable String id) {									
		
		useCase.delete(Base64Util.fromBase64Decode(id));							
		
		return toList(null, MessageUtil.getDeleteMessage(1));
	}			
	
}
