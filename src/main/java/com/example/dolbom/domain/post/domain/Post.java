package com.example.dolbom.domain.post.domain;

import com.example.dolbom.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@Entity
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String category;

    @Column(length = 30, nullable = false)
    private String title;

    @Column(length = 500, columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column
    private String author;

    @ColumnDefault("0")
    private Long view;

    @ColumnDefault("'대전'")
    private String region;

    @Builder
    public Post(String category, String title, String content, String author){
        this.category = category;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String category, String title, String content){
        this.category = category;
        this.title = title;
        this.content = content;
    }

}
