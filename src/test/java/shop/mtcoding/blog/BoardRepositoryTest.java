package shop.mtcoding.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.board.BoardRepository;

import java.util.List;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void findById(){
        //given
        int id = 1;

        //when
        boardRepository.findById(id);
    }

    @Test
    public void findAll_test() {
        // given

        // when
        List<Board> boardList = boardRepository.findAll();
        boardList.forEach(board -> {
            System.out.println(board.getUser().getUsername());
        });

        // then
    }

}
