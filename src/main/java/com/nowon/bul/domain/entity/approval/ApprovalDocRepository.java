package com.nowon.bul.domain.entity.approval;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nowon.bul.domain.entity.member.Member;

public interface ApprovalDocRepository extends JpaRepository<ApprovalDoc, Long>{

	Page<ApprovalDoc> findAllByMember(Member member, Pageable pageable);
}
