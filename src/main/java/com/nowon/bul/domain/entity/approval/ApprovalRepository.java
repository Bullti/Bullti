package com.nowon.bul.domain.entity.approval;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowon.bul.domain.entity.member.Member;

public interface ApprovalRepository extends JpaRepository<Approval, Long>{

	List<Approval> findAllByMemberAndResult(Member member, Result result);

	Optional<Approval> findByMemberAndApDoc(Member member, ApprovalDoc approvalDoc);

	Optional<Approval> findByApDocAndOrder(ApprovalDoc approvalDoc, int order);

}
