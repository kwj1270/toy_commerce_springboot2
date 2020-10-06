package com.kwj1270.commerce.service.board;

import com.kwj1270.commerce.domain.board.Board;
import com.kwj1270.commerce.domain.board.BoardRepository;
import com.kwj1270.commerce.dto.board.BoardListResponseDto;
import com.kwj1270.commerce.dto.board.BoardResponseDto;
import com.kwj1270.commerce.dto.board.BoardSaveRequestDto;

import com.kwj1270.commerce.dto.board.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardSaveRequestDto requestDto){
        return boardRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public BoardResponseDto findById(Long id){
        Board entity = boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(("해당 게시글이 없습니다. id="+ id)));
        return new BoardResponseDto(entity);
    }

    // Page 를 사용한 이유는 가독성 및 명화해짐
    @Transactional
    public Page<BoardListResponseDto> findAllDesc(Pageable pageable){
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, Sort.by("createdDate").descending());
        return boardRepository.findAll(pageable).map(BoardListResponseDto::new);
    }

    @Transactional
    public Page<BoardListResponseDto> findAll(Pageable pageable){
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10);
        return boardRepository.findAll(pageable).map(BoardListResponseDto::new);
    }

    @Transactional
    public Long update(Long id, BoardUpdateRequestDto requestDto){
        Board board = boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(("해당 게시글이 없습니다. id="+ id)));
        board.update(requestDto.getTitle(), requestDto.getSubtitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public void delete(Long id){
        Board board = boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(("해당 게시글이 없습니다. id="+ id)));

        boardRepository.delete(board);
    }

}
