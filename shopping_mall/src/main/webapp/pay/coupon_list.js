var pageno = ""; //페이지 번호
var uri = window.location.search; //웹 URL의 ?부터 파라미터값 가져옴
//console.log(uri);

if(uri == ""){ //최초 접속 시
	pageno = 1;
}
else{ //페이지 번호 클릭 시
	pageno = uri.split("?page=")[1];	
}
