package com.nowon.bul.utils;

import org.springframework.security.core.Authentication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenUtils {

    public static long nameToLong(Authentication auth) {
        long result = 0L;
        try {
            result = Long.parseLong(auth.getName());
            if ( result == 0L) {
            	result = 0/0;
            }
        } catch (Exception e) {
            log.debug("Authentication object error", e);
        }
        return result;
    }
}
