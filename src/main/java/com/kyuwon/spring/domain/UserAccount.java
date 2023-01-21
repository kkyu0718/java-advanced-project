package com.kyuwon.spring.domain;

import com.kyuwon.spring.model.Authority;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Table(name = "user_account", indexes = {
        @Index(columnList = "email")
})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserAccount extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter @Column(nullable = false, length = 50) private String email;
    @Setter @Column(nullable = false, length = 200) private String password;
    @Setter @Column(nullable = false, length = 30) private String name;
    @ElementCollection(fetch = FetchType.EAGER) @Column(nullable = false, length = 50) @Enumerated(EnumType.STRING) private Set<Authority> authorities;
}