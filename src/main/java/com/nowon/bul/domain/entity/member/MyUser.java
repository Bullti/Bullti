package com.nowon.bul.domain.entity.member;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class MyUser extends User{

	private static final long serialVersionUID = 1L;
	private long memberNo;
	private String profile;
	private String memberName;
	private String memberRank;
	private String memberDept; 
	
	private MyUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public MyUser(Member member, Set<SimpleGrantedAuthority> grnatedAuthority) {
		this(member.getId(), member.getPassword(), grnatedAuthority);
		memberNo = member.getNo();
		profile = member.getProfile();
		memberName = member.getName();
		memberRank = member.getRank().getRankName();
		memberDept = member.getDept().getDeptName();
	}
	
	
}
