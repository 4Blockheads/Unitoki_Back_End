package com.glitch.unitoki.service;

import com.glitch.unitoki.model.Reply;
import com.glitch.unitoki.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    public List<Reply> findBySigId(Integer sigId){return replyRepository.findBySigId(sigId);}

    public Reply createReply(Reply replyRequest) {
        return replyRepository.save(replyRequest);
    }
}
