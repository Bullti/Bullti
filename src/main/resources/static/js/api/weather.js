var geocoder = new kakao.maps.services.Geocoder();
var rs;
// 주소로 좌표를 검색합니다
geocoder.addressSearch('상계동', function(result, status) {
	// 정상적으로 검색이 완료됐으면 
	if (status === kakao.maps.services.Status.OK) {
		var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		console.log("경도:" + result[0].x);//경도
		console.log("위도:" + result[0].y);//위도

		rs = dfs_xy_conv("toXY", result[0].y, result[0].x);
		console.log("x:" + rs.x);
		console.log("y:" + rs.y);

		$.ajax({
			url : "/weather",
			data : rs,
			success : function(resulthtml) {
				$("#weather-data").html(resulthtml);
			}
		});
	}
});