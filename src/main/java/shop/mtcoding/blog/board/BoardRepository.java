package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    public void save(String username, String title, String content) {
        String q = """
                insert into board_tb(username, title, content, created_at) values (?,?,?,now())
                 """;
        em.createNativeQuery(q, Board.class)
                .setParameter(1, username)
                .setParameter(2, title)
                .setParameter(3, content)
                .executeUpdate();
    }
}

