package com.like.cooperation.board.application.port.dto;

public record ArticleListDTO(
	String boardId,
	String articleId,
	String writerName,
	String title,
	int hitCount,
	Boolean editable,
	Boolean isAttachedFile,
    Integer fileCount) {	
}
