package com.glitch.unitoki.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Data
@Table(name="reply")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Integer replyId ;

    @Column(name = "sig_id")
    private Integer sigId ;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "comment")
    private String comment;

}
