package com.glitch.unitoki.controller;


import com.glitch.unitoki.model.Reply;
import com.glitch.unitoki.model.ReplyRequest;
import com.glitch.unitoki.service.ReplyService;
import com.glitch.unitoki.service.UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private UserService userService;

    @GetMapping("/reply")
    public ResponseEntity<List<Reply>> getReply (@RequestHeader("sigId") Integer sigId ) {
        List<Reply> replylist = replyService.findBySigId(sigId);
        return ResponseEntity.ok(replylist);
    }

    @PostMapping("/reply")
    public ResponseEntity<String> createReply (@RequestHeader("Authorization") String token, @RequestBody ReplyRequest replyRequest){

        String userWallet = (Jwts.parser().setSigningKey("yourSecretKey").parseClaimsJws(token).getBody()).getSubject();
        String userNickname = userService.findByUserWallet(userWallet).getUserNickname();

        Reply newReply = new Reply();
        newReply.setUserNickname(userNickname);
        newReply.setSigId(replyRequest.getSigId());
        newReply.setComment(replyRequest.getComment());

        replyService.createReply(newReply);

        return ResponseEntity.ok("댓글이 작성되었습니다.");
    }
}
