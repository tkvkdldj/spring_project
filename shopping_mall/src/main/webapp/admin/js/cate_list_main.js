export class delete_cate{
	//frm_catewrite
	
	//각 체크박스의 체크 해제 여부 -> 하나라도 해제 되면 전체 체크 해제
	//이걸 각 체크박스를 클릭할 때마다 가져와야하는데 id 중복을 못함...
	count_each_ck(){
		this.eachdata = document.getElementsByName("each_ck");
		this.w = 0;
		this.false_count = 0;
		while(this.w < this.eachdata.length){
			if(this.eachdata[this.w].checked == true){
				this.false_count++;
			} 
			this.w++;
		}		
		return this.false_count;
	}

	//전체 체크박스 체크에 따른 각 체크박스
	handling_all_ck(){
		this.eachdata = document.getElementsByName("each_ck");
		this.w = 0;
		while(this.w < this.eachdata.length){
			this.eachdata[this.w].checked = frm_catewrite.all_ck.checked; 
			this.w++;
		}
		
		//this.false_ck = this.count_each_ck();
		//console.log(this.false_ck);
	}
}