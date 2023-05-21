package com.glitch.unitoki.service;

import com.glitch.unitoki.model.Board;
import com.glitch.unitoki.repository.BoardRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepsitory boardRepsitory;

    public Board createSignal(Board board){
        return boardRepsitory.save(board);
    }

    public List<Board> getAllSignal() {
        return boardRepsitory.findAll(Sort.by(Sort.Direction.DESC, "writeAt"));
    }

    public List<Board> getTopSignal() {
        Pageable pageable = PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "likeNum"));
        return boardRepsitory.findAll(pageable).getContent();
    }

    public void increaseLikeCount(Integer sigId) {
        Board signal = boardRepsitory.findBySigId(sigId);

        int currentLikeCount = signal.getLikeNum();
        signal.setLikeNum(currentLikeCount + 1);

        boardRepsitory.save(signal);
    }

    public void decreaseLikeCount(Integer sigId) {
        Board board = boardRepsitory.findBySigId(sigId);

        int currentLikeCount = board.getLikeNum();
        board.setLikeNum(Math.max(currentLikeCount - 1, 0));

        boardRepsitory.save(board);
    }
}
