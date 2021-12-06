package dao;


import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MBConn {
//	static:공용메모리 객체 생성 안하도록 편하게
	public static SqlSession getSession() {
		SqlSession session =null;
		String resource = "mybatis/mybatisConfig.xml";
		try {
			//마이바티스 환경 xml파일
			InputStream is =Resources.getResourceAsStream(resource);
			//팩토리 만들기(세션을 만들수있는 객체)
			//Factory:공장 Builder:건축자 build:짓다
			SqlSessionFactory sf=new SqlSessionFactoryBuilder().build(is);
			//세션 생성
			session = sf.openSession();
			System.out.println("session생성 성공");
		} catch (IOException e) {
			System.out.println("mybatis환경파일 읽기 실패");
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return session;
	}

}
