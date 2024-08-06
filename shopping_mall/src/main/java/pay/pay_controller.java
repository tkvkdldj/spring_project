package pay;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class pay_controller {
	/*
	//프론트에서 페이징 하는 방법(파라미터값 노출 없음, API 형태)
	@CrossOrigin(origins = "*", allowedHeaders = "*") //API를 사용하기 때문
	@RequestMapping(value="/pay/coupon_api.do", method=RequestMethod.GET)
	public String coupon_api(HttpServletResponse res, HttpServletRequest req) throws Exception{
		String ips = req.getRemoteAddr(); //접속 도메인 및 IP정보 확인 -> 로그기록 할 때 사용
		//System.out.println(ips); // 0:0:0:0:0:0:0:1 로컬이라 그런가..?
		
		String browser = req.getHeader("User-Agent");
		if(browser.contains("Edg")) {
			System.out.println("Edg로 접속 확인");
		}
		else if(browser.contains("Chrome")) {
			System.out.println("Chrome으로 접속 확인");	
		}
		//System.out.println(browser);
		
		
		//Spring-boot에서 사용하는 IP 정보 형태
		//HttpServletRequest req2 = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		//String ips2 = req2.getHeader("X-FORWARED-FOR");
		//System.out.println(ips2);
		
		
		PrintWriter pw = null;
		JSONObject jo = null;
		JSONArray ja = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		pw = res.getWriter();
		
		try {
			con = new dbinfo().info();
			//프론트에서 핸들링하기 위해선 limit이 아닌 전체 다 보내줘야함
			String sql = "select * from coupon order by cidx desc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			pw.print("ok");
			
		}
		catch(Exception e) {
			
		}
		finally {
			
		}
		
		return null;
	}
	*/
	
	//백엔드에서만 페이징 하는 방법
	//1page당 데이터 2개씩
	@GetMapping("/pay/coupon_list.do")
	public String coupon_list(@RequestParam(value="",required=false)Integer page, Model m) throws Exception {
		//System.out.println(page); //Integer이기 때문에 do만 실행했을 때 null
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSet rs2 = null; //카운팅 전용
		
		int pageno = 2; //데이터 2개씩
		int startpg = 0;
		
		try {
			con = new dbinfo().info();
			
			//페이징 3단계
			//url의 파라미터값 page
			if(page == null || page == 1) { //처음 do 실행시킬 때 null
				startpg = 0;
			}
			else {
				startpg= (page - 1)* pageno; // sql문의 limit 시작
			}
			
			
			//페이징 2단계
			//데이터 총 개수 (전체 개수)
			String count = "select count(*) as ctn from coupon";
			ps = con.prepareStatement(count);
			rs2 = ps.executeQuery();
			rs2.next();
			
			m.addAttribute("startpg", startpg); //가공된 page 번호
			m.addAttribute("total", rs2.getString("ctn"));
			
			//페이징 1단계
			String sql = "select * from coupon order by cidx desc limit ?,?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, startpg);
			ps.setInt(2, pageno);
			rs = ps.executeQuery();
			
			List<ArrayList<String>> all = new ArrayList<ArrayList<String>>();
			while(rs.next()) {
				ArrayList<String> al = new ArrayList<String>();
				al.add(rs.getString(1)); //1부터
				al.add(rs.getString(2));
				al.add(rs.getString(3));
				al.add(rs.getString(4));
				al.add(rs.getString(5));
				all.add(al);
			}
			//addAllAttributes:Object메서드 배열관련 객체를 차례대로 추가하는 방식
			//addAllAttributes 단점 : 2차 배열시 JSP로 전달에 대한 문제가 발생함
			
			//m.addAllAttributes(all);	//여기서 그냥 addAttribute쓰면 찍기 힘듦
			m.addAttribute("all", all); //// 2차배열이라 이걸로 출력
		}
		catch(Exception e) {
			System.out.println("DB연결 실패");
		}
		finally {
			rs.close();
			rs2.close(); //rs2가 먼저 닫히면 종종 에러
			ps.close();
			con.close();
		}
		
		return "/coupon_list";
	}
	
	
	@PostMapping("/pay/pay3.do")
	public String pay3(@ModelAttribute("payinfo") pay_dao dao, HttpServletRequest req) throws Exception{
		req.setAttribute("goodcode", dao.getGoodcode());		//상품코드
		req.setAttribute("goodname", dao.getGoodname());		//상품명
		req.setAttribute("goodea", dao.getGoodea());			//상품개수
		req.setAttribute("goodprice", dao.getGoodprice());		//상품가격
		req.setAttribute("price", dao.getPrice());				//결제금액
		req.setAttribute("buyername", dao.getBuyername());		//결제자이름
		req.setAttribute("buyertel", dao.getBuyertel());		//결제자연락처
		req.setAttribute("buyeremail", dao.getBuyeremail());	//결제자이메일
		req.setAttribute("rec_post", dao.getRec_post());		//우편번호
		req.setAttribute("rec_way", dao.getRec_way());			//도로명
		req.setAttribute("rec_addr", dao.getRec_addr());		//상세주소
		req.setAttribute("gopaymethod", dao.getGopaymethod());	//결제방식
		//이 중에 하나라도 값이 빠지면 결제창 안 뜰 것
		return "/pay3";
	}
	
	
	@PostMapping("/pay/pay2.do") //jsp를 강제로 접속 못하게 하기 위해 views에
	public String pay2(@ModelAttribute("product") pay_dao dao, Model m) throws Exception {
		//Model의 addAllAttributes를 쓰기 위해
		List<String> as = new ArrayList<String>();
		as.add(dao.getProduct_code());
		as.add(dao.getProduct_nm());
		as.add(dao.getProduct_money());
		as.add(dao.getProduct_ea());
		as.add(dao.getPrice());
		
		/*
		Collection<String> cl = new ArrayList<String>();
		cl.add(dao.getProduct_code());
		=> 이런 식으로 사용하는 경우도 있음
		*/
		
		/*
		Map<String, String> mp = new HashMap<String, String>();
		mp.put("pdcode", dao.getProduct_code());
		m.addAllAttributes(Arrays.asList(mp)); 
		=> 이런 식으로 사용하는 경우도 있음
		*/
		
		//addAllAttributes : key가 없음, 단 배열명 + 자료형을 기반으로 jsp 출력
		m.addAllAttributes(Arrays.asList(as)); //jsp에서 출력을 하기 위해서 get(), [] 로 출력이 가능함 //배열 이름만x, asList로 해줘야 jsp에서 찍기 가능
		
		return "/pay2"; //WEB-INF/views -> views에 jsp를 넣었으면 여기에 직접 파일이름을 넣어줘야함
	}
}
