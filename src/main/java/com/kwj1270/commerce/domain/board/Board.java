package com.kwj1270.commerce.domain.board;

import com.kwj1270.commerce.domain.BaseTimeEntity;
import com.kwj1270.commerce.domain.board.comment.Comment;
import com.kwj1270.commerce.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Table(name="BOARD")
@Entity
public class Board extends BaseTimeEntity {

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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "BOARD_ID")
    private List<Comment> comments;


    @Builder
    public Board(String title, String subTitle, String content, BoardType boardType, User user, List<Comment> comments) {
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.boardType = boardType;
        this.user = user;
        this.comments = comments;
    }

    public Board update(String title, String subTitle, String content){
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        return this; // 체이닝 사용 가능하게 해준다.
    }

}
