package com.kwj1270.commerce.controller.board;

import com.kwj1270.commerce.dto.board.BoardResponseDto;
import com.kwj1270.commerce.dto.board.BoardSaveRequestDto;
import com.kwj1270.commerce.dto.board.BoardUpdateRequestDto;
import com.kwj1270.commerce.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

    // CRUD 기반 정렬
    @PostMapping("/api/v1/board")
    public Long save(@RequestBody BoardSaveRequestDto requestDto){
        return boardService.save(requestDto);
    }

    @GetMapping("/api/v1/board/{id}")
    public BoardResponseDto findById(@PathVariable Long id){
        return boardService.findById(id);
    }

    @PutMapping("/api/v1/board/{id}")
    public Long update(@PathVariable Long id, @RequestBody BoardUpdateRequestDto requestDto){
        return boardService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/board/{id}")
    public Long delete(@PathVariable Long id){
        boardService.delete(id);
        return id;
    }

}
