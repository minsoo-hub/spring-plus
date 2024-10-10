package org.example.expert.domain.common.dto;

import java.util.Collection;
import java.util.List;
import lombok.Getter;
import org.example.expert.domain.user.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
public class AuthUser {

    private final Long userId;
    private final String email;
    private final String nickName;
    private final Collection<? extends GrantedAuthority> authorities;

    public AuthUser(Long userId, String email, String nickName,UserRole role) {
        this.userId = userId;
        this.email = email;
        this.nickName = nickName;
        this.authorities = List.of(new SimpleGrantedAuthority(role.name()));
    }
}
