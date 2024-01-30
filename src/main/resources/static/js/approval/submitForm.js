/**
 * 
 */
function submitForm(action) {
		
		if(action == '/approval'){
			let check = document.getElementById("exchange").innerHTML;
			if(!check || check.trim() == null){
				return alert("결재선을 지정해주세요");
			}
		}
			
		// JavaScript를 사용하여 폼의 action 값을 변경하고 서브밋
	 	var form = document.getElementById("approvalId");
		form.action = action;
		form.submit();
		
	 }