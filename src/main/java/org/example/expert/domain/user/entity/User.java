package org.example.expert.domain.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.common.entity.Timestamped;
import org.example.expert.domain.user.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User extends Timestamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    private String nickName;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public User(String email, String password, String nickName,UserRole userRole) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.userRole = userRole;
    }

    private User(Long id, String email, String nickName,UserRole userRole) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
        this.userRole = userRole;
    }

    public static User fromAuthUser(AuthUser authUser) {
        GrantedAuthority grantedAuthority = authUser.getAuthorities().stream().findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유저 찾지 못함"));
        return new User(authUser.getUserId(), authUser.getEmail(), authUser.getNickName(), UserRole.of(grantedAuthority.getAuthority()));
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void updateRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
