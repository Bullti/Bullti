/**
 * 
 */
//결재선 부서
	function getEmployee(elemental) {
		$('.clickable').css("color", "");

		// jQuery로 감싸지 않은 경우 jQuery로 감싸기
		var $elemental = $(elemental);

		// 텍스트 색상을 파란색으로 변경
		$elemental.css("color", "blue");

		let deptName = elemental.textContent;
		$.ajax({
			type : "GET",
			url : "/approval/members", // 
			data : {
				deptName : deptName
			},
			dataType : "json",
			success : function(data) {
				displayEmployeeList(data);
			},
			error : function(error) {
				console.error("Ajax 요청 중 에러 발생:", error);
			}
		});
	}

	var target;
	function displayEmployeeList(employeeList) {
		// em-form 요소 초기화
		$(".em-form").empty();

		// target 요소 초기화
		target = null;

		// 받아온 employeeList를 이용하여 목록 생성 및 추가
		for (var i = 0; i < employeeList.length; i++) {
			var employee = employeeList[i];
			var employeeInfo = "<p class='target-list'>" + employee.name + " ("
					+ employee.rank
					+ ")<input type='hidden' value='"+ employee.id + "'></p>";

			$(".em-form").append(employeeInfo);
		}

		// 기존 클릭 이벤트 핸들러 제거
		$(".em-form").off("click", ".target-list");

		$(".em-form").on("click", ".target-list", function() {
			$(".target-list").css("background-color", "");
			$(this).css("background-color", "#a9a9a9");
			target = $(this)
		});
	}
	function addAproval() {
		var targetText = target.text();

		// 이미 해당 텍스트를 가진 요소가 존재하는지 확인
		var existingTarget = $(".re-form .wrap").find(".target-list").filter(
				function() {
					return $(this).text() === targetText;
				});

		if (existingTarget.length === 0) {
			let clone = target.clone();
			clone.css("background-color", "");
			$(".re-form .wrap").append(clone);
		} else {
			alert("결재선에 등록되어 있는 사원입니다.")
		}

		$(".re-form").on("click", ".target-list", function() {
			$(".target-list").css("background-color", "");
			$(this).css("background-color", "#a9a9a9");
			target = $(this)
		});
	}
	function removeAproval() {
		if (target && target.closest('.re-form').length) {
			target.remove();
			target = null;
		}
	}
	
	 function approval_line() {
			    var empArr = [];
			 
		        var inputValues = $(".re-form .wrap p input");
		        for(let i=0; i<inputValues.length; i++){
		        	empArr.push($(inputValues[i]).val());
		        }
		        
		        $.ajax({
		            type: "POST",
		            url: "/approval/line",
		            data: {emps: empArr},
		            success: function(data) {
		                $("#exchange").html(data);
		                btn_approval();
		            },
		            error: function(error) {
		                console.error("Ajax 요청 중 에러 발생:", error);
		            }
		        });
		    };