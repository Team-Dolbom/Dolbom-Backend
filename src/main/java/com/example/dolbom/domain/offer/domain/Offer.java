package com.example.dolbom.domain.offer.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Entity
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ColumnDefault("b'0'")
    private Boolean babySitter;

    @Column(length = 30, nullable = false)
    private String title;

    @Column(length = 200, columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(length = 200)
    private String intro;

    @Column(nullable = false)
    private String author;

    @ColumnDefault("b'0'")
    private Boolean certification;

    @ColumnDefault("0")
    private Long view;

    @ColumnDefault("'대전'")
    private String region;

    @Builder
    public Offer(Long id, Boolean babySitter, String title, String content, String intro, String author){
        this.id = id;
        this.babySitter = babySitter;
        this.title = title;
        this.content = content;
        this.intro = intro;
        this.author = author;
    }

    public void update(Boolean babySitter, String title, String content, String intro){
        this.babySitter = babySitter;
        this.title = title;
        this.content = content;
        this.intro = intro;
    }
}
