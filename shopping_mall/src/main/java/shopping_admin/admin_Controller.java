package shopping_admin;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class admin_Controller {

	PrintWriter pw = null;
	
	@Resource(name="admindml")
	private admin_dml ad;
	
	@PostMapping("/admin/admin_main.do")
	public String admin_main(String aid, String apass, HttpServletResponse res) throws Exception{
		System.out.println(aid);
		System.out.println(apass);
		/*
		this.pw = res.getWriter();
		this.pw.print("<script>"
				+ "location.href='./admin_list.jsp';"
				+ "</script>");
		this.pw.close();
		*/
		//admin_list.jsp 이름을 admin_main으로 바꿔서 url을 do로 띄워지게 해야할 것 같은데
		return null;
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
}
