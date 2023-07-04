package com.example.dolbom.domain.user.domain;


import com.example.dolbom.global.enums.Authority;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    @Size(min = 4, max = 15)
    private String accountId;

    @Column
    @NotNull
    @Size(min = 8)
    private String password;

    @Column
    @NotNull
    private Authority authority;

    @Column
    @NotNull
    private String nickname;

    @Column
    private Long callNumber;

    @Builder
    public User(Long id, String accountId, String password, Long callNumber, Authority authority, String nickname){
        this.id = id;
        this.accountId = accountId;
        this.password = password;
        this.callNumber = callNumber;
        this.authority = authority;
        this.nickname = nickname;
    }
}
