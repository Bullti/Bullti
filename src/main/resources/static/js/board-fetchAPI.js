/**
 * //then 은 성공시 발생
 * //catch는 에러발생시 실행
 * //fetch API를 쓰면 간결하고 가벼워진다.
 * 
 */

//*
document.addEventListener("DOMContentLoaded", () => boardList2());

function boardList2() { //event.preventDefault();처리를 안해서 동기처리임
    fetchAndDisplay("/members/notice"); //GET매핑 실행
}
//*/
function boardList(element,event) {
	event&&event.preventDefault();
	const formElement = document.querySelector("#search-form");
	const form = new FormData(formElement);
	const params = new URLSearchParams(form).toString();
	console.log("params: "+params);
	var url=element.getAttribute("href")+"&"+params;
    fetchAndDisplay(url); //GET매핑 실행
}

function searchClicked(element, event){
	event.preventDefault();
	const form = new FormData(element); //get은 불가능 post나 put
	const params = new URLSearchParams(form).toString();
	console.log(">>>"+params);
	fetchAndDisplay(`/members/notice?${params}`); 
}

/**
 * url: 데이터를 가져올 URL
 * method: HTTP 요청 메소드 (기본값은 "GET"이며, 선택적으로 지정 가능)
 * target: 결과를 표시할 HTML 요소의 선택자 (기본값은 #content-area이며, 선택적으로 지정 가능)
 * callback: 데이터를 표시한 후 호출할 콜백 함수 (선택적으로 지정 가능)
 */
function fetchAndDisplay(url, method = "GET", target = "#content-area", callback) { //디폴트 GET
    fetch(url, { method })
        .then(response => response.text())//텍스트 데이터를 추출
        .then(result => {
			//server에서 리턴이 void인경우 텍스트변환결과가= ""
            if(result!="")document.querySelector(target).innerHTML = result;
            callback && callback();// 만약 콜백 함수 (callback)가 정의되어 있다면 실행
        })
        .catch(error => console.error(error));
}


function boardDetail(element, event) {
    event.preventDefault();
    fetchAndDisplay(element.getAttribute("href"));
}

function deleteBoard(element, event, type) {
    event.preventDefault();
    fetchAndDisplay(element.getAttribute("href"), type, null, boardList);
}

function updateBoard(element, event, type) {
    event.preventDefault();
    const url = element.getAttribute("action");
    const form = new FormData(element);
    const boardNo=element.getAttribute("data-no");

    fetch(url, { method: type, body: form })
        .then(() => fetchAndDisplay(`/boards/${boardNo}`, "GET", "#content-area"))
        .catch(error => console.error(error));
}

function updateForm(status) {
    const updateFormElement = document.querySelector("#update-form");
    const detailViewElement = document.querySelector("#detail-view");

    updateFormElement.style.display = status ? "block" : "none";
    detailViewElement.style.display = !status ? "block" : "none";
}

document.addEventListener('DOMContentLoaded', () => {
    const boardLinks = document.querySelectorAll('.d a');
    boardLinks.forEach(link => {
        link.addEventListener('click', event => {
            event.preventDefault();
            const boardNo = link.getAttribute('href').split('/').pop();
            fetch('/members/notice/' + boardNo)
                .then(response => response.text())
                .then(data => {
                     const contentArea = document.querySelector('#content-area');
					    const notice = JSON.parse(data);
					    const tableHTML = `
					        <table>
					            <thead>
					                <tr>
					                    <td class="a">글번호</td>
					                    <td class="b">작성자</td>
					                    <td class="c">부서</td>
					                    <td class="d">제목</td>
					                    <td class="e">조회수</td>
					                    <td class="f">작성일</td>
					                </tr>
					            </thead>
					            <tbody>
					                <tr>
					                    <td class="a">${notice.boardNo}</td>
					                    <td class="b">${notice.name}</td>
					                    <td class="c">${notice.deptName}</td>
					                    <td class="d">${notice.boardTitle}</td>
					                    <td class="e">${notice.boardHitcnt}</td>
					                    <td class="f">${new Date(notice.createdDatetime).toLocaleString()}</td>
					                </tr>
					            </tbody>
					        </table>`;
					    contentArea.innerHTML = tableHTML;
                })
                .catch(error => console.error(error));
        });
    });
});

