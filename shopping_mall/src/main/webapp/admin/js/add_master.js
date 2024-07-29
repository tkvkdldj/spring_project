var idck = "";

export class enroll_member{
	send_data(){
		this.data = [frm_enroll.aid, frm_enroll.apass[0], frm_enroll.apass[1], frm_enroll.aname
		,frm_enroll.aemail, frm_enroll.atel[0], frm_enroll.atel[1], frm_enroll.atel[2], 
		frm_enroll.adepart, frm_enroll.aposition];
		
		//빈 값이 있는 지 확인
		this.w = 0;
		this.count = 0;
		while(this.w < this.data.length){
			if(this.data[this.w].value == ""){
				this.count++;
			}
			this.w++;
		}
		
		if(this.count > 0){ //하나라도 빈값이 있다는 것
			alert("모든 정보를 입력하셔야만 등록이 가능합니다.");
		}
		else{ //아이디 중복시 날아가지 않게 하는 것도 핸들링
			if(idck == "ok"){
				frm_enroll.method="post";
				frm_enroll.action="./enrollok.do";
				frm_enroll.submit();	
			}
			else{
				alert("중복된 아이디는 등록하실 수 없습니다.");
			}
		}
	}
	
	check_id(aid){
		this.id = "aid="+aid; // 이걸 꼭 해줘야하는 구나
		fetch("./checkidok.do",{
			method : "POST",
			headers : {"content-type":"application/x-www-form-urlencoded"},
			body : this.id
		}).then(function(a){
			return a.text();
			
		}).then(function(b){
			if(b == "ok"){
				alert("가입 가능한 아이디 입니다.");
			}
			else{
				alert("중복된 아이디 입니다.");
				frm_enroll.aid.focus();
			}
			idck = b;
			
		}).catch(function(error){
			console.log("error");
		});
		
	}
}