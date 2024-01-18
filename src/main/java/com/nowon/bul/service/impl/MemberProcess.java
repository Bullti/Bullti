package com.nowon.bul.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nowon.bul.domain.dto.MemberDTO;
import com.nowon.bul.domain.entity.MemberRepository;
import com.nowon.bul.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberProcess implements MemberService{

	private final MemberRepository memberRepo;
	private final PasswordEncoder passEncoder;
	
	@Override
	public void save(MemberDTO dto) {
		memberRepo.save(dto.toEntity(passEncoder));
	}

}
