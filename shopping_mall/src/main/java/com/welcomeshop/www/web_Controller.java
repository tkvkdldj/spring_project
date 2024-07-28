package com.welcomeshop.www;

import java.io.PrintWriter;
import java.util.List;

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


@Controller
public class web_Controller {
	PrintWriter pw = null;
	
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
