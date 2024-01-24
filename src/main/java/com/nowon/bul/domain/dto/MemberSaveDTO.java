package com.nowon.bul.domain.dto;

import java.time.LocalDate;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.nowon.bul.department.DeEntity;
import com.nowon.bul.domain.entity.member.Member;
import com.nowon.bul.domain.entity.member.Rank;
import com.nowon.bul.domain.entity.member.Role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class MemberSaveDTO {

	private String id;
	private String name;
	private Rank rank;
	private String tel;
	private int deptId; 
	private LocalDate birthDate;
	private String adress;
	private String addressDetail;
	private LocalDate joinCompanyDate;
	private String newName;
	
	
	public Member toEntity(PasswordEncoder passEncoder, String profileUrl, DeEntity dept) {
		return Member.builder()
				.id(id)
				.password(passEncoder.encode(id))
				.name(name)
				.profile(profileUrl)
				.phone(tel)
				.rank(rank)
				.dept(dept)
				.adress(adress+addressDetail)
				.birthDate(birthDate)
				.joinCompanyDate(joinCompanyDate)
				.build().addRole(Role.USER);
	}
	
	public Member toList() {
		return Member.builder()
				.id(id)
				.name(name)
				.rank(Rank.Assistant)
				.phone(tel)
				.joinCompanyDate(joinCompanyDate)
				//.resignationDate(resignationDate) //퇴사일받을거
				.build();
 }
}
