package com.glitch.unitoki.repository;

import com.glitch.unitoki.model.Board;
import com.glitch.unitoki.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepsitory extends JpaRepository<Board, Integer> {

    Board findBySigId(Integer sigId);
}
