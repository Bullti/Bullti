package com.nowon.bul.domain.entity.approval;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.nowon.bul.domain.entity.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "approval_doc")
@Entity
public class ApprovalDoc {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "ap_doc_no")
	private Long no; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_no")
	private Member member;
	
	private String title;
	
	@Column(columnDefinition = "longtext")
	private String content;
	
	@CreationTimestamp
	@Column(columnDefinition = "timestamp(6) null")
	private LocalDateTime created_date;
	
	@UpdateTimestamp
	@Column(columnDefinition = "timestamp(6) null")
	public LocalDateTime updated_date;
	
}
