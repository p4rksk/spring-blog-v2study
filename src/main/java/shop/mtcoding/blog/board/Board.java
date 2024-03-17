package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Entity(name = "board_tb")
@Table
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //pk
    private String username;
    private String title;
    private String content;
    private Timestamp createdAt;


    public Board(String title, String content, String username) {
        this.title = title;
        this.content = content;
        this.username = username;
    }
}
