package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import dto.Board;
import dto.Page;

class JunitTest {
	
	BoardDAO bdao = new BoardDAOImpl();

	@Test
	void testMBConn() {
		MBConn.getSession();
	}
	
	@Test
	void testInsert() {
		Board board = new Board();
		board.setEmail("rabbit@gmail.com");
		board.setSubject("안녕하세용");
		board.setContent("좋은하루입니다~");
		board.setIp("192.168.0.100");
		int cnt =bdao.insert(board);
		System.out.println(cnt+"건 추가");
		assertEquals(1, cnt);
	}
	
	@Test
	void testUpdate() {
		Board board = new Board();
		board.setBnum(1);
		board.setEmail("rabbit@gmail.com");
		board.setSubject("안녕하세용");
		board.setContent("좋은하루입니다~");
		board.setIp("192.168.0.100");
		int cnt = bdao.update(board);
		System.out.println(cnt+"건 수정");
		assertEquals(1, cnt);
	}
	
	@Test
	void testDelete() {
		int cnt=bdao.delete(3);
		System.out.println(cnt+"건 삭제");
		assertEquals(1, cnt);
	}
	
	@Test
	void testselectOne() {
		Board board = bdao.selectOne(1);
		System.out.println(board);
		//board가 null과 같지 않으면 성공
		//assertNotEquals(null, board);
		assertNotNull(board);
	}
	
	@Test
	void testselectList() {
		Page page = new Page();
		page.setFindkey("email");
		page.setFindvalue("rabbit");
		List<Board> blist = bdao.selectList(page);
		System.out.println(blist);
		
	}

}
