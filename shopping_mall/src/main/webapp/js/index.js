var arr = [["10%","20%","30%"],["30%","40%","50%"]];
//숙제 데이터
var basket = [
	{"seq":"1", "product":"냉장고", "price":"195000"},
	{"seq":"2", "product":"세탁기", "price":"287000"},
	{"seq":"10", "product":"에어프라이어", "price":"97000"},
];


$(function(){
	//Front 배열값 응용평
	$("#btn3").click(function(){
		$.ajax({
			url : "./ajaxok3.do",
			type : "",
			cache : false,
			dataType : "text",
			contentType : "application/json",
			data : JSON.stringify(arr),
			success : function($result){
				console.log($result);
			},
			error : function(){
				console.log("error")
			}
		});
		
	});
	
	
	
	$("#btn2").click(function(){
		var $data = new Array();
		$data[0] = "홍길동";
		$data[1] = "이순신";
		$data[2] = "강감찬";
		
		//JSON.stringify : Object 형태로 전달
		$.ajax({
			url : "./ajaxok2.do",
			type : "post",
			cache : false,
			dataType : "json",
			contentType : "application/json",
			data : JSON.stringify({
				"all_data" : $data
			}),
			success : function($result){
				console.log($result);
			},
			error : function(){
				console.log("error")
			}
			
			
		});
		
	});
	
	
	$("#btn").click(function(){
		var $data = new Array();
		$data[0] = "20";
		$data[1] = "30";
		$data[2] = "40";
		//join -> JSON.stringfy 형태로 변화
		//console.log($data.join(","));
		
		
		//post는 되돌려받을 값이 없을 때
		$.ajax({
			url : "./ajaxok.do",
			cache : false,
			dataType : "json", //백엔드가 어떻게 주느냐에 따라 결정
			contentType : "application/json",
			type : "get", //백엔드가 역시 알려줘야
			data:{
				"all_data" : $data.join(",")
			},
			success : function($result){
				console.log($result);
			},
			error : function(){
				console.log("error");
			}
		});
	});
	
});