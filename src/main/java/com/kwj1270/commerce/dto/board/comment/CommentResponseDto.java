package com.kwj1270.commerce.dto.board.comment;

import com.kwj1270.commerce.domain.board.comment.Comment;
import com.kwj1270.commerce.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private Long id;
    private String content;
    private User user;
    private LocalDateTime modifiedDate;


    public CommentResponseDto(Comment entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.user = entity.getUser();
        this.modifiedDate = entity.getModifiedDate();
    }

}
