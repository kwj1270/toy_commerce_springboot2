package com.kwj1270.commerce.dto.board.comment;

import com.kwj1270.commerce.domain.board.comment.Comment;
import com.kwj1270.commerce.domain.user.User;
import lombok.Getter;

@Getter
public class CommentListResponseDto {

    private Long id;
    private String content;
    private User user;

    public CommentListResponseDto(Comment entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.user = entity.getUser();
    }

}
