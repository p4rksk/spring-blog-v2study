package shop.mtcoding.blog.board;

import lombok.Data;

public class BoardRequest {

    @Data
    public static class SaveDTO {
        private String title;
        private String content;
        private String username;

        public Board toEntity(){//pc에 넣기위해 entity로 변환
            return new Board(title, content, username);
        }
    }
}
