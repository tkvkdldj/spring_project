package com.welcomeshop.www;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

//md5 : 회원가입, 로그인, 패스워드 변경, 1:1문의, 자유게시판, 상품구매.....
@Controller
public class web_Controller extends md5_pass{
	PrintWriter pw = null;
	
	@Resource(name = "userselect")
	private user_select us;
	
	//DAO를 쓰고싶다면 => @ModelAttribute
	//이름과 이메일, 정보 두 개만 가져오고 싶다면 => DAO 없이 사용시 : 자료형 객체 or @RequestParam을 이용해서 사용(null로 날아왔을 때 조건처리하기 편함)  
	@PostMapping("/idsearch.do")
	public String idsearch(String[] uname, String uemail, HttpServletResponse res) throws Exception { //아이디 찾기
		res.setContentType("text/html;charset=utf-8");
		
		this.pw = res.getWriter();
		//null로 날아오면 에러나니 조건 걸어야함
		try {
			if(uname[0] == null || uemail == null) {
				this.pw.print("<script>"
						+ "alert('올바른 접근 방식이 아닙니다.');"
						+ "history.go(-1);"
						+ "</script>");
			}
			else {
				ArrayList<Object> onedata = us.search_id(uname[0], uemail); 
				
			}
		}
		catch(Exception e) {
			this.pw.print("<script>"
					+ "alert('Database 문제로 인하여 해당 정보가 확인 되지 않습니다.');"
					+ "history.go(-1);"
					+ "</script>");
		}
		finally {
			this.pw.close();
		}
		
		return null;
	}
	
	@PostMapping("/passmodify.do")
	public String passmodify() { //패스워드 변경
		return null;
	}
	
	/*
	이제는 이런 식으로 쓰는 것이 아니라 md5_pass를 abstract로 바꾸고 여기에 extends함
	(둘 중 뭘로 쓸지는 개발자맘이지만 Resource는 조금 불편함->모듈에 Repository 꼭 넣어줘야함)
	
	@Resource(name = "md5pass") //Java Build path에 server runtime을 추가해야 나옴
	private md5_pass md;
	*/
	//패스워드 변경 여부를 체크(MD5)
	@GetMapping("/passwd.do")
	public String passwd() {
		String pwd = "a1234";
		String result = this.md5_making(pwd);
		System.out.println(result);
		return null;
	}
	
	@GetMapping("/restapi.do")
	//@SessionAttribute : session이 이미 등록되어있는 상황일 경우 해당 정보를 가져올 수 있음
	//값이 없으면 null이 떠버릴 것
	public String restapi(@SessionAttribute(name="mid", required=false) String mid) 
			throws Exception{
		//System.out.println(mid);
		if(mid==null) {
			System.out.println("로그인 해야만 결제내역을 확인하실 수 있습니다.");
		}
		else {
			System.out.println("결제내역은 다음과 같습니다.");
		}
		return null;
	}
	
	//HttpSession : interface를 활용하여, 세션을 빠르게 구현하는 방식 스타일
	@PostMapping("/loginok.do")
	public String loginok(@RequestParam(value="", required = false) String mid, HttpSession session) {
		/* HttpServletRequest req 사용
		HttpSession session = req.getSession();
		session.setAttribute("mid", mid);
		session.setMaxInactiveInterval(1800);//1800=30분 //이거 안하면 계속 로그아웃 될 것
		System.out.println(mid);
		*/
		if(mid != null || mid != "") {
			session.setAttribute("mid", mid);
			session.setMaxInactiveInterval(1800);			
		}
		return null;
	}
	/*
	@PostMapping("/ajaxok3.do")
	public String ajaxok3() {
		return null;
	}
	*/
	
	@CrossOrigin(origins="*", allowedHeaders = "*")
	@PostMapping("/ajaxok2.do")
	public String ajaxok2(@RequestBody String all_data,
			HttpServletResponse res) throws Exception {
		System.out.println(all_data);
		JSONObject jo = new JSONObject(all_data);
		JSONArray ja = (JSONArray)jo.get("all_data");
		System.out.println(ja.get(0));
		
		JSONObject result = new JSONObject();
		result.put("result", "ok");
		this.pw = res.getWriter();
		this.pw.print(result);
		this.pw.close();
		return null;
	}
	
	
	
	//@RequestBody : GET/POST 에선 잘 사용x, 일반적으로 JSON기반일 경우
	//@ResponseBody : 미디어타입, 파라미터 타입. 단, 절대 인자값에는 미선언
	//프론트에서 보낼 때 html이런거 일 때는 @RequestParam으로 받음
	//ajax 통신 CORS 방식 필수
	//@ResponseBody -> Getmapping일 때는 이거 써도 안써도 잘 받음
	@CrossOrigin(origins="*", allowedHeaders = "*")
	//@RequestParam : 배열을 이용하여 대표키로 전달 또는 대표키 없이 보조키로 전달될 경우 사용할 수 있음
	@GetMapping("/ajaxok.do") //produce 안써도됨, 받는 @를 뭘로 받을지가 중요
	public String ajaxok(@RequestParam(value="all_data") List<String> alldata, HttpServletResponse res) throws Exception {
		//RequestBody는 통신이 post로 날아올 때 씀
		System.out.println(alldata);
		System.out.println(alldata.get(0));
		this.pw = res.getWriter();
		//json으로 보낼 때는 this.pring("ok"); 안됨
		JSONObject jo = new JSONObject();
		jo.put("result", "ok");
		this.pw.print(jo);
		this.pw.close();
		return null;
	}
}
