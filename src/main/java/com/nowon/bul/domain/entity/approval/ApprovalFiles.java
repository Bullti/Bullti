package com.nowon.bul.domain.entity.approval;


import com.nowon.bul.domain.dto.approval.AppResponseFilesDTO;
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
@Table(name = "approval_files")
@Entity
public class ApprovalFiles {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "approval_files_no")
	private Long no; 
	
	private String orgName;
	
	private String newName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ap_doc_no")
	private ApprovalDoc approvalDoc;
	
	
	public AppResponseFilesDTO toAppResponseFilesDTO() {
		return AppResponseFilesDTO.builder()
				.orgName(orgName)
				.newName(newName)
				.build();
	}
}
