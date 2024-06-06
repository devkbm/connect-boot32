package com.like.system.file.external;

import com.like.system.file.adapter.out.file.FileServerRepository;
import com.like.system.file.domain.FileInfo;

public record FileResponseDTO(
		String uid,
		String name,
		String status,
		String response,
		String url
		) {
	
	public static FileResponseDTO convert(FileInfo entity) {
		
		// url 예시 - http://localhost:8090/common/file/"+info.getPkFile()
		
		return new FileResponseDTO(entity.getId().toString()
								  ,entity.getFileName()
								  ,"done"
								  ,"success"
								  ,FileServerRepository.fileDownLoadUrl+entity.getId().toString());
	}
	
	public static FileResponseDTO convert(FileInfoDTO info) {
		
		// url 예시 - http://localhost:8090/common/file/"+info.getPkFile()
		
		return new FileResponseDTO(info.fildId()
								  ,info.fileName()
								  ,"done"
								  ,"success"
								  ,FileServerRepository.fileDownLoadUrl+info.fildId());
	}
}
