package com.glitch.unitoki.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "liky")
@Data
@Getter
@Setter
public class Liky {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "liky_id")
    private Integer likyId;

    @Column(name = "sig_id")
    private Integer sigId;

    @Column(name = "user_nickname")
    private String userNickname;
}
