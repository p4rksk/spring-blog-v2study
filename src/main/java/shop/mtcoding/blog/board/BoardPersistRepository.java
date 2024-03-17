package shop.mtcoding.blog.board;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardPersistRepository {
    private final EntityManager em;


    public Board findById(int id){
        Board board = em.find(Board.class, id);
        return board;
    }

    public List<Board> findAll(){
        String q = """
                select b from Board b order by b.id desc
                """;
        List<Board> boardList = em.createQuery(q).getResultList();
        return boardList;
    }

    @Transactional
    public Board save(Board board) {
        // 1. 비영속 객체
        em.persist(board);
        // 2. board -> 영속 객체
        return board;
    }

    @Transactional
    public void updateById(int id, BoardRequest.UpdateDTO reqDTO){
        Board board = findById(id);
        board.update(reqDTO);
    } // 더티체킹

    @Transactional
    public void deleteById(int id) {
        Query query =
                em.createNativeQuery("delete from board_tb where id = ?");
        query.setParameter(1, id);

        query.executeUpdate();
    }

}

