package com.kyuwon.spring.domain.user.domain;

import com.kyuwon.spring.domain.user.model.Address;
import com.kyuwon.spring.domain.user.model.Authority;
import com.kyuwon.spring.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Table(name = "user_account", indexes = {
        @Index(columnList = "email")
})
@EqualsAndHashCode(of = {"id"}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserAccount extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter @Column(nullable = false, length = 50) private String email;
    @Setter @Column(nullable = false, length = 200) private String password;
    @Setter @Column(nullable = false, length = 30) private String name;
    @Embedded @Column(length = 300) private Address address;
    @ElementCollection(fetch = FetchType.EAGER) @Column(nullable = false, length = 50) @Enumerated(EnumType.STRING) private Set<Authority> authorities;
    @Setter @Column(length=300) private String refreshToken;
    @Builder
    public UserAccount(String email, String password, String name, Address address, Set<Authority> authorities, String refreshToken) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.authorities = authorities;
    }
}