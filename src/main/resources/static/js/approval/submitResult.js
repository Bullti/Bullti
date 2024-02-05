/**
 * 
 */
function submitForm(action) {
	
	let form = document.getElementById("approvalId");
	if (action == "/approval/wait-list") {
		form.setAttribute("method", "get");
	} else {
		form.setAttribute("method", "post");
	}

	form.action = action;
	form.submit();
}