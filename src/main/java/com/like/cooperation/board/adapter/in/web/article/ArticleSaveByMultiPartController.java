package com.like.cooperation.board.adapter.in.web.article;

import static com.like.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.like.cooperation.board.application.port.in.article.ArticleSaveByMultiPartUseCase;
import com.like.cooperation.board.dto.ArticleSaveMultipartDTO;
import com.like.core.message.MessageUtil;

@Controller
public class ArticleSaveByMultiPartController {	
					
	ArticleSaveByMultiPartUseCase useCase;	
	
	public ArticleSaveByMultiPartController(ArticleSaveByMultiPartUseCase useCase) {				
		this.useCase = useCase;
	}	
			
	@PostMapping("/api/grw/board/article_multipart")
	@ResponseBody
	public ResponseEntity<?> saveArticleWithMultiPartFile(ArticleSaveMultipartDTO dto) throws Exception {													
											
		useCase.save(dto);											
		
		return toList(null, MessageUtil.getSaveMessage(1));
	}		
	
}
