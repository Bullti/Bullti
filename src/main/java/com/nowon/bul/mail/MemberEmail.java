package com.nowon.bul.mail;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.nowon.bul.department.DeEntity;
import com.nowon.bul.domain.entity.approval.ApprovalDoc;
import com.nowon.bul.domain.entity.member.Member;
import com.nowon.bul.domain.entity.member.Rank;
import com.nowon.bul.domain.entity.member.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member_email")
@Entity
public class MemberEmail {
	
	@Id
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_email_no")
	private Member member;
	private String email;
	private String password;
	
}
