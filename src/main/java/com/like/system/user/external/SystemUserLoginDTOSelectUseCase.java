package com.like.system.user.external;

public interface SystemUserLoginDTOSelectUseCase {

	SystemUserLoginDTO get(String companyCode, String staffNo);
}
