package com.pnu.lab.control.labcontrol.utils;

import com.pnu.lab.control.labcontrol.constant.Role;
import com.pnu.lab.control.labcontrol.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {

    public static final String ROLE_PREFIX = "ROLE_";

    public static String getUserId() {
        return getUserInfo().getId();
    }

    public static boolean hasRole(Role role) {
        return getAuthentication().getAuthorities()
                .contains(new SimpleGrantedAuthority(ROLE_PREFIX + role.name()));
    }

    public static User getUserInfo() {
        return (User) getAuthentication().getPrincipal();
    }

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
