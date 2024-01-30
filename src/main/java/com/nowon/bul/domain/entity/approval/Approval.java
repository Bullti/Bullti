package com.nowon.bul.domain.entity.approval;

import java.time.LocalDateTime;

import com.nowon.bul.domain.entity.member.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "approval")
@Entity
public class Approval {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "approval_no")
	private Long no; 
	
	
	//결재 순서
	@Column(name = "ap_order")
	private int order;
	
	private Result result;
	
	//반려 사유
	private String reason;
	
	//받은 일자
	@Column(columnDefinition = "timestamp(6)")
	private LocalDateTime receivedDate;
	
	//결재 일자
	@Column(columnDefinition = "timestamp(6) null")
	private LocalDateTime approvaledDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ap_member")
	private Member member;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ap_doc")
	private ApprovalDoc apDoc;
	
	
	
}
