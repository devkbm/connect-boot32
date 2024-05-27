package com.like.system.dept.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.like.core.jpa.vo.LocalDatePeriod;
import com.like.system.dept.domain.DeptHierarchy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DeptHierarchyNgZorro {
	
	/* DeptHierarchy Property */
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
	/* DeptHierarchy Property */
	
		
	List<DeptHierarchyNgZorro> children;
	
	/**
	 * NzTreeNode property 
	 */
	String title;
	
	String key;
			
	@JsonProperty(value="isLeaf") 
	boolean isLeaf;
	
	public static DeptHierarchyNgZorro build(DeptHierarchy dto) {
		DeptHierarchyNgZorro rec = new DeptHierarchyNgZorro();
		
		Optional<LocalDatePeriod> period = Optional.ofNullable(dto.getPeriod());
		
		rec.companyCode = dto.getCompanyCode();
		rec.parentDeptCode = dto.getParentDeptCode();
		rec.deptCode = dto.getDeptCode();
		rec.deptNameKorean = dto.getDeptAbbreviationKorean();
		rec.deptAbbreviationKorean = dto.getDeptAbbreviationKorean();
		rec.deptNameEnglish = dto.getDeptAbbreviationEnglish();
		rec.deptAbbreviationEnglish = dto.getDeptAbbreviationEnglish();
		rec.fromDate = period.map(LocalDatePeriod::getFrom).orElse(null);
		rec.toDate = period.map(LocalDatePeriod::getTo).orElse(null);
		rec.seq = dto.getSeq();
		rec.comment = dto.getComment();
		
		rec.title = dto.getDeptNameKorean();
		rec.key = dto.getDeptCode();
		rec.isLeaf = dto.isLeaf();
		
		return rec;
	}

	public void setChildren(List<DeptHierarchyNgZorro> children) {
		this.children = children;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	
}
