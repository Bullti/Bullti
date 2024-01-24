package com.nowon.bul.domain.entity.member;


import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.nowon.bul.department.DeEntity;
import com.nowon.bul.domain.dto.ApprovalMemberDTO;
import com.nowon.bul.domain.entity.approval.ApprovalDoc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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
@Table(name = "member")
@Entity
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_no")
	private long no;
	
	@Column(nullable = false, unique = true)
	private String id;
	
	@Column(nullable = false)
	private String password;
	
	private String email;
	
	@Column(nullable = false)
	private String name;
	
	private String phone;
	
	private String adress;
	
	private String profile;
	
	//생년월일
	private LocalDate birthDate;
	
	//입사일자
	private LocalDate joinCompanyDate;
	
	//퇴사일자
	private LocalDate resignationDate;

	//직급
	@Enumerated(EnumType.STRING)
	private Rank rank;
	
	
	@OneToMany(mappedBy = "member")
	private List<ApprovalDoc> approvalDoc;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_id")
	private DeEntity dept;

	
	/*
	 * UserEntity에 대응하는 권한을 나타내는 테이블을 만듬 *UserEntity내부 테이블에 생성되지 않음!!!
	 * 중복을 허용하지 않고 순서가 상관없으므로 Set Collection을 이용해서 만듬
	 */
	@Builder.Default
	@Enumerated(EnumType.STRING)  //선언하지 않으면 DB에 저장시 ordinal(숫자)로 저장됨
	@CollectionTable(name = "member_role")
	@ElementCollection(fetch = FetchType.EAGER)//1:n UserEntity에서만 접근가능한 내장테이블
	private Set<Role> memberRoles = new HashSet<>();
	
	
	/*
	 * 편의메서드
	 * Service에서 회원가입시 Role을 부여하기 편하기 위한 편의메서드
	 */
	public Member addRole(Role role) {
		memberRoles.add(role);
		return this;
	}
	
	public ApprovalMemberDTO toApprovalMemberDTO() {
		return ApprovalMemberDTO.builder().name(name).build();
	}
	
}