package com.nowon.bul.utils;

import org.springframework.security.core.Authentication;

import com.nowon.bul.domain.entity.member.MyUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenUtils {

    public static long extractMemberNo(Authentication auth) {
        long result = 0L;
        try {
            result = ( (MyUser) auth.getPrincipal() ).getMemberNo();
        } catch (Exception e) {
            log.debug("Authentication object error", e);
        }
        return result;
    }
}
