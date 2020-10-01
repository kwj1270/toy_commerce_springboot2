package com.kwj1270.commerce.domain.board;

import com.kwj1270.commerce.domain.BaseTimeEntity;
import com.kwj1270.commerce.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Table(name="BOARD")
@Entity
public class Board extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID")
    private Long id;

    @Column(name = "BOARD_TITLE", length = 500, nullable = false)
    private String title;

    @Column(name = "BOARD_SUBTITLE", length = 500, nullable = false)
    private String subTitle;

    @Column(name = "BOARD_CONTENT", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "BOARD_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Builder
    public Board(String title, String subTitle, String content, BoardType boardType, User user) {
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.boardType = boardType;
        this.user = user;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
