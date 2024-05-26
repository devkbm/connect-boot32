package com.like.system.dept.domain;

import java.util.List;

import com.like.core.jpa.vo.LocalDatePeriod;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeptHierarchy {

	public String companyCode;	
	
	public String deptCode;
		
	public String deptNameKorean;
	
	public String deptAbbreviationKorean;
	
	public String deptNameEnglish;
	
	public String deptAbbreviationEnglish;
	
	public LocalDatePeriod period;
	
	public Integer seq;
	
	public String comment;
	
	public String parentDeptCode;
	
	public List<DeptHierarchy> children;
	
	public boolean isLeaf;
		
	public DeptHierarchy() {}
	
	/**
	 * @param companyCode
	 * @param deptCode
	 * @param deptNameKorean
	 * @param deptAbbreviationKorean
	 * @param deptNameEnglish
	 * @param deptAbbreviationEnglish
	 * @param period
	 * @param seq
	 * @param comment
	 * @param parentDeptCode
	 * @param children
	 */
	public DeptHierarchy(String companyCode, 
						 String deptCode, 
						 String deptNameKorean,
						 String deptAbbreviationKorean, 
						 String deptNameEnglish, 
						 String deptAbbreviationEnglish,
						 LocalDatePeriod period, 
						 Integer seq, 
						 String comment, 
						 String parentDeptCode, 
						 List<DeptHierarchy> children) {
		this.companyCode = companyCode;		
		this.deptCode = deptCode;
		this.deptNameKorean = deptNameKorean;
		this.deptAbbreviationKorean = deptAbbreviationKorean;
		this.deptNameEnglish = deptNameEnglish;
		this.deptAbbreviationEnglish = deptAbbreviationEnglish;
		this.period = period;
		this.seq = seq;
		this.comment = comment;
		
		this.parentDeptCode = parentDeptCode;
		this.children = children;				
	}
	
	public String parentDeptCode() {
		return this.parentDeptCode;
	}

	public void setChildren(List<DeptHierarchy> children) {
		this.children = children;
	}
	
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	
}
