package com.nowon.bul.domain.entity.approval;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowon.bul.domain.entity.member.Member;

public interface ApprovalRepository extends JpaRepository<Approval, Long>{

	List<Approval> findAllByMemberAndResult(Member member, Result result);

}
