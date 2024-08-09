package admin;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

//이미지 저장을 여기서 할거임, 컨트롤러에서는 이름만 받아옴
@Repository("pdimgsave")
public class product_img_save {
	ArrayList<String> imgs_ornm = null;
	ArrayList<String> imgs_renm = null;
	
	//컨트롤러에서 현재 실행
	public void save_imgs() {
		
	}
	
	public void get_imgs(MultipartFile imgs[], HttpServletRequest req) {
		//같은 name의 파일들을 원시배열로 받았을 때 size를 불러오면 첨부 여부와 상관없이 name 개수가 찍힘
		//이미지 이름 집어넣는 원시배열
		this.imgs_ornm = new ArrayList<String>();
		this.imgs_renm = new ArrayList<String>();
		
		int w = 0;
		
		while(w < imgs.length) {
			if((imgs[w].getSize() > 0)) { //파일이 있으면 (첨부파일 용량 제한은 프론트에서)
				String nm = imgs[w].getOriginalFilename(); //파일명을 가져와
				imgs_ornm.add(nm); //배열에 넣
				
				String renm = this.rename(nm);
				imgs_renm.add(renm);
			}
			w++;	
		}
	}
	
	//사본명 제작
	public String rename(String orname) {
		int period = orname.lastIndexOf(".");
		String wd = orname.substring(period);
		String img_renm = this.rand_num() + wd;
				
		return img_renm;
	}
	
	
	//저장한날짜+랜덤숫자
	public String rand_num() {
		Date day = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyymmdd");
		String today = sf.format(day);
		
		int no = (int)Math.ceil(Math.random()*1000);
		String datacode = today + no;
		return datacode;
	}
	
	//저장시킬 url 가져와
	//String url = req.getServletContext().getRealPath("/upload/");
	//D:\spring_project\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\shopping_mall
}
