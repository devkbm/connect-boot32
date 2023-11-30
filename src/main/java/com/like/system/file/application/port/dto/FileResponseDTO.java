package com.like.system.file.application.port.dto;

import com.like.system.file.adapter.out.file.FileServerRepository;
import com.like.system.file.domain.FileInfo;

public record FileResponseDTO(
		String uid,
		String name,
		String status,
		String response,
		String url
		) {
			
	public static FileResponseDTO convert(FileInfo info) {
		
		// url 예시 - http://localhost:8090/common/file/"+info.getPkFile()
		
		return new FileResponseDTO(info.getId().toString()
								  ,info.getFileName()
								  ,"done"
								  ,"success"
								  ,FileServerRepository.fileDownLoadUrl+info.getId().toString());
	}
}
