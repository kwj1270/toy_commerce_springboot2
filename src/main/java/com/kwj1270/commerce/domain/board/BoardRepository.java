package com.kwj1270.commerce.domain.board;

import com.kwj1270.commerce.domain.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("SELECT b FROM Board b ORDER BY b.id DESC")
    List<Board> findAllDesc(Pageable pageable);
    Optional<List<Board>> findByUser(User user);
}
