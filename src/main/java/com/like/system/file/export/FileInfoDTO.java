package com.like.system.file.export;

import com.like.system.file.domain.FileInfo;

import lombok.Builder;

@Builder
public record FileInfoDTO(
		String fildId,
		String appUrl,
		String userId,
		String contentType,
		String uuid,
		String path,
		String fileName,
		long size,
		long downloadCount
		) {

	public static FileInfoDTO toDTO(FileInfo entity) {
		return FileInfoDTO
				.builder()
				.fildId(entity.getId().toString())
				.appUrl(entity.getAppUrl())
				.userId(entity.getUserId())
				.contentType(entity.getContentType())
				.uuid(entity.getUuid())
				.path(entity.getPath())
				.fileName(entity.getFileName())
				.size(entity.getSize())
				.downloadCount(entity.getDownloadCount())
				.build();
	}
}
