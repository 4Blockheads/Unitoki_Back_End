package com.glitch.unitoki.controller;

import com.glitch.unitoki.model.SignupRequest;
import io.jsonwebtoken.Jwts;
import com.glitch.unitoki.model.User;
import com.glitch.unitoki.model.UserDTO;
import com.glitch.unitoki.repository.UserRepository;
import com.glitch.unitoki.service.UserService;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;



@RestController
@RequestMapping("/api")
@SessionAttributes("user")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    /*지갑로그인*/
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO loginReq) {

        String userWallet = loginReq.getUserWallet();

        User user = userRepository.findByUserWallet(userWallet);


        if (user != null) {
            String token = generateToken(loginReq.getUserWallet());

            return ResponseEntity.ok(token);
        } else {
            // 사용자가 없는 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
    }

    /*회원등록*/
    @PostMapping("/signup")
    public ResponseEntity<User> addUser(@RequestBody SignupRequest user) {

        String userWallet = user.getUserWallet();

        // user_wallet 값이 이미 존재하는지 검사
        boolean userExists = userRepository.existsByUserWallet(userWallet);
        if (userExists) {
            return ResponseEntity.badRequest().body(null);
        }

        User savedUser = new User();
        savedUser.setUserWallet(user.getUserWallet());
        savedUser.setUserNickname(user.getUserNickname());

        userService.save(savedUser);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(URI.create("/api/users/" + savedUser.getUserId()))
                .body(savedUser);
    }

    /*로그아웃*/
    @GetMapping("/logout")
    public ResponseEntity<String> getHomeData(HttpSession session) {

        String userwallet = (String) session.getAttribute("userWallet");

        System.out.print(userwallet);

        session.invalidate();
        return ResponseEntity.ok("Logged out successfully");
    }

    /*로그인 회원 정보 조회*/
    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestHeader("Authorization") String token , HttpServletRequest request) {

        String userWallet = (Jwts.parser().setSigningKey("yourSecretKey").parseClaimsJws(token).getBody()).getSubject();

        User user = userRepository.findByUserWallet(userWallet);

        System.out.println("data"+user);

        return ResponseEntity.ok(user);
    }

    /*로그인시 토큰 발행*/
    private String generateToken(String userWallet) {
        String token = Jwts.builder()
                .setSubject(userWallet)
                .signWith(SignatureAlgorithm.HS512, "yourSecretKey")
                .compact();
        return token;
    }
}
