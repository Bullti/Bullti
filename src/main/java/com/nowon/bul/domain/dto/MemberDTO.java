package com.nowon.bul.domain.dto;

import java.time.LocalDate;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.nowon.bul.domain.entity.Member;
import com.nowon.bul.domain.entity.Role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class MemberDTO {

	private String id;
	private String name;
	private String tel;
	private LocalDate birthDate;
	private LocalDate joinCompanyDate;
	
	
	public Member toEntity(PasswordEncoder passEncoder) {
		return Member.builder()
				.id(id)
				.password(passEncoder.encode(id))
				.name(name)
				.phone(tel)
				.birthDate(birthDate)
				.joinCompanyDate(joinCompanyDate)
				.build().addRole(Role.USER);
	}
}
