package com.glitch.unitoki.service;

import com.glitch.unitoki.model.Board;
import com.glitch.unitoki.model.Liky;
import com.glitch.unitoki.repository.LikyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikyService {

    @Autowired
    private LikyRepository likyRepository;

    public List<Liky> getAll() {
        return likyRepository.findAll();
    }
    public boolean toggleLike(Integer sigId, String userNickname) {
        Liky existingLiky = likyRepository.findBySigIdAndUserNickname(sigId, userNickname);

        if (existingLiky != null) {
            // 좋아요 취소
            likyRepository.delete(existingLiky);
            return false;
        } else {
            // 좋아요 추가
            Liky liky = new Liky();
            liky.setSigId(sigId);
            liky.setUserNickname(userNickname);
            likyRepository.save(liky);
            return true;
        }
    }
}
