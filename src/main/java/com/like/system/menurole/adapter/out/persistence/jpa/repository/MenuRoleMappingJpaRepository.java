package com.like.system.menurole.adapter.out.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.ListQuerydslPredicateExecutor;

import com.like.system.menurole.domain.MenuRoleMapping;
import com.like.system.menurole.domain.MenuRoleMappingId;

public interface MenuRoleMappingJpaRepository extends JpaRepository<MenuRoleMapping, MenuRoleMappingId>, ListQuerydslPredicateExecutor<MenuRoleMapping> {

}
