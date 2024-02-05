/**
 * 
 */
function createQr() {
	fetch('/emp/atte/cqr')
	  .then(response => response.blob())
	  .then(blob => {
        // Blob 데이터를 URL로 변환
        const imageUrl = URL.createObjectURL(blob);

        // 이미지를 표시하는 HTML 요소에 설정
        const imgElement = document.getElementById('qr-atte');
        imgElement.src = imageUrl;
        document.getElementById('workCheck').style.display = 'none';
	  })
	  .catch(error => console.error('fetch 중 에러:', error));
}

// 초기에 한 번 실행
workingTime().then(resultTime => {
  updateClock(resultTime);
});

function updateClock(resultTime) {
  // resultTime을 Date 객체로 변환
  var targetTime = new Date(resultTime);

  function updateTime() {
    var now = new Date();
    var timeDiff = now - targetTime;

    if (timeDiff <= 0) {
      timeDiff = 0;
      // 시간이 지난 후에는 작업을 중단하거나 새로운 시간을 가져오는 등의 작업을 수행할 수 있습니다.
    }

    var hours = Math.floor(timeDiff / (1000 * 60 * 60));
    var minutes = Math.floor((timeDiff % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = Math.floor((timeDiff % (1000 * 60)) / 1000);

    // 시, 분, 초가 한 자리일 경우 앞에 0 추가
    seconds = seconds < 10 ? '0' + seconds : seconds;

    var timeString = '';

    if (hours > 0) {
      timeString += hours + '시간';
    }
    if (minutes > 0 || hours > 0) {
      timeString += minutes + '분';
    }
    timeString += seconds + '초';

    document.getElementById('workingTime').innerText = timeString;
  }

  if (resultTime == "") {
    document.getElementById('workingTime').innerText = '근무 중 아님';
    document.getElementById('workCheck').innerText = '출근';
  } else {
    updateTime();
    document.getElementById('workCheck').innerText = '퇴근';
  }

  // 다음 프레임에서 updateTime 함수를 1초마다 호출
  setInterval(updateTime, 1000);
}



async function workingTime() {
	return fetch('/emp/atte/status')
	  .then(response => {
	    if (!response.ok) {
	      throw new Error(`HTTP error! status: ${response.status}`);
	    }
	    return response.text(); // 또는 response.text()를 사용하여 텍스트로 읽을 수 있습니다.
	  })
	  .then(data => {
	    return data; // 여기서 데이터를 사용하여 원하는 작업을 수행합니다.
	  })
	  .catch(error => console.error('fetch 중 에러:', error));
}