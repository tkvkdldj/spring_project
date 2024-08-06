package admin;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class admin_Controller {

	PrintWriter pw = null;
	
	@Resource(name="admindml")
	private admin_dml ad;
	
	@Resource(name="adsession")
	private admin_session as;
	
	
	//상품관리-신규등록-카테고리 등록(카테고리 관리)-카테고리 등록
	@PostMapping("/admin/cate_write.do")
	public String cate_write() {
		
		return "cate_write";
	}
	
	//카테고리 생성 성공(카테고리 data insert)
	@PostMapping("/admin/cate_writeok.do")
	public String cate_writeok(@ModelAttribute("cate") cate_list_dao dao, HttpServletResponse res) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		
		try {
			int callback = ad.cate_insert(dao);
			
			if(callback > 0) {
				this.pw.print("<script>"
						+ "alert('카테고리가 정상적으로 생성 되었습니다.');"
						+ "location.href = './cate_list.do';"
						+ "</script>");
			}
		}
		catch (DuplicateKeyException dke) {
	        this.pw.print("<script>"
	                + "alert('중복된 데이터가 존재합니다.');"
	                + "history.go(-1);"
	                + "</script>");
		} 
		catch(Exception e) {
			 this.pw.print("<script>"
		                + "alert(데이터베이스 오류로 생성되지 못하였습니다.');"
		                + "history.go(-1);"
		                + "</script>");
		}
		finally {
			this.pw.close();
		}
		
		return null;
	}
	
	//상품관리-신규 등록-카테고리 등록(카테고리 관리) -> 그냥 리스트만 띄움
	@GetMapping("/admin/cate_list.do") //@SessionAttribute(name="aid", required=false) String aid, 
	public String cate_list(Model m) {
		//카테고리 데이터베이스 select해서 받아옴
		try {
			List<cate_list_dao> cate_data = ad.cate_select();
			int total = cate_data.size();
			m.addAttribute("cate_data", cate_data);
			m.addAttribute("total", total);
		}
		catch(Exception e) {
			
		}
		
		return "cate_list";
	}
	
	
	//상품관리-신규 등록 클릭
	@PostMapping("/admin/product_write.do")
	public String product_write() {
		
		return "product_write";
	}
	
	//상품관리 클릭 (로그인 제한)
	@GetMapping("/admin/product_list.do")
	public String product_list(@SessionAttribute(name="aid", required=false) String aid, HttpServletResponse res) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		if(aid == null) {
			this.pw.print("<script>"
					+ "alert('로그인 후 이용 가능합니다.');"
					+ "location.href='./';"
					+ "</script>");
			this.pw.close();
		}
		return "product_list";
	}
	
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
	
	
	//쇼핑몰 기본 설정 (로그인 제한)
	@GetMapping("/admin/admin_siteinfo.do")
	public String admin_siteinfo(@SessionAttribute(name="aid", required=false) String aid, HttpServletResponse res) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		if(aid == null) {
			this.pw.print("<script>"
					+ "alert('로그인 후 이용 가능합니다.');"
					+ "location.href = './';"
					+ "</script>");
			this.pw.close();
		}
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
				+ "location.href = './';"
				+ "</script>");
		this.pw.close();
		
		return null; 
	}
	
	
	//리스트 이동 (로그인 제한)
	@GetMapping("/admin/admin_list.do") //get으로도, post로도 -> 이걸 get으로 바꿔야할까..? 내부에서 이동할 때도 get으로 처리할까
	public String admin_main2(@SessionAttribute(name="aid", required=false) String aid, Model m, HttpServletResponse res) throws Exception{
		//jstl로 찍을 수 있게 select 값 보내기 (전체 카운트값을 보낼 수 있는 방법 없나 아)
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		if(aid == null) {
			this.pw.print("<script>"
					+ "alert('로그인 후 이용 가능합니다.');"
					+ "history.go(-1);"
					+ "</script>");
			this.pw.close();
		}
		else {			
			try {
				List<ad_member_dao> result = ad.all_select();
				int total = result.size();
				m.addAttribute("result", result);
				m.addAttribute("total", total);
			}
			catch(Exception e) {
				m.addAttribute("result", "no"); // 이건 왜 안써먹냐
			}
		}
				
		return "admin_list"; //변경함
	}
	
	
	//로그인
	@PostMapping("/admin/admin_main.do")
	public String admin_main(@RequestParam(defaultValue="", required=true) String aid, String apass, Model m, HttpSession session, 
			HttpServletResponse res) throws Exception{
		
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		
		if(aid.equals("master") && apass.equals("shop_master123")) {
			as.make_session(session, "aid", aid); //세션을 모듈에서 생성
			as.make_session(session, "aname", "홍길동");
			
			this.pw.print("<script>"
					+ "alert('로그인 되었습니다.');"
					+ "location.href = './admin_list.do';"
					+ "</script>");			
			this.pw.close();
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
