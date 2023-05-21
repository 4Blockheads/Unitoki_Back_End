package com.glitch.unitoki.repository;

import com.glitch.unitoki.model.Liky;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikyRepository extends JpaRepository<Liky, Integer> {
    Liky findBySigIdAndUserNickname(Integer sigId, String userNickname);
}
