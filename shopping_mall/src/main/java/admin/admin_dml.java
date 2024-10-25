package admin;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@Resource(name= "pdimgsave")
	private product_img_save pis;
	
	//상품 전체 select
	public List<product_list_dao> allpd_select(){
		List<product_list_dao> all_data = new ArrayList<product_list_dao>();
		all_data = tm.selectList("Welcome_mall.product_select");
		
		return all_data;
	}
	
	//상품코드 select
	public String pdcode_select(String pdcode) {
		String result = tm.selectOne("Welcome_mall.product_code", pdcode);
		return result;
	}
	
	
	//상품 insert
	public int pd_insert(product_list_dao dao) {
		//첨부파일 이름을 받아야
		ArrayList<String> or_al = pis.imgs_ornm;
		ArrayList<String> re_al = pis.imgs_renm;
		
		String ornames = String.join(",", or_al);
		String renames = String.join(",", re_al);
		
		dao.setPdorimage(ornames);
		dao.setPdreimage(renames);
		
		int result = tm.insert("Welcome_mall.product_insert",dao);
		return result;
	}


/*
	//상품코드 중복 확인
	public int pdcode_ck(String pdcode) {
		int result = tm.selectOne("Welcome_mall.pdcode_ck", pdcode);
		return result;
	}
*/	

	//카테고리 delete
	public int cate_delete(String cidx[]) {
		int count = 0;
		int f;
		for(f=0; f<cidx.length; f++) {
			tm.delete("Welcome_mall.cate_delete", cidx[f]);
			count++;
		}
		
		return count;
	}
	
	
	//회원가입된 모든 관리자 select
	public List<ad_member_dao> all_select(){
		List<ad_member_dao> all_data = new ArrayList<ad_member_dao>();
		all_data = tm.selectList("Welcome_mall.select_all");
		
		return all_data;
	}
	
	//카테고리 검색 select
	public List<cate_list_dao> cate_select(String part, String search){
		List<cate_list_dao> search_data = new ArrayList<cate_list_dao>();
		
		Map<String, String> m = new HashMap<String, String>();
		m.put("part", part);
		m.put("search", search);
		
		search_data = tm.selectList("Welcome_mall.cate_search", m);
		
		return search_data;
	}
	
	//카테고리 전부 select
	public List<cate_list_dao> cate_select(){
		List<cate_list_dao> cate_data = new ArrayList<cate_list_dao>();
		cate_data = tm.selectList("Welcome_mall.cate_select");
		
		return cate_data;
	}
	
	//카테고리코드만 select
	public List<String> cate_code(){
		List<String> code = new ArrayList<String>();
		code = tm.selectList("Welcome_mall.cate_code");
		
		return code;
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
