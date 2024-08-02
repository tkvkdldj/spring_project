var datalist = new Array();

export function get_data() {
    return {
        jhometitle: document.getElementById("jhometitle"),
        jemail: document.getElementById("jemail").value,
        jpoint: document.getElementById("jpoint").value,
        jlevel: document.getElementById("jlevel").value,
        hcomp_name: document.getElementById("hcomp_name").value,
        hcomp_num: document.getElementById("hcomp_num").value,
        hceo_name: document.getElementById("hceo_name").value,
        hceo_num: document.getElementById("hceo_num").value,
        htelemk_num: document.getElementById("htelemk_num").value,
        hadd_num: document.getElementById("hadd_num").value,
        hbusi_post: document.getElementById("hbusi_post").value,
        hbusi_addr: document.getElementById("hbusi_addr").value,
        hadmin_name: document.getElementById("hadmin_name").value,
        hadmin_email: document.getElementById("hadmin_email").value,
        pbank: document.getElementById("pbank").value,
        pbank_num: document.getElementById("pbank_num").value,
        pminpoint: document.getElementById("pminpoint").value,
        pmaxpoint: document.getElementById("pmaxpoint").value,
        pdeli_name: document.getElementById("pdeli_name").value,
        pdeli_price: document.getElementById("pdeli_price").value,
        jispoint: document.querySelector('input[name="jispoint"]:checked').value,
        pcard: document.querySelector('input[name="pcard"]:checked').value,
        pcelphone: document.querySelector('input[name="pcelphone"]:checked').value,
        pbookgift: document.querySelector('input[name="pbookgift"]:checked').value,
        pcash: document.querySelector('input[name="pcash"]:checked').value,
        pdeli_date: document.querySelector('input[name="pdeli_date"]:checked').value
    };
}

export class shopping_set{
	
	//숫자로 입력되어야하는 거 : 회원적립금(frm_setting1.jpoint), 최소결제포인트(frm_setting3.pminpoint), 
	//최대결제포인트(frm_setting3.pmaxpoint)
	make_datalist(){
		const values = get_data();
		datalist = [];
		
		datalist.push(values.jhometitle.value);
		datalist.push(values.jemail);
		
		datalist.push(values.jispoint);
		
		datalist.push(values.jpoint);
		datalist.push(values.jlevel);
		datalist.push(values.hcomp_name);
		datalist.push(values.hcomp_num);
		datalist.push(values.hceo_name);
		datalist.push(values.hceo_num);
		datalist.push(values.htelemk_num);
		datalist.push(values.hadd_num);
		datalist.push(values.hbusi_post);
		datalist.push(values.hbusi_addr);
		datalist.push(values.hadmin_name);
		datalist.push(values.hadmin_email);
		datalist.push(values.pbank);
		datalist.push(values.pbank_num);
		
		datalist.push(values.pcard);
		datalist.push(values.pcelphone);
		datalist.push(values.pbookgift);
		
		datalist.push(values.pminpoint);
		datalist.push(values.pmaxpoint);
		
		datalist.push(values.pcash);
		
		datalist.push(values.pdeli_name);
		datalist.push(values.pdeli_price);
		
		//return this.list;
	}
	
	/* 일단 나중에 고치자... */
	check_num(){
		const values = get_data();
		
		this.cknum = 0;	
		if(isNaN(values.jpoint) || isNaN(values.pminpoint) ||
			isNaN(values.pmaxpoint)){
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
		const values = get_data();
		
		this.data = [values.jhometitle.value, values.jemail, values.ispoint,
		values.jpoint, values.jlevel];
		
		this.jcount = this.check_empty(this.data);
		
		return this.jcount;
	}
	
	default_setting(){
		const values = get_data();
		//통신 판매업 신고번호, 부가통신 사업자 번호 제외 필수
		this.data2 = [values.hcomp_name, values.hcomp_num , values.hceo_name, 
		values.hceo_num, values.hbusi_post, values.hbusi_addr,
		values.hadmin_name, values.hadmin_email];
		
		this.scount = this.check_empty(this.data2);
		
		return this.scount;
	}
	
	default_pay(){
		const values = get_data();
		
		this.data3 = [values.pminpoint, values.pmaxpoint, values.pdeli_name, 
		values.pdeli_price];
		
		this.pcount = this.check_empty(this.data3);
		
		if((values.pbank != "" && values.pbank_num == "") || 
			(values.pbank == "" && values.pbank_num != "")){
			this.pcount += 1;		
		}
		
		return this.pcount;
	}

	ajax_data(){
		//데이터를 배열로 받겠다
		this.make_datalist();
			
		fetch("./admin_sitedataok.do",{
			method : "POST",
			headers : {"content-type":"application/json"},
			body : JSON.stringify(datalist)
		})
		.then(function(a){
			return a.text();
		})
		.then(function(b){
			//console.log(b);
			alert("정상적으로 저장되었습니다.");
			location.reload(true);
			//로컬저장소에 저장시켜야겠다
			localStorage.setItem("setting_data", datalist);
		})
		.catch(function(error){
			console.log("통신 오류");
		});
		
	}
	
	get_data(){
		//this.saved_data = localStorage.getItem("setting_data");
		//console.log("뭐야?" + this.saved_data);
		
		const values = get_data();
		console.log("abc");
		values.jhometitle.value = "abc";
	}
}