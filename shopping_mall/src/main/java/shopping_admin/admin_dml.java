package shopping_admin;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

//select, insert, update, delete
@Repository("admindml")
public class admin_dml {
	
	@Resource(name = "template2")
	private SqlSessionTemplate tm;
	
	//관리자 insert -> 아이디 외의 이메일, 전화번호 중복도 생각 (??) & 비밀번호는 암호화 (이메일 형식 체크도 해야함,전화번호도 - 없어야함)
	
	
	//아이디 체크
	public String idcheck(String aid) {
		String result = tm.selectOne("Welcome_mall.checkid", aid);
		return result;
	}
}
