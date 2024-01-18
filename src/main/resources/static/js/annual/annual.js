/**
 * 
 */


{
	// start 날짜는 오늘부터 지정 가능
	let today = new Date();
	document.getElementById('start').value = today.toISOString().split('T')[0];
	document.getElementById('start').min = today.toISOString().split('T')[0];
	endProperties();
	typeClickView();
}

// start가 변경될 때 end의 최소 날짜를 설정
document.getElementById('start').addEventListener('change', endProperties) 

//end 날짜설정
function endProperties() {
    let startDate = new Date(document.getElementById('start').value);
    startDate.setDate(startDate.getDate()); // start 다음 날짜
    document.getElementById('end').min = startDate.toISOString().split('T')[0];
};

//휴가 종류별로 메뉴 보이게하기
document.getElementById('type').addEventListener('change', typeClickView)

function typeClickView() {
	let selectedtype = document.getElementById('type').value;
	let specialTextElement = document.getElementById(selectedtype);
	let selectViewElements = document.getElementsByClassName('selectView');

	for (let i = 0; i < selectViewElements.length; i++) {
	    selectViewElements[i].style.display = 'none';
	}
	
	if (selectedtype != 'halfDay') {
		document.getElementById('fullDay').style.display = 'inline-block';
	}
	specialTextElement.style.display = 'inline-block';
}