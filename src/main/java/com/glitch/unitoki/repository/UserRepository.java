package com.glitch.unitoki.repository;

import com.glitch.unitoki.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUserWallet(String userWallet);
    User findByUserWallet(String userWallet);

}
