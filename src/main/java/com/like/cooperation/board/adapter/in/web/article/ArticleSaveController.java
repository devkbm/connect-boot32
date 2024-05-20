package com.like.cooperation.board.adapter.in.web.article;

import jakarta.validation.Valid;

import static com.like.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.like.cooperation.board.application.port.in.article.ArticleSaveUseCase;
import com.like.cooperation.board.dto.ArticleSaveDTO;
import com.like.cooperation.board.dto.ArticleSaveMultipartDTO;
import com.like.core.message.MessageUtil;

@Controller
public class ArticleSaveController {	
					
	ArticleSaveUseCase useCase;	
	
	public ArticleSaveController(ArticleSaveUseCase useCase) {				
		this.useCase = useCase;
	}	
			
	@PostMapping("/api/grw/board/article_multipart")
	@ResponseBody
	public ResponseEntity<?> saveArticleWithMultiPartFile(ArticleSaveMultipartDTO dto) throws Exception {													
											
		useCase.save(dto);											
		
		return toList(null, MessageUtil.getSaveMessage(1));
	}	
	
	@PostMapping("/api/grw/board/article")
	@ResponseBody
	public ResponseEntity<?> saveArticleJson(@RequestBody @Valid ArticleSaveDTO dto) throws Exception {															
										
		useCase.save(dto);											
		
		return toList(null, MessageUtil.getSaveMessage(1));
	}	
	
}
