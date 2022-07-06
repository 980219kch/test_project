package com.its.test;

import com.its.test.dto.BoardDTO;
import com.its.test.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
@SpringBootTest
public class BoardTest {

    @Autowired
    private BoardService boardService;

    public BoardDTO newBoard(int i) {
        BoardDTO boardDTO = new BoardDTO("테스트작성자"+i, "테스트제목"+i, "테스트내용"+i);
        return boardDTO;
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void saveTest() {
        Long savedId = boardService.save(newBoard(99));
        BoardDTO findDTO = boardService.findById(savedId);
        assertThat(newBoard(99).getBoardWriter()).isEqualTo(findDTO.getBoardWriter());
    }
}
