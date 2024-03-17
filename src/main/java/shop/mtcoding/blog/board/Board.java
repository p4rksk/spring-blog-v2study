package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Entity
@Table(name = "board_tb")
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //pk
    private String username;
    private String title;
    private String content;
    private Timestamp createdAt;

    @Builder
    public Board(String title, String content, String username) {
        this.title = title;
        this.content = content;
        this.username = username;
    }

    public void update(BoardRequest.UpdateDTO reqDTO){
        this.title = reqDTO.getTitle();
        this.content = reqDTO.getContent();
        this.username = reqDTO.getUsername();

    }
}
