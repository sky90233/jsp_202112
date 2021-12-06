package service;

import java.util.List;
import java.util.Map;

import dto.Member;

public interface MemberService {
	String insert(Member member);
//	String update(Member member);
//	String delete(String email);
//	Member selectOne(String email);
//	List<Member> selectList(String findkey,String findvalue);
	//로그인할때 msg랑 rcode 넘겨주려고함
	Map<String,String> login(String email, String passwd);

	Member selectOne(String email);
	
	String update(Member member, String changepw);

}
