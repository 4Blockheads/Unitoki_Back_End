package com.glitch.unitoki.controller;

import com.glitch.unitoki.model.Board;
import com.glitch.unitoki.model.User;
import com.glitch.unitoki.service.BoardService;
import com.glitch.unitoki.service.UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @Autowired
    private UserService userService;

    @GetMapping("/signal")
    public ResponseEntity<List<Board>> getAllSignal(){
        List<Board> board = boardService.getAllSignal();

        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @GetMapping("/topsignal")
    public ResponseEntity<List<Board>> getTopSignal(){
        List<Board> board = boardService.getTopSignal();

        return  new ResponseEntity<>(board, HttpStatus.OK);
    }

    @PostMapping("/addsignal")
    public ResponseEntity<String> createSignal(@RequestHeader("Authorization") String token, @RequestBody Board board) {

        String userWallet = (Jwts.parser().setSigningKey("yourSecretKey").parseClaimsJws(token).getBody()).getSubject();
        board.setUserNickname(userService.findByUserWallet(userWallet).getUserNickname());
        Board createSignal = boardService.createSignal(board);

        if(createSignal != null) {
            User user = userService.findByUserWallet(userWallet);
            user.setPoint(user.getPoint()+10);
            userService.save(user);
        }

        return ResponseEntity.ok("게시글이 생성되었습니다.");
    }
}
