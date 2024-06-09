package com.like.cooperation.board.application.service.article;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.like.cooperation.board.application.port.in.article.ArticleSelectUseCase;
import com.like.cooperation.board.application.port.out.ArticleCommandDbPort;
import com.like.cooperation.board.application.port.out.ArticleUserHitCountDbPort;
import com.like.cooperation.board.domain.Article;
import com.like.cooperation.board.dto.ArticleResponseDTO;
import com.like.system.file.export.FileInfoDTO;
import com.like.system.file.export.FileInfoDTOSelectUseCase;

@Service
public class ArticleSelectService implements ArticleSelectUseCase {

	ArticleCommandDbPort dbPort;
	ArticleUserHitCountDbPort userHitCountPort;
	FileInfoDTOSelectUseCase fileSelectUseCase;
	
	ArticleSelectService(ArticleCommandDbPort dbPort
						,ArticleUserHitCountDbPort userHitCountPort
						,FileInfoDTOSelectUseCase fileSelectUseCase) {
		this.dbPort = dbPort;
		this.userHitCountPort = userHitCountPort;
		this.fileSelectUseCase = fileSelectUseCase;
	}
	
	@Override
	public ArticleResponseDTO select(Long id) {
		Article entity = this.dbPort.select(id).orElse(null);
		List<FileInfoDTO> fileList = Collections.emptyList();
		
		if (entity.getAttachedFileInfoList() != null) {
			fileList = fileSelectUseCase.select(entity.getFileIds());
		}
		
		return ArticleResponseDTO.toDTO(this.dbPort.select(id).orElse(null), fileList);
	}

	@Override
	public void plusHitCount(Long articleId, String userId) {
		Article article = this.dbPort.select(articleId)
				  					 .orElseThrow(() -> new IllegalArgumentException("존재 하지 않은 게시글입니다."));

		article.updateHitCnt();
		
		this.userHitCountPort.plusHitCount(article, userId);
		
	}

}
