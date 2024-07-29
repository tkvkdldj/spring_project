package com.welcomeshop.www;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

//user table (select,insert,update,delete)
@Repository("userselect")
public class user_select {
	
	//이제는 DB연결도 모듈에서
	@Resource(name="template")
	private SqlSessionTemplate tm;
	
	public ArrayList<Object> search_id(String uname, String uemail) { //1명의 정보 (아이디 찾기) //아이디만 찾는거니까 String으로 리턴해도 됨
		ArrayList<Object> onedata = new ArrayList<Object>();
		Map<String, String> keycode = new HashMap<String, String>();
		keycode.put("part", "1");
		keycode.put("uname", uname);
		keycode.put("uemail", uemail);
		
		//mapper에서 작성한 select 문이 *로 들고오기 때문에 받는 건 dao로 받을 거임
		user_dao dao = tm.selectOne("Shopping_mall.search", keycode);
		System.out.println(dao.getUid());
		return onedata;
	}
}
