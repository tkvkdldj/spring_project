var menuname = frm_catewrite.cmenuname; //value값을 가져오는게 아니라 태그를 가져오면 먹힌다

export class cate_enroll{
	check_value(){
		//대메뉴 이름 비교
		this.orname = menuname.value;
		this.rename = menuname.value.replace(/[^a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣\s]/g, '');
		
		if(frm_catewrite.cmenucode.value == "" || frm_catewrite.cmenuname.value == ""){
			alert("대메뉴 코드 및 대메뉴명을 모두 입력해주세요.");
		}
		else if(frm_catewrite.cmenucode.value.length < 2 || isNaN(frm_catewrite.cmenucode.value)){
			alert("대메뉴 코드는 최소 2자 이상의 숫자로 입력하셔야 합니다.");
		}
		else if(this.orname != this.rename){
			alert("대메뉴 이름은 한글 및 영어만 입력 가능합니다.");
		}
		else{
			frm_catewrite.method = "post";
			frm_catewrite.action = "./cate_list.do";
			frm_catewrite.submit();
		}
	}
}