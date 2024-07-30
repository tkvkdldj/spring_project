export class sign_in{
	admin_in(){
		if(frm_login.aid.value == "master" && frm_login.apass.value == "shop_master123"){
			frm_login.method = "post";
			frm_login.action = "./admin_main.do";
			frm_login.submit();
		}
		else{
			alert("관리자 아이디 및 패스워드가 아님");
		}
	}
}