package com.kwj1270.commerce.service;

import com.kwj1270.commerce.domain.board.Board;
import com.kwj1270.commerce.domain.board.BoardRepository;
import com.kwj1270.commerce.dto.board.BoardListResponseDto;
import com.kwj1270.commerce.dto.board.BoardSaveRequestDto;

import com.kwj1270.commerce.dto.board.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardSaveRequestDto requestDto){
        return boardRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public List<BoardListResponseDto> findAllDesc(Pageable pageable){
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber()-1,
                pageable.getPageSize());
        return boardRepository.findAllDesc(pageable).stream()
                .map(BoardListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long seq, BoardUpdateRequestDto requestDto){
        Board board = boardRepository.findBySeq(seq).orElseThrow(() ->
                new IllegalArgumentException(("해당 게시글이 없습니다. id="+ seq)));
        board.update(requestDto.getTitle(), requestDto.getContent());
        return seq;
    }

    @Transactional
    public void delete(Long seq){
        Board board = boardRepository.findBySeq(seq).orElseThrow(() ->
                new IllegalArgumentException(("해당 게시글이 없습니다. id="+ seq)));

        boardRepository.delete(board);
    }




}
