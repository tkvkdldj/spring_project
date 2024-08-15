var idck = "";
var cktext = document.getElementById("cktext");
/* 
아이디 값이 사라졌을 때 자동으로 cktext도 실시간 사라지는 것 구현하기 => ok
*/

export class enroll_member{
	detect_id(){
		idck = "";
		cktext.innerText = "";
	}
	
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
		else{ 
			if(idck == "ok"){ //아이디 중복시 날아가지 않게 하는 것도 핸들링
				if(frm_enroll.apass[0].value != frm_enroll.apass[1].value){
					alert("패스워드가 일치하지 않습니다.");
				}
				else if(frm_enroll.aemail.value.indexOf("@") == -1){ //이메일 체크
					alert("제대로된 형식의 이메일 입력해주세요.");	
				}
				else if(isNaN(frm_enroll.atel[0].value) || isNaN(frm_enroll.atel[1].value) || isNaN(frm_enroll.atel[2].value)){ //전화번호 체크
					alert("전화번호는 숫자만 입력 가능합니다.");
				}	
				else{
					frm_enroll.method="post";
					frm_enroll.action="./enrollok.do";
					frm_enroll.submit();	
				}
			}
			else if(idck == "no"){
				alert("중복된 아이디는 등록하실 수 없습니다.");
			}
			else{
				alert("아이디 중복 체크해주세요.");
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
				cktext.innerText = "사용 가능한 아이디입니다.";
				cktext.style.color = "green";
			}
			else{
				cktext.innerText = "이미 사용 중인 아이디입니다.";
				cktext.style.color = "red";
				frm_enroll.aid.focus();
			}
			idck = b;
			
		}).catch(function(error){
			console.log("error");
		});
		
	}
}