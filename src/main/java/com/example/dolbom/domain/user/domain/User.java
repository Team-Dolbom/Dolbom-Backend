package com.example.dolbom.domain.user.domain;


import com.example.dolbom.global.enums.Authority;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Entity
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
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

    @Column(unique = true)
    private String nickname;

    @Column
    private String callNumber;

    @ColumnDefault("b'0'")
    private Boolean certification;

    @Builder
    public User(Long id, String accountId, String password, String callNumber, Authority authority, String nickname, Boolean certification){
        this.id = id;
        this.accountId = accountId;
        this.password = password;
        this.callNumber = callNumber;
        this.authority = authority;
        this.nickname = nickname;
        this.certification = certification;
    }

    public void updatePass(String password){
        this.password = password;
    }

    public void updateProfile(String nickname, Boolean certification){
        this.nickname = nickname;
        this.certification = certification;
    }
}
