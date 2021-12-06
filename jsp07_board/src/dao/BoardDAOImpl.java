package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.Board;
import dto.Page;

public class BoardDAOImpl implements BoardDAO{

	@Override
	public int insert(Board board) {
		try(SqlSession session=MBConn.getSession()){
			return session.insert("BoardMapper.insert",board);
		}
		
	}
	@Override
	public int update(Board board) {
		try(SqlSession session=MBConn.getSession()){
			return session.update("BoardMapper.update",board);
		}
	}

	@Override
	public int delete(int bnum) {
		try(SqlSession session=MBConn.getSession()){
			return session.delete("BoardMapper.delete",bnum);
		}
	}

	@Override
	public Board selectOne(int bnum) {
		try(SqlSession session=MBConn.getSession()){
			return session.selectOne("BoardMapper.selectOne",bnum);
		}
	}

	@Override
	public List<Board> selectList(Page page) {
		try(SqlSession session=MBConn.getSession()){
			return session.selectList("BoardMapper.selectList",page);
		}
	}
	@Override
	public void cntplus(int bnum) {
		//조회수+1
		try(SqlSession session=MBConn.getSession()){
			//리턴해주는것 없음 void
			session.selectList("BoardMapper.update_readcntplus",bnum);
		}
	}
	//전체게시물수
	@Override
	public int select_totcnt(Page page) {
		try(SqlSession session=MBConn.getSession()){
			return session.selectOne("BoardMapper.select_totcnt",page);
		}
	}
	
	@Override
	public void restepplus(Board board) {
		//댓글의 순서 +1
		try(SqlSession session=MBConn.getSession()){
			session.update("BoardMapper.update_restepplus",board);
	
		}
	}
	
	//댓글조회
	@Override
	public List<Board> select_reply(int ref) {
		try(SqlSession session=MBConn.getSession()){
			return session.selectList("BoardMapper.select_reply",ref);
		}
	}

}
