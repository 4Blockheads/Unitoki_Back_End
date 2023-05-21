package com.glitch.unitoki.repository;

import com.glitch.unitoki.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    List<Reply> findBySigId(Integer sigId);
}
