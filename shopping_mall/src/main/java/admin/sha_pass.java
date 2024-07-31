package admin;

import java.security.MessageDigest;

import org.springframework.stereotype.Repository;

@Repository("shapass")
public class sha_pass {
	public String making_secpass(String apass) {
		StringBuilder sb = new StringBuilder();
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA3-256");
			sha.update(apass.getBytes());
			for(byte bt : sha.digest()) {
				sb.append(String.format("%x", bt));
			}
		}
		catch(Exception e) {
			sb.append("인자값 오류 발생"); 
		}
		
		return sb.toString();
	}
}
