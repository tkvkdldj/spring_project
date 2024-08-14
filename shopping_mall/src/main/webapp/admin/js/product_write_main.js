export class send_data{

	//상품코드 난수
	make_pdcode(){
		this.w = 0;
		this.code = "";
		while(this.w < 7){
			this.rand = Math.floor(Math.random()*10);
			this.code += this.rand;
			this.w++;
		}
		return this.code;
	}
	
	//상품코드 일단 집어넣는 건 따로 + //할인율 입력시 자동으로 할인가격 추가
	ck_pdcode(){
		frm_pdwrite.pdcode.value = this.make_pdcode();
	}
	
	//상품코드 중복 확인
	ck_isdupli(){
		//중복되었을 시 자동으로 새로고침하는 것 추가
	}
	
	ck_values(){
		//판매가격 , 검사
		if(frm_pdwrite.pdname.value == "" || frm_pdwrite.pdaddexplan.value == "" ||
		frm_pdwrite.pdprice.value == ""){
			alert("값을 모두 입력해주세요.");
		}
		//첨부파일도
		else if(frm_pdwrite.pdprice.value.indexOf(',') != -1){
			alert("판매가격은 , 없이 숫자만 입력하셔야 합니다.");
		}
		else if(isNaN(frm_pdwrite.pd_discount.value)){
			alert("할인율은 숫자만 입력하셔야 합니다.");	
		}
		else if(isNaN(frm_pdwrite.pdstock.value)){
			alert("상품재고는 숫자만 입력하셔야 합니다.");	
		}
		else{
			frm_pdwrite.method = "post";
			frm_pdwrite.action = "./prouduct_writeok.do";	
			frm_pdwrite.submit();
		}
	}
	
	
	
}