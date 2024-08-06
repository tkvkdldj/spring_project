package admin;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

//select, insert, update, delete
@Repository("admindml")
public class admin_dml {
	
	@Resource(name = "template2")
	private SqlSessionTemplate tm;
	
	@Resource(name = "shapass")
	private sha_pass sp;
	
	//회원가입된 모든 관리자 select
	public List<ad_member_dao> all_select(){
		List<ad_member_dao> all_data = new ArrayList<ad_member_dao>();
		all_data = tm.selectList("Welcome_mall.select_all");
		
		return all_data;
	}
	
	//카테고리 전부 select
	public List<cate_list_dao> cate_select(){
		List<cate_list_dao> cate_data = new ArrayList<cate_list_dao>();
		cate_data = tm.selectList("Welcome_mall.cate_select");
		
		return cate_data;
	}
	
	
	//카테고리 insert
	public int cate_insert(cate_list_dao dao) {
		int result = tm.insert("Welcome_mall.cate_insert", dao);
		return result;
	}
	
	//관리자 insert -> 아이디 외의 이메일, 전화번호 중복도 생각 & 비밀번호는 암호화
	public int member_insert(ad_member_dao dao) { //왜 extends 가 안되지
		//비밀번호 암호화
		String repass = "";
		if(dao.getApass() != null) {
			repass = sp.making_secpass(dao.getApass());
		}
		//전화번호
		String retel = "";
		if(dao.getAtel() != null) {
			retel = dao.getAtel().replaceAll(",", "");
		}
		
		dao.setApass(repass);
		dao.setAtel(retel);
		
		int result = tm.insert("Welcome_mall.member_insert", dao);
		return result;
	}
	
	//아이디 체크
	public String idcheck(String aid) {
		String result = tm.selectOne("Welcome_mall.checkid", aid);
		return result;
	}
}
