package com.kwj1270.commerce.dto.board;

import com.kwj1270.commerce.domain.board.Board;
import com.kwj1270.commerce.domain.enums.BoardType;
import com.kwj1270.commerce.domain.user.User;
import lombok.Builder;

public class BoardSaveRequestDto {

    private String title;
    private String subTitle;
    private String content;
    private BoardType boardType;
    private final User user;

    @Builder
    public BoardSaveRequestDto(String title, String subTitle, String content, BoardType boardType, User user) {
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.boardType = boardType;
        this.user = user;
    }

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .subTitle(subTitle)
                .content(content)
                .boardType(boardType)
                .user(user)
                .build();
    }

}
