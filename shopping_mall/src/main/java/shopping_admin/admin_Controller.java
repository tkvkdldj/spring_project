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
		this.pw = res.getWriter();
		this.pw.print("<script>"
				+ "alert('test');"
				+ "</script>");
		this.pw.close();
		ad.member_insert(dao);
		return null;
	}
}
