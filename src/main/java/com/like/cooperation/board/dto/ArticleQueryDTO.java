package com.like.cooperation.board.dto;

import static org.springframework.util.StringUtils.hasText;

import java.util.Base64;

import org.springframework.util.StringUtils;

import com.like.cooperation.board.domain.QArticle;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

public record ArticleQueryDTO(
		String boardId,
		String title,
		String contents
		) {
	private static final QArticle qArticle = QArticle.article;
	
	public BooleanBuilder getBooleanBuilder() {
		BooleanBuilder builder = new BooleanBuilder();
		
		builder
			.and(qArticle.board.boardId.eq(base64ToLong(this.boardId)))
			.and(likeTitle(this.title))
			.and(likeContents(this.contents));											
		
		return builder;
	}
	
	private BooleanExpression likeTitle(String title) {
		return hasText(title) ? qArticle.content.title.like("%"+title+"%") : null;					
	}
	
	private BooleanExpression likeContents(String contents) {
		return hasText(contents) ? qArticle.content.contents.like("%"+contents+"%") : null;			
	}
	
	private Long base64ToLong(String str) {
    	return StringUtils.hasText(str) ? Long.parseLong(new String(Base64.getDecoder().decode(str))) : null;
    }
}
