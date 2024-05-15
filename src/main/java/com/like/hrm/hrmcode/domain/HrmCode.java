package com.like.hrm.hrmcode.domain;

import com.like.core.jpa.domain.AbstractAuditEntity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "HRMCODE")
public class HrmCode extends AbstractAuditEntity {			

	@EmbeddedId		
	HrmCodeId id;		
	
	@Column(name="CODE_NM")
	String codeName;
		
	@Column(name="USE_YN")
	boolean useYn = true;

	@Column(name="PRT_SEQ")
	Integer sequence;
	
	@Column(name="CMT")
	String comment;			
	
	@Column(name="THE_1_ADD_INFO")
	String the1AddInfo;
	
	@Column(name="THE_2_ADD_INFO")
	String the2AddInfo;
	
	@Column(name="THE_3_ADD_INFO")
	String the3AddInfo;
	
	@Column(name="THE_4_ADD_INFO")
	String the4AddInfo;
	
	@Column(name="THE_5_ADD_INFO")
	String the5AddInfo;
		
	@Transient
	String relCode;
		
	public HrmCode(HrmCodeId id						 
			 	  ,String codeName
			 	  ,boolean useYn
			 	  ,Integer sequence
			 	  ,String comment) {		
		this.id = id;				
		this.codeName = codeName;		
		this.useYn = useYn;
		this.sequence = sequence;
		this.comment = comment;
	}
		
	public void modify(String codeName
					  ,boolean useYn
					  ,Integer sequence
					  ,String comment ) {	
		this.codeName = codeName;
		this.useYn = useYn;
		this.sequence = sequence;
		this.comment = comment;
	}	
	
}
