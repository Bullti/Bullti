package com.nowon.bul.domain.entity.member;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowon.bul.domain.dto.approval.ApprovalMemberDTO;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findById(String memberId);

	List<Member> findByDept_deptName(String deptName);

	boolean existsById(String id);

	List<Member> findByRank(Rank rank);
}
