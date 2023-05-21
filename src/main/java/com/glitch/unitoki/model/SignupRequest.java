package com.glitch.unitoki.model;

public class SignupRequest {
        private String userWallet;

        private String userNickname;

        public String getUserWallet() {
            return userWallet;
        }

        public void setUserWallet(String userWallet) {
            this.userWallet = userWallet;
        }

        public String getUserNickname() {
            return userNickname;
        }

        public void setUserNickname(String userNickname) {
            this.userNickname = userNickname;
        }
}
