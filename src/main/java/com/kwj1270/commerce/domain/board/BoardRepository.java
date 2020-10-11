package com.kwj1270.commerce.domain.board;

import com.kwj1270.commerce.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<List<Board>> findByUser(User user);
}
