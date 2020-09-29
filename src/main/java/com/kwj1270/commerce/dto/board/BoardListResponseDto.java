package com.kwj1270.commerce.dto.board;

import com.kwj1270.commerce.domain.board.Board;
import com.kwj1270.commerce.domain.board.BoardType;
import com.kwj1270.commerce.domain.user.User;
import lombok.Builder;

public class BoardListResponseDto {

    private String title;
    private String subTitle;
    private String content;
    private BoardType boardType;
    private final User user;

    @Builder
    public BoardListResponseDto(Board board){
        this.title = board.getTitle();
        this.subTitle = board.getSubTitle();
        this.content = board.getContent();
        this.boardType = board.getBoardType();
        this.user = board.getUser();
    }

}
