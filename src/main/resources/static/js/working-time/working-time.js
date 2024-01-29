/**
 * 
 */

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
    hours = hours < 10 ? '0' + hours : hours;
    minutes = minutes < 10 ? '0' + minutes : minutes;
    seconds = seconds < 10 ? '0' + seconds : seconds;

    var timeString = hours + ':' + minutes + ':' + seconds;

    document.getElementById('clock').innerText = timeString;

    // 다음 프레임에서 updateTime 함수 호출
    requestAnimationFrame(updateTime);
  }

  updateTime();
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