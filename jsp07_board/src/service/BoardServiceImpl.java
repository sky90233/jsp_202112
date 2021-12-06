package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.BoardDAO;
import dao.BoardDAOImpl;
import dao.BoardFileDAO;
import dao.BoardFileDAOImpl;
import dto.Board;
import dto.BoardFile;
import dto.Page;

public class BoardServiceImpl implements BoardService{

	private BoardDAO bdao = new BoardDAOImpl();
	private BoardFileDAO bfdao = new BoardFileDAOImpl();
	
	@Override
	public List<Board> selectList(Page page) {
		/* 페이징처리 */
		int curpage = page.getCurpage();//현재페이지(지정)
		int perpage = page.getPerpage();//한페이지당 게시물수
		int startnum = (curpage-1)*perpage+1; //시작번호
		int endnum = startnum + perpage -1;
		page.setStartnum(startnum);
		page.setEndnum(endnum);
		
		/* 전체페이지수 구하기 */
		int totcnt = bdao.select_totcnt(page); //전체건수
		int totpage = totcnt/perpage; //전체페이지수
		System.out.println("전체건수:"+totcnt);
		//System.out.println("전체페이지수1:"+totpage);
		if(totcnt%perpage>0) totpage ++;//나머지가 있으면 +1
		System.out.println("전체페이지수2:"+totpage);
		page.setTotpage(totpage);
		
		//페이지블럭 구하기
		int perblock = page.getPerblock();//페이지블럭의수(지정)
		int startpage = curpage-((curpage-1)%perblock);  //시작페이지
		int endpage = startpage +perblock-1;//끝페이지
		//전체페이지가 페이지블럭의 마지막페이지보다 작다면
		//마지막페이지를 전체페이지로 설정
		if(totpage<endpage) endpage = totpage;
		//ex)32 <40(40면안됨)  32 = 32 페이지까지 나오게해줌
		
		page.setStartpage(startpage);
		page.setEndpage(endpage);
		
		
		
		System.out.println(page);
		return bdao.selectList(page);
	}

	@Override
	public String insert(Board board, List<String> filenames) {
		//게시물등록(한건)
		int cnt =bdao.insert(board);
		System.out.println("board"+cnt+"건 추가");
		System.out.println("service:"+board);
		//파일이름 배열 처리
		//board.getBnum() : boardMapper의 insert시 bnum구해줌
		BoardFile boardFile = new BoardFile();
		for(String filename: filenames) {
			if(filename==null) continue;
//			BoardFile boardFile = new BoardFile();
			boardFile.setBnum(board.getBnum());
			boardFile.setFilename(filename);
			bfdao.insert(boardFile);
			System.out.println("file:"+boardFile);
		}
		if(cnt>0)
			return "추가 성공";
		else
			return "추가 실패";
	}

	@Override
	public Map<String, Object> selectOne(int bnum) {
		//1) 게시물 한건 조회
		Board board = bdao.selectOne(bnum);
		
		//2)게시물의 파일들 조회
		//게시물하나에 여러개의 파일들이 들어있으니 selectList
		List<BoardFile> bflist = bfdao.selectList(bnum); 
		//3)댓글 조회
		List<Board> rlist=bdao.select_reply(bnum);//원본의 bnum과 ref 는 같다
		
		System.out.println(board);
		System.out.println(bflist);
		System.out.println(rlist);
		
		Map<String,Object> map = new HashMap<>();
		map.put("board", board);
		map.put("bflist", bflist);
		map.put("rlist", rlist);
		return map;
	}

	@Override
	public String delete(int bnum) {
		//주의 :fk때문에 자식데이터부터 삭제 후 부모데이터 삭제
		//게시물의 파일들 삭제
		bfdao.delete_bnum(bnum);
		//게시물삭제
		int cnt =bdao.delete(bnum);
		if(cnt>0)
			return "삭제 성공";
		else
			return "삭제 실패";
	}

	@Override
	public String update(Board board, String[] filedels, List<String> filenames) {
		//게시물 수정
		int cnt = bdao.update(board);

		//파일들 삭제
		if(filedels != null) {//파일 아무것도 삭제안할떄도있으니 null처리 안하면 null오류
			for(String fnum : filedels) {
				bfdao.delete(Integer.parseInt(fnum));
			}
		}
		
		
		//파일들 추가
		BoardFile boardFile = new BoardFile();
		for(String filename:filenames) {
			boardFile.setBnum(board.getBnum());
			boardFile.setFilename(filename);
			bfdao.insert(boardFile);
		}
		
		if(cnt>0)
			return "수정 완료";
		else
			return "수정 실패";
		
	}
	//조회수//
	@Override
	public void cntplus(int bnum) {
		// 조회수+1
		bdao.cntplus(bnum);
		
	}

}
