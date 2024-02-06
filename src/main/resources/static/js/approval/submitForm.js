/**
 * 
 */
function submitForm(action) {
	
	let exchangeDiv = document.getElementById('exchange');
	let exchangeValue = exchangeDiv.innerHTML.trim();

	if (exchangeValue === '') {
		alert('결재선을 지정해주세요');
		return;
	}
	
	let titleInputs = document.getElementsByClassName('title-content');
	let titleInput = titleInputs[0].value;
	
	if (titleInput === ''){
		alert('제목을 입력해주세요');
		return;
	}
	
	let form = document.getElementById("approvalId");
	if (action == "/approval/wait-list") {
		form.setAttribute("method", "get");
	} else {
		form.setAttribute("method", "post");
	}

	form.action = action;
	form.submit();
}