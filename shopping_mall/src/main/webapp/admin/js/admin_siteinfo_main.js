var datalist = new Array();
//나중에 고칠 수 있으면 고쳐보기 -> datalist에 value가 아니라 태그를 직접 넣고 핸들링해보기
export function save_values(){
		datalist = [];
		datalist.push(document.getElementById("jhometitle").value); //0
        datalist.push(document.getElementById("jemail").value);	 //1
 		datalist.push(document.querySelector('input[name="jispoint"]:checked').value);
        
		datalist.push(document.getElementById("jpoint").value); //3
        datalist.push(document.getElementById("jlevel").value); //4
        
		datalist.push(document.getElementById("hcomp_name").value); //5
        datalist.push(document.getElementById("hcomp_num").value); //6
        datalist.push(document.getElementById("hceo_name").value); //7
        datalist.push(document.getElementById("hceo_num").value); //8
        datalist.push(document.getElementById("htelemk_num").value); //9
        datalist.push(document.getElementById("hadd_num").value); //10
        datalist.push(document.getElementById("hbusi_post").value); //11
        datalist.push(document.getElementById("hbusi_addr").value); //12
        datalist.push(document.getElementById("hadmin_name").value); //13
        datalist.push(document.getElementById("hadmin_email").value); //14
        
		datalist.push(document.getElementById("pbank").value); //15
        datalist.push(document.getElementById("pbank_num").value); //16

		datalist.push(document.querySelector('input[name="pcard"]:checked').value);
        datalist.push(document.querySelector('input[name="pcelphone"]:checked').value);
        datalist.push(document.querySelector('input[name="pbookgift"]:checked').value);

        datalist.push(document.getElementById("pminpoint").value); //20
        datalist.push(document.getElementById("pmaxpoint").value); //21
		
		datalist.push(document.querySelector('input[name="pcash"]:checked').value);

        datalist.push(document.getElementById("pdeli_name").value); //23
        datalist.push(document.getElementById("pdeli_price").value); //24
       
        datalist.push(document.querySelector('input[name="pdeli_date"]:checked').value);
}

export class shopping_set{
	check_num(){
		console.log(datalist[3]);
		console.log(datalist[20]);
		console.log(datalist[21]);
		
		this.cknum = 0;	
		if(isNaN(datalist[3]) || isNaN(datalist[20]) ||
			isNaN(datalist[21])){
			this.cknum++;
		}
		return this.cknum;
	}
	
	check_empty(arr){
		
		this.data = arr;
		
		this.w = 0;
		this.count = 0;
		while(this.w < this.data.length){
			if(this.data[this.w] == ""){
				this.count++;
			}
			this.w++;
		}		
		return this.count;
	}
	
	default_join(){
		this.data = [datalist[0], datalist[1], datalist[3], datalist[4]];
		
		this.jcount = this.check_empty(this.data);
		
		return this.jcount;
	}
	
	default_setting(){
		
		//통신 판매업 신고번호, 부가통신 사업자 번호 제외 필수
		this.data2 = [datalist[5], datalist[6] , datalist[7], 
		datalist[8], datalist[11], datalist[12],
		datalist[13], datalist[14]];
		
		this.scount = this.check_empty(this.data2);
		
		return this.scount;
	}
	
	default_pay(){
		
		this.data3 = [datalist[20], datalist[21], datalist[23], 
		datalist[24]];
		
		this.pcount = this.check_empty(this.data3);
		
		if((datalist[15] != "" && datalist[16] == "") || 
			(datalist[15] == "" && datalist[16] != "")){
			this.pcount += 1;		
		}
		
		return this.pcount;
	}

	ajax_data(){
		//데이터를 배열로 받겠다
			
		fetch("./admin_sitedataok.do",{
			method : "POST",
			headers : {"content-type":"application/json"},
			body : JSON.stringify(datalist)
		})
		.then(function(a){
			return a.text();
		})
		.then(function(b){
			alert("정상적으로 저장되었습니다.");
			localStorage.setItem("setting_data", JSON.stringify(datalist));
			location.reload(true);
			
		})
		.catch(function(error){
			console.log(error);
			console.log("통신 오류");
		});
	}
}

export function print_data(){
	var saved_data = JSON.parse(localStorage.getItem("setting_data"));
    
    if (saved_data) {
        document.getElementById("jhometitle").value = saved_data[0]; //0
        document.getElementById("jemail").value = saved_data[1]; //1
        document.querySelector(`input[name="jispoint"][value="${saved_data[2]}"]`).checked = true;
        
        document.getElementById("jpoint").value = saved_data[3]; //3
        document.getElementById("jlevel").value = saved_data[4]; //4
        
        document.getElementById("hcomp_name").value = saved_data[5]; //5
        document.getElementById("hcomp_num").value = saved_data[6]; //6
        document.getElementById("hceo_name").value = saved_data[7]; //7
        document.getElementById("hceo_num").value = saved_data[8]; //8
        document.getElementById("htelemk_num").value = saved_data[9]; //9
        document.getElementById("hadd_num").value = saved_data[10]; //10
        document.getElementById("hbusi_post").value = saved_data[11]; //11
        document.getElementById("hbusi_addr").value = saved_data[12]; //12
        document.getElementById("hadmin_name").value = saved_data[13]; //13
        document.getElementById("hadmin_email").value = saved_data[14]; //14
        
        document.getElementById("pbank").value = saved_data[15]; //15
        document.getElementById("pbank_num").value = saved_data[16]; //16
        document.querySelector(`input[name="pcard"][value="${saved_data[17]}"]`).checked = true;
        document.querySelector(`input[name="pcelphone"][value="${saved_data[18]}"]`).checked = true;
        document.querySelector(`input[name="pbookgift"][value="${saved_data[19]}"]`).checked = true;
        
        document.getElementById("pminpoint").value = saved_data[20]; //20
        document.getElementById("pmaxpoint").value = saved_data[21]; //21
        document.querySelector(`input[name="pcash"][value="${saved_data[22]}"]`).checked = true;
        
        document.getElementById("pdeli_name").value = saved_data[23]; //23
        document.getElementById("pdeli_price").value = saved_data[24]; //24
        document.querySelector(`input[name="pdeli_date"][value="${saved_data[25]}"]`).checked = true;
	}
}
