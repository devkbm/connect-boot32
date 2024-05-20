package com.like.cooperation.board.dto;

public record ArticleListDTO(
	String boardId,
	String articleId,
	String writerName,
	String writerImage,
	String title,
	int hitCount,
	Boolean editable,
	Boolean isAttachedFile,
    Integer fileCount,
    Boolean isRead) {	
}

