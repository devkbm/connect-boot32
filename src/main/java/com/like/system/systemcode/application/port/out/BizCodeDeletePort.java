package com.like.system.systemcode.application.port.out;

public interface BizCodeDeletePort {
	void delete(String organizationCode, String typeId, String code);
}
