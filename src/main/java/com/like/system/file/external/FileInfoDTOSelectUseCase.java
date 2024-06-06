package com.like.system.file.external;

import java.util.List;

public interface FileInfoDTOSelectUseCase {

	FileInfoDTO select(String id);
	
	List<FileInfoDTO> select(List<String> ids);
}
