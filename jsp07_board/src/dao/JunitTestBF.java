package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import dto.BoardFile;

class JunitTestBF {
	
	BoardFileDAO bfdao = new BoardFileDAOImpl();

	@Test
	void testInsert() {
		BoardFile boardfile = new BoardFile();
		boardfile.setBnum(1);
		boardfile.setFilename("b.jpg");
		int cnt = bfdao.insert(boardfile);
		System.out.println(cnt + "건 추가");
	}

	@Test
	void testUpdate() {
		BoardFile boardfile = new BoardFile();
		boardfile.setFnum(2);
		boardfile.setFilename("a.png");
		int cnt = bfdao.update(boardfile);
		System.out.println(cnt + "건 수정");
		
	}

	@Test
	void testDelete() {
		int cnt =bfdao.delete(2);
		System.out.println(cnt+"건 삭제");
		
	}

	@Test
	void testSelectOne() {
		BoardFile boardfile = bfdao.selectOne(3);
		System.out.println(boardfile);
	}

	@Test
	void testSelectList() {
		List<BoardFile> bflist = bfdao.selectList(1);
		System.out.println(bflist);
		
		
	}

}
