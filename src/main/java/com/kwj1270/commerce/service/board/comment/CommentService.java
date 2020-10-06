package com.kwj1270.commerce.service.board.comment;

import com.kwj1270.commerce.domain.board.comment.Comment;
import com.kwj1270.commerce.domain.board.comment.CommentRepository;
import com.kwj1270.commerce.dto.board.comment.CommentResponseDto;
import com.kwj1270.commerce.dto.board.comment.CommentSaveRequestDto;
import com.kwj1270.commerce.dto.board.comment.CommentUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public long save(CommentSaveRequestDto requestDto){
        return commentRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional  // 업데이트 하고 seq 값 반
    public Long update(Long id, CommentUpdateRequestDto requestDto) {
        Comment Comment = commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 공지가 없습니다" + id));
        Comment.update(requestDto.getContent());
        return id;
    }

    @Transactional(readOnly = true)
    public Page<CommentResponseDto> findAllDesc(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
        pageable = PageRequest.of(page, 10, Sort.by("createdDate").descending());
        return commentRepository.findAll(pageable).map(CommentResponseDto::new);
    }


    @Transactional(readOnly = true)
    public Page<CommentResponseDto> findAll(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
        pageable = PageRequest.of(page, 10);
        return commentRepository.findAll(pageable).map(CommentResponseDto::new);
    }

    @Transactional // 유저 정보 삭제
    public CommentResponseDto findById(Long id) {
        Comment entity = commentRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 댓글 없습니다. id=" + id));
        return new CommentResponseDto(entity);
    }

    @Transactional // 유저 정보 삭제
    public void delete(Long id) {
        Comment Comment = commentRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 공지가 없습니다. id=" + id));
        commentRepository.delete(Comment);
    }

}
