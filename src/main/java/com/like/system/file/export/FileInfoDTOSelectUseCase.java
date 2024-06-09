package com.like.system.file.export;

import java.util.List;

public interface FileInfoDTOSelectUseCase {

	FileInfoDTO select(String id);
	
	List<FileInfoDTO> select(List<String> ids);
}
