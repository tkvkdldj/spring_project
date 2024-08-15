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
		this.pdcode = frm_pdwrite.pdcode.value;
		this.code = "pdcode="+this.pdcode; // 이걸 꼭 해줘야하는 구나
		
		fetch("./checkpcodeok.do",{
			method : "POST",
			headers : {"content-type":"application/x-www-form-urlencoded"},
			body : this.code
		}).then(function(a){
			return a.text();
			
		}).then(function(b){
			this.ck_div = document.getElementById('ck_code'); 
			if(b == "ok"){
				this.ck_div.innerText = "사용 가능한 상품코드입니다.";
            	this.ck_div.style.color = "green";
			}
			else if(b == "no"){
				this.ck_div.innerText = "이미 사용된 상품코드입니다.";
            	this.ck_div.style.color = "red";
			}
		}.bind(this)).catch(function(error){
			console.log("error");
		});
	}
	
	ck_values(){
		//값 체크
		if(frm_pdwrite.pdname.value == "" || frm_pdwrite.pdaddexplan.value == "" ||
		frm_pdwrite.pdprice.value == "" || frm_pdwrite.pd_detailexplan.value == ""){
			alert("값을 모두 입력해주세요.");
		}
		else if(frm_pdwrite.pdimage[0].value == ""){
			alert("상품 대표이미지는 최소 한 개 이상 첨부하셔야 합니다.")
		}
		else if(frm_pdwrite.pdprice.value.indexOf(',') != -1){
			alert("판매가격은 , 없이 숫자만 입력하셔야 합니다.");
		}
		else if(isNaN(frm_pdwrite.pd_discount.value) || isNaN(frm_pdwrite.pdstock.value)){
			alert("할인율 및 상품재고는 숫자만 입력하셔야 합니다.");	
		}
		else{
			//첨부파일 검사여부 여기서
			this.ck_div = document.getElementById('ck_code'); 
			this.callback = this.ck_images();
		
			if(this.callback == "no_img"){
				alert("이미지 파일만 첨부 가능합니다.");
			}
			else if(this.callback == "no_size"){
				alert("이미지 파일 용량은 2MB까지 가능합니다.");
			}
			else if(this.ck_div.innerText == ""){
				alert("상품코드 중복확인을 해주세요.");
			}
			else if(this.ck_div.style.color == "red"){
				alert("중복된 상품코드는 등록할 수 없습니다.");
			}
			else{
				frm_pdwrite.method = "post";
				frm_pdwrite.action = "./prouduct_writeok.do";	
				frm_pdwrite.submit();
			}
		}
		
	}
	
	//이미지 속성, 파일 사이즈 체크
	ck_images(){
		this.extension = ["gif","jpg","jpeg","bmp","wmf","emf","png","svg","webp"];
		this.imgs = document.getElementsByName("pdimage");
		this.result = "";
		
		this.w = 0
		while(this.w < this.imgs.length){
			
			if(this.imgs[this.w].value != ""){
				
				this.index = this.imgs[this.w].files[0].name.lastIndexOf('.') + 1;
				this.ext_nm = this.imgs[this.w].files[0].name.substr(this.index);
				
				if(this.extension.includes(this.ext_nm)){ //이미지가 맞을 때
					if(this.imgs[this.w].files[0].size > 2097152){ //용량이 클때
						this.result = "no_size"
					}
					else{
						this.result = "ok";
					}
				}
				else{ //이미지가 아닐 때
					this.result = "no_img";
				}
			}	
			this.w++;
		}
		return this.result;
	}
	
	discount_price(){
		if(frm_pdwrite.pdprice.value != ""){
			this.price = frm_pdwrite.pdprice.value;
			this.discount = frm_pdwrite.pd_discount.value;
		
			frm_pdwrite.pd_disprice.value = this.price - (this.price * this.discount * 0.01);	
		}
	}
	
}