package com.like.system.systemcode.adapter.out.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.like.system.systemcode.adapter.out.persistence.jpa.entity.JpaBizCode;
import com.like.system.systemcode.adapter.out.persistence.jpa.entity.JpaBizCodeId;

public interface BizCodeRepository extends JpaRepository<JpaBizCode, JpaBizCodeId> {

}
