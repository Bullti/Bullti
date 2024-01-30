package com.nowon.bul.domain.entity.approval;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowon.bul.domain.entity.member.Member;

public interface ApprovalDocRepository extends JpaRepository<ApprovalDoc, Long>{

	List<ApprovalDoc> findAllByMember(Member member);
}
