package com.kwj1270.commerce.dto.board;

import com.kwj1270.commerce.domain.board.Board;
import com.kwj1270.commerce.domain.board.BoardType;
import com.kwj1270.commerce.domain.board.comment.Comment;
import com.kwj1270.commerce.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BoardResponseDto {

    private Long id;
    private String title;
    private String subTitle;
    private String content;
    private BoardType boardType;
    private User user;
    private List<Comment> comments;
    private LocalDateTime modifiedDate;

    public BoardResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.subTitle = entity.getSubTitle();
        this.content = entity.getContent();
        this.boardType = entity.getBoardType();
        this.user = entity.getUser();
        this.comments = entity.getComments();
        this.modifiedDate = entity.getModifiedDate();
    }
}
