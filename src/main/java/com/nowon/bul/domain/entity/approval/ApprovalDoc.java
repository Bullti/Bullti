package com.nowon.bul.domain.entity.approval;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.nowon.bul.domain.dto.approval.ApprovalDraftDTO;
import com.nowon.bul.domain.dto.approval.ApprovalDraftListDTO;
import com.nowon.bul.domain.dto.approval.ApprovalWaitDTO;
import com.nowon.bul.domain.dto.approval.ApprovalWaitListDTO;
import com.nowon.bul.domain.entity.member.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	
	@Column(unique = true, name = "ap_doc_name")
	private String docName;
	
	@OneToMany(mappedBy = "apDoc")
	private List<Approval> approval;
	
	private String title;
	
	@Column(columnDefinition = "longtext")
	private String content;
	
	@Enumerated(EnumType.STRING)
	private State state;
	
	@CreationTimestamp
	@Column(columnDefinition = "timestamp(6) null")
	private LocalDateTime created_date;
	
	@UpdateTimestamp
	@Column(columnDefinition = "timestamp(6) null")
	public LocalDateTime updated_date;
	
	
	
	
	public void setDocName() {
		int currentYear = LocalDate.now().getYear();
		String number = String.format("%05d", this.no);
		this.docName = "(주)불티" + currentYear +"-"+number;
	}
	
	
	public ApprovalDraftDTO toApprovalDraftDTO() {
		return ApprovalDraftDTO.builder()
				.deptName(member.getDept().getDeptName())
				.rank(member.getRank().getRankName())
				.memberName(member.getName())
				.title(title)
				.content(content)
				.approvalLine(approval.stream().map(Approval::toApprovalLineDTO).collect(Collectors.toList()))
				.build();
	}
	
	public ApprovalDraftListDTO toDraftListDTO() {
		return ApprovalDraftListDTO.builder()
				.createdDate(created_date)
				.title(title)
				.docName(docName)
				.state(state.getSateName())
				.docNo(no)
				.build();
	}
	
	public ApprovalWaitDTO toApprovalWaitDTO() {
		return ApprovalWaitDTO.builder()
				.deptName(member.getDept().getDeptName())
				.rank(member.getRank().getRankName())
				.memberName(member.getName())
				.title(title)
				.content(content)
				.approvalLine(approval.stream().map(Approval::toApprovalLineDTO).collect(Collectors.toList()))
				.build();
	}
	
	public ApprovalWaitListDTO toWaitListDTO() {
		return ApprovalWaitListDTO.builder()
				.createdDate(created_date)
				.title(title)
				.writer(member.getName())
				.docName(docName)
				.docNo(no)
				.build();
	}
}
