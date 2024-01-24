package com.like.cooperation.board.application.port.in.article;

public interface ArticleUserHitCountUseCase {

	void plusHitCount(Long articleId, String userId);
}
