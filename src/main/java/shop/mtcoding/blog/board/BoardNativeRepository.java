package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardNativeRepository {
    private final EntityManager em;

    @Transactional
    public void deleteById(int id) {
        Query query =
                em.createNativeQuery("delete from board_tb where id = ?");
        query.setParameter(1, id);

        query.executeUpdate();
    }

    public Board findById(int id) {
        String q = """
                select * from board_tb where id = ?;
                """;
        Board board = (Board) em.createNativeQuery(q,Board.class).setParameter(1,id).getSingleResult();
        return board;
    }

    public List<Board>findAll(){
        String q = """
                select * from board_tb order by id desc
                """;
        List<Board> boardList = em.createNativeQuery(q,Board.class).getResultList();
        return boardList;
    }

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

