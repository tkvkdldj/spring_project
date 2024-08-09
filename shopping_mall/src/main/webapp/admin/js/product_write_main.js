export class send_data{
	go_page(){
		frm_pdwrite.method = "post";
		frm_pdwrite.action = "./prouduct_writeok.do";	
		frm_pdwrite.submit();
	}
}