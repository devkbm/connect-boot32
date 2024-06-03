package com.like.cooperation.board.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.util.StringUtils;

import com.like.cooperation.board.adapter.out.persistence.jpa.repository.ArticleJpaRepository;
import com.like.cooperation.board.domain.Article;
import com.like.core.util.SessionUtil;
import com.like.system.file.domain.FileInfo;
import com.like.system.file.dto.FileResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleResponseDTO {

	LocalDateTime createdDt;
	String createdBy;
	LocalDateTime modifiedDt;
	String modifiedBy;
	String userName;
	String boardId;
	String articleId;
	Long articleParentId;
	String title;
	String contents;
	String pwd;
	int hitCount;			
	Integer seq;
	Integer depth;	
	Boolean editable;
	Boolean isAttachedFile;
    Integer fileCount;	
	List<FileResponseDTO> fileList;
	
	
	public static ArticleResponseDTO toDTO(Article entity) {
		
    	if (entity == null) return null;
    	
		List<FileInfo> fileInfoList = entity.getAttachedFileInfoList();
		List<FileResponseDTO> responseList = convertFileResponseDTO(fileInfoList);
							
		return ArticleResponseDTO
				 .builder()
				 .createdDt(entity.getCreatedDt())
				 .createdBy(entity.getCreatedBy() == null ? null : entity.getCreatedBy().getLoggedUser())
				 .modifiedDt(entity.getModifiedDt())
				 .modifiedBy(entity.getModifiedBy() == null ? null : entity.getModifiedBy().getLoggedUser())
				 .articleId(toBase64Encode(entity.getArticleId()))
				 .articleParentId(entity.getArticleParentId())							 
				 .userName(entity.getUserName())
				 .boardId(toBase64Encode(entity.getBoard().getBoardId()))				
				 .title(entity.getContent().getTitle())
				 .contents(entity.getContent().getContents())
				 .fileList(responseList)			
				 .editable(entity.getEditable(SessionUtil.getUserId()))
				 .build();
	}
	
	public void addFileResponseDTO(ArticleJpaRepository repository) {
		
		Article entity = repository.findById(fromBase64ToDecode(this.articleId)).orElse(null);
		
		if (entity == null) return;
		
		List<FileInfo> fileInfoList = entity.getAttachedFileInfoList();
		
		this.fileList = convertFileResponseDTO(fileInfoList);
	}
	
	private static List<FileResponseDTO> convertFileResponseDTO(List<FileInfo> fileInfoList) {
    	List<FileResponseDTO> responseList = new ArrayList<>();	
    	
    	for (FileInfo fileInfo : fileInfoList) {							
			responseList.add(FileResponseDTO.convert(fileInfo));				
		}
    	
    	return responseList;
    }
	
	private static String toBase64Encode(Long id) {
		return Base64.getEncoder().encodeToString(id.toString().getBytes());
	}
	
    private Long fromBase64ToDecode(String str) {
    	return StringUtils.hasText(str) ? Long.parseLong(new String(Base64.getDecoder().decode(str))) : null;
    }
	
}
