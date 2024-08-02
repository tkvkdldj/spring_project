package admin;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class admin_Controller {

	PrintWriter pw = null;
	
	@Resource(name="admindml")
	private admin_dml ad;
	
	@Resource(name="adsession")
	private admin_session as;
	
	
	//기본설정값 받기
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/admin/admin_sitedataok.do")
	public String admin_sitedataok(@RequestBody String data, HttpServletResponse res) throws Exception{
		//System.out.println(data);
		
		this.pw = res.getWriter();
		// [] 모양으로 날아올거임
		try {
			JSONArray ja = new JSONArray(data);
			System.out.println(ja);
			this.pw.print("ok");
		}
		catch(Exception e) {
			System.out.println("배열 받기 에러");
			this.pw.print("no");
		}
		
		
		this.pw.close();
		
		return null;
	}
	
	
	//쇼핑몰 기본 설정
	@GetMapping("/admin/admin_siteinfo.do")
	public String admin_siteinfo(HttpServletResponse res) {
		
		return "admin_siteinfo";
	}
	
	
	//로그아웃 (로그인 없이 해당 페이지 접근 못하게)
	@PostMapping("/admin/admin_logout.do")
	public String admin_logout(HttpServletResponse res, HttpServletRequest req) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		
		as.remove_session(req);
		this.pw = res.getWriter();
		this.pw.print("<script>"
				+ "alert('정상적으로 로그아웃 되셨습니다.');"
				+ "location.href = './index.jsp';"
				+ "</script>");
		this.pw.close();
		
		return null; 
	}
	
	//로그인
	@PostMapping("/admin/admin_main.do")
	public String admin_main(String aid, String apass, Model m, HttpSession session, 
			HttpServletResponse res) throws Exception{
		
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		
		if(aid.equals("master") && apass.equals("shop_master123")) {
			this.pw.print("<script>"
					+ "alert('정상적으로 로그인 완료 되었습니다.');"
					+ "</script>");
			as.make_session(session, aid, "홍길동"); //세션을 모듈에서 생성
		}
		
		//jstl로 찍을 수 있게 select 값 보내기 (전체 카운트값을 보낼 수 있는 방법 없나 아)
		try {
			List<ad_member_dao> result = ad.all_select();
			int total = result.size();
			m.addAttribute("result", result);
			m.addAttribute("total", total);
		}
		catch(Exception e) {
			m.addAttribute("result", "no");
		}
		
		return "admin_main"; 
		//"views가 admin디렉토리라 같다고 생각" "다 지우고 admin_main이라고 하면 views 안에 admin디렉토리를 만들필요 없음
		//패키지명이랑 디렉토리 명이랑 같으면 css도 안깨짐
		//xml을 건드리지는 말기
		//jsp들은 죄다 views에 넣는 것이 좋음 
	}
	
	
	//아이디 중복 체크
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/admin/checkidok.do")
	public String checkidok(String aid, HttpServletResponse res) throws Exception{
		this.pw = res.getWriter();
		if(aid != null) {
			try {
				String callback = ad.idcheck(aid);
				if(callback.equals("0")) {
					this.pw.print("ok"); //중복된 아이디값 없음		
				}
				else {
					this.pw.print("no");//중복된 아이디 있음
				}
			}
			catch(Exception e) {
				this.pw.print("error");
			}
			finally {
				this.pw.close();
			}
		}
		return null;
	}
	
	@PostMapping("/admin/enrollok.do")
	public String enrollok(@ModelAttribute("admember") ad_member_dao dao, HttpServletResponse res) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		
		int callback = ad.member_insert(dao);
		
		try {
			if(callback > 0) {
				this.pw.print("<script>"
						+ "alert('정상적으로 회원가입이 완료되었습니다.');"
						+ "location.href='./index.jsp';"
						+ "</script>");			
			}
			else {
				this.pw.print("<script>"
						+ "alert('데이터베이스 문제로 가입이 되지 못하였습니다.');"
						+ "history.go(-1);"
						+ "</script>");
			}
		}
		catch(Exception e) {
			this.pw.print("<script>"
					+ "alert('데이터베이스 문제로 가입이 되지 못하였습니다.');"
					+ "history.go(-1);"
					+ "</script>");
		}
		finally {
			this.pw.close();
		}
		
		return null;
	}
	
	//신규가입으로 이동
	@GetMapping("/admin/add_master.do")
	public String add_master () {
		return "add_master";
	}
}
