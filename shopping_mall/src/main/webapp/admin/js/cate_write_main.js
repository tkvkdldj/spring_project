export class cate_enroll{
	check_value(){
		//console.log(isNaN(frm_catewrite.cmenucode.value));
		//영어나 숫자외의 문자가 있는지 여부  
		var orvalue = frm_catewrite.cmenuname.value;
		var revalue =  frm_catewrite.cmenuname.value.replaceAll(/[^a-zA-Z가-힣]/g, '');
		console.log(orvalue);
		console.log(revalue);
		/*
		if(this.orvalue == this.revalue){
			console.log("한글, 영어 외 문자 포함 없음");	
		}
		else{
			console.log("한글, 영어 외 문자 포함");
		}
		*/
		/*
		if(frm_catewrite.cmenucode.value == "" || frm_catewrite.cmenuname.value == ""){
			alert("대메뉴 코드 및 대메뉴명을 모두 입력해주세요.");
		}
		else if(frm_catewrite.cmenucode.value.length < 2 || isNaN(frm_catewrite.cmenucode.value)){
			alert("대메뉴 코드는 최소 2자 이상의 숫자로 입력하셔야 합니다.");
		}
		else if(frm_catewrite.cemnunam.value.)
		else{
			console.log("등록");
		}
		*/
	}
	
}