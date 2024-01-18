package com.nowon.bul.domain.dto;

import java.time.LocalDate;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.nowon.bul.domain.entity.Member;
import com.nowon.bul.domain.entity.Rank;
import com.nowon.bul.domain.entity.Role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class MembersaveDTO {

	private String id;
	private String name;
	private String tel;
	private LocalDate birthDate;
	private LocalDate joinCompanyDate;
	private String street;
	private String detail;
	
	
	public Member toEntity(PasswordEncoder passEncoder) {
		return Member.builder()
				.id(id)
				.password(passEncoder.encode(id))
				.name(name)
				.phone(tel)
				.rank(Rank.Assistant)
				.birthDate(birthDate)
				.joinCompanyDate(joinCompanyDate)
				.adress(street + " " + detail)
				.build().addRole(Role.USER);
	}
	
	public Member toList() {
		return Member.builder()
				.id(id)
				.name(name)
				.rank(Rank.Assistant)
				.phone(tel)
				.joinCompanyDate(joinCompanyDate)
				.build();
 }
}
