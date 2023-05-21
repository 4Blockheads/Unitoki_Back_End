package com.glitch.unitoki.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer userId;

    @Column(name = "user_wallet")
    private String userWallet;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "profile_nft")
    private String profileNFT;

    @Column(name = "point")
    @Builder.Default
    private Integer point = 0;

    public  User() {}


    public User(Integer userId, String userWallet, String userNickname, Integer point, String profileNFT) {
        this.userId = userId;
        this.userWallet = userWallet;
        this.userNickname = userNickname;
        this.point = point;
        this.profileNFT = profileNFT;
    }
}
