export class shopping_set{
	//숫자 체크는 따로 안했..
	//숫자로 입력되어야하는 거 : 회원적립금(frm_setting1.jpoint), 최소결제포인트(frm_setting3.pminpoint), 
	//최대결제포인트(frm_setting3.pmaxpoint)
	
	check_num(){
		this.cknum = 0;	
		if(isNaN(frm_setting1.jpoint.value) || isNaN(frm_setting3.pminpoint.value) ||
			isNaN(frm_setting3.pmaxpoint.value)){
			this.cknum++;
		}
		return this.cknum;
	}
	
	check_empty(arr){
		this.data = arr;
		
		this.w = 0;
		this.count = 0;
		while(this.w < this.data.length){
			if(this.data[this.w].value == ""){
				this.count++;
			}
			this.w++;
		}		
		return this.count;
	}
	
	default_join(){
		this.data = [frm_setting1.jhometitle, frm_setting1.jemail, frm_setting1.ispoint,
		frm_setting1.jpoint, frm_setting1.jlevel];
		
		this.jcount = this.check_empty(this.data);
		
		return this.jcount;
	}
	
	default_setting(){
		//통신 판매업 신고번호, 부가통신 사업자 번호 제외 필수
		this.data2 = [frm_setting2.hcomp_name, frm_setting2.hcomp_num , frm_setting2.hceo_name, 
		frm_setting2.hceo_num, frm_setting2.hbusi_post, frm_setting2.hbusi_addr,
		frm_setting2.hadmin_name, frm_setting2.hadmin_email];
		
		this.scount = this.check_empty(this.data2);
		
		return this.scount;
	}
	
	default_pay(){
		this.data3 = [frm_setting3.pminpoint, frm_setting3.pmaxpoint, frm_setting3.pdeli_name, 
		frm_setting3.pdeli_price];
		
		this.pcount = this.check_empty(this.data3);
		
		if((frm_setting3.pbank.value != "" && frm_setting3.pbank_num.value == "") || 
			(frm_setting3.pbank.value == "" && frm_setting3.pbank_num.value != "")){
			this.pcount += 1;		
		}
		
		return this.pcount;
	}
	
}