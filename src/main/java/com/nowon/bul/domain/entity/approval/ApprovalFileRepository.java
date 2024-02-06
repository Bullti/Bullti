package com.nowon.bul.domain.entity.approval;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ApprovalFileRepository extends JpaRepository<ApprovalFiles, Long> {

	List<ApprovalFiles> findAllByApprovalDoc(ApprovalDoc approvalDoc);

}
