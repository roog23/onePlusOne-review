package com.example.oneplusone.domain.auth.entity;

import com.example.oneplusone.domain.auth.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users") // 테이블명은 "users"
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(unique = true)
    private String loginId;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private String zipCode;

    private String address;

    private String detailAddress;

    public User(String nickname, String loginId, String password, UserRole userRole, String zipCode, String address, String detailAddress) {
        this.nickname = nickname;
        this.loginId = loginId;
        this.password = password;
        this.userRole = userRole;
        this.zipCode = zipCode;
        this.address = address;
        this.detailAddress = detailAddress;
    }
}