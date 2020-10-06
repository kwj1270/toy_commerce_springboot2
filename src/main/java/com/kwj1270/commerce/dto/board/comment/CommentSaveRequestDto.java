package com.kwj1270.commerce.dto.board.comment;

import com.kwj1270.commerce.domain.board.comment.Comment;
import com.kwj1270.commerce.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentSaveRequestDto {

    private String content;
    private User user;

    @Builder
    public CommentSaveRequestDto(String content, User user) {
        this.content = content;
        this.user = user;
    }

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .user(user)
                .build();
    }

}
