package com.like.system.dept.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.like.core.jpa.vo.LocalDatePeriod;
import com.like.system.dept.domain.DeptHierarchy;
import com.querydsl.core.annotations.QueryProjection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeptHierarchyNgZorro {
	
	String parentDeptCode;
	
	String companyCode;	
	
	String deptCode;		
			
	String deptNameKorean;		
	
	String deptAbbreviationKorean;
	
	String deptNameEnglish;
	
	String deptAbbreviationEnglish;
						
	LocalDate fromDate;
			
	LocalDate toDate;
	
	Integer seq;
	
	String comment;
	
	@Singular
	List<DeptHierarchyNgZorro> children;
	
	/**
	 * NzTreeNode property 
	 */
	String title;
	
	String key;
			
	@JsonProperty(value="isLeaf") 
	boolean isLeaf;

	@QueryProjection
	public DeptHierarchyNgZorro(String parentDeptCode, String companyCode, String deptCode, String deptNameKorean, String deptAbbreviationKorean,
			String deptNameEnglish, String deptAbbreviationEnglish, LocalDatePeriod period,
			Integer seq, String comment) {				
		this.parentDeptCode = parentDeptCode;
		this.companyCode = companyCode;		
		this.deptCode = deptCode;
		this.deptNameKorean = deptNameKorean;
		this.deptAbbreviationKorean = deptAbbreviationKorean;
		this.deptNameEnglish = deptNameEnglish;
		this.deptAbbreviationEnglish = deptAbbreviationEnglish;
		this.fromDate = period.getFrom();
		this.toDate = period.getTo();
		this.seq = seq;
		this.comment = comment;
		
		this.title 	= this.deptNameKorean;
		this.key 	= this.deptCode;			
	}
	
	public static DeptHierarchyNgZorro build(DeptHierarchy dto) {
		DeptHierarchyNgZorro rec = new DeptHierarchyNgZorro();
		
		rec.companyCode = dto.getCompanyCode();
		rec.parentDeptCode = dto.getParentDeptCode();
		rec.deptCode = dto.getCompanyCode();
		rec.deptNameKorean = dto.getDeptAbbreviationKorean();
		rec.deptAbbreviationKorean = dto.getDeptAbbreviationKorean();
		rec.deptNameEnglish = dto.getDeptAbbreviationEnglish();
		rec.deptAbbreviationEnglish = dto.getDeptAbbreviationEnglish();
		rec.fromDate = dto.getPeriod().getFrom();
		rec.toDate = dto.getPeriod().getTo();
		rec.seq = dto.getSeq();
		rec.comment = dto.getComment();
		
		rec.title = dto.getDeptNameKorean();
		rec.key = dto.getDeptCode();
		
		return rec;
	}

	public void setChildren(List<DeptHierarchyNgZorro> children) {
		this.children = children;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	
}
