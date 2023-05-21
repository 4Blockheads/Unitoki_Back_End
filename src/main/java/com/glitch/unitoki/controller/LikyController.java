package com.glitch.unitoki.controller;

import com.glitch.unitoki.model.Board;
import com.glitch.unitoki.model.LikeRequest;
import com.glitch.unitoki.model.Liky;
import com.glitch.unitoki.service.BoardService;
import com.glitch.unitoki.service.LikyService;
import com.glitch.unitoki.service.UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class LikyController {

    @Autowired
    private LikyService likyService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private UserService userService;

    @GetMapping("/like")
    public ResponseEntity<List<Liky>> searchLike() {
        List<Liky> liky = likyService.getAll();
        return new ResponseEntity<>(liky, HttpStatus.OK);
    }

    @PostMapping("/like")
    public ResponseEntity<Void> toggleLike(@RequestHeader("Authorization") String token ,@RequestBody LikeRequest likeRequest) {

        String userWallet = (Jwts.parser().setSigningKey("yourSecretKey").parseClaimsJws(token).getBody()).getSubject();

        Integer sigId = likeRequest.getSigId();
        likeRequest.setUserNickname(userService.findByUserWallet(userWallet).getUserNickname());
        String userNickname = likeRequest.getUserNickname();

        boolean isLiked = likyService.toggleLike(sigId, userNickname);

        if (isLiked) {
            boardService.increaseLikeCount(sigId);
        } else {
            boardService.decreaseLikeCount(sigId);
        }

        return ResponseEntity.ok().build();
    }
}
