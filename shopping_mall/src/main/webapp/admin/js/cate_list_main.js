//fucntion에는 this가 안먹음

export class delete_cate{
	//frm_catewrite
	//각 체크박스의 체크 해제 여부 -> 하나라도 해제 되면 전체 체크 해제
	count_each_ck(){
		this.all_ck = document.getElementById("all_ck");
		this.eachdata = document.getElementsByName("each_ck");
		this.w = 0;
		this.true_count = 0;
		
		while(this.w < this.eachdata.length){
			if(this.eachdata[this.w].checked == true){
				this.true_count++;
			} 
			this.w++;
		}	
		
		if(this.true_count == this.eachdata.length){
			this.all_ck.checked = true;
		}
		else{
			this.all_ck.checked  = false;
		}
    }
	
	//전체 체크박스 체크에 따른 각 체크박스
	handling_all_ck(){
		this.all_ck = document.getElementById("all_ck");
		this.eachdata = document.getElementsByName("each_ck");
		this.w = 0;
		while(this.w < this.eachdata.length){
			this.eachdata[this.w].checked = this.all_ck.checked; 
			this.w++;
		}
	}
}