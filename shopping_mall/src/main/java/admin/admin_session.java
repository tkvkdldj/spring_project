package admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

//아이디랑 이름만 세션 생성해놓음
@Repository("adsession")
public class admin_session {
	//로그인 시 세션 생성
	public void make_session(HttpSession session , String key ,String value) {
		session.setAttribute(key, value);
		session.setMaxInactiveInterval(1800);
	}
	
	//세션 파기하기
	public void remove_session(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.removeAttribute("aid");
		session.removeAttribute("aname");
	}
	
}
