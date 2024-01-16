package com.cena.tdd.domain.repository;

import com.cena.tdd.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserIndex(Long userIndex);
}
