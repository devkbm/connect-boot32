package com.like.cooperation.board.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.like.system.file.export.FileInfoDTO;

public class AttachedFileConverter {

	public static List<ArticleAttachedFile> convert(Article article, List<FileInfoDTO> fileInfoList) {
		
		if (fileInfoList == null || fileInfoList.isEmpty()) return Collections.emptyList();
		
		List<ArticleAttachedFile> list = new ArrayList<>();
		
		List<ArticleAttachedFile> existList = article.getAttachedFileInfoList();
		
		for (FileInfoDTO file : fileInfoList) {
								
			if (isNotExists(existList, file)) {
				list.add(new ArticleAttachedFile(article, file.fildId()));
			}
		}
		
		return list;				
	}
	
	private static boolean isNotExists(List<ArticleAttachedFile> list, FileInfoDTO file) {			
		return list.stream().noneMatch(e -> e.fileInfo.toString().equals(file.fildId()));
	}
	
}
