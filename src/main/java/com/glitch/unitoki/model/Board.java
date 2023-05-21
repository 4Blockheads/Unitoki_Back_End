package com.glitch.unitoki.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Getter
@Setter
@DynamicInsert
@Table(name="board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sig_id")
    private Integer sigId;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "content")
    private String content;

    @Column(name = "image")
    private String image;

    @Column(name = "view_num")
    private Integer viewNum ;

    @Column(name = "like_num")
    private Integer likeNum ;

    @Column(name = "comment_count")
    private Integer commentCount ;

    @Column(name = "write_At")
    private Timestamp writeAt;
}
