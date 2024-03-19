const sendBoardList = function(){
    
    const packet = {
     cmd : 'boardlist'
    };

    const jsonStr = JSON.stringify(packet);     // js객체 -> json문자열
    sendMessage(jsonStr);
    }

// 서버로부터 응답처리
// const requestboard = function (message) {
//     // json문자열 -> js 객체로 변환
//     const msgObj = JSON.parse(message);

//     switch (msgObj.cmd) {
//       case 'boardlist':
//         const postList = document.querySelector('#postList');
//         postList.innerHTML='';
//         msgObj.result.forEach(element => {
//             const li = document.createElement('li');
//             li.innerHTML = element.subject;
//             // li.innerHTML += `<br>${element.date}`        
//             postList.appendChild(li);
//         });
//         console.log(msgObj.result)
//     }
// }
const requestboard = function (message) {
    // 결과를 출력할 부모 요소 선택
    const parentElement = document.getElementById('resultTable');
    const msgObj = JSON.parse(message);

    // 이전에 생성된 테이블이 있다면 삭제
    while (parentElement.firstChild) {
        parentElement.removeChild(parentElement.firstChild);
    }

    // 테이블 열 제목 생성
    const table = document.createElement('table');
    table.style.border = "1px solid black"; // 테이블 경계선 추가
    table.style.width = "100%"; // 테이블 전체 너비 설정


    const tableHeader = document.createElement('tr');
    const headers = ['INDEX', '제목', '이름', '등록일', '조회수']; // 열 제목들

    // 테이블 열 제목 추가
    headers.forEach(headerText => {
        const th = document.createElement('th');
        th.textContent = headerText;
        th.style.width = (headerText === '제목') ? "30%" : "20%"; // 제목 셀만 너비를 늘림
        th.style.color = "white"; // 흰색 글씨
        th.style.backgroundColor = "black"; // 검은색 바탕
        th.style.textAlign = "center"; // 헤더 셀 중앙 정렬
        tableHeader.appendChild(th);
    });
    table.appendChild(tableHeader);

    for(let i=0; i<msgObj.result.length; i++){
        const row = document.createElement('tr');

        const cell1 = document.createElement('td');
        cell1.textContent = msgObj.result[i].indexNum;
        cell1.style.textAlign = "center";
        row.appendChild(cell1);

        const cell2 = document.createElement('td');
        cell2.textContent = msgObj.result[i].subject;
        cell2.style.textAlign = "center";
        row.appendChild(cell2);

        const cell3 = document.createElement('td');
        cell3.textContent = msgObj.result[i].name;
        cell3.style.textAlign = "center";
        row.appendChild(cell3);

        const cell4 = document.createElement('td');
        cell4.textContent = msgObj.result[i].date;
        cell4.style.textAlign = "center";
        row.appendChild(cell4);

        const cell5 = document.createElement('td');
        cell5.textContent = msgObj.result[i].views;
        cell5.style.textAlign = "center";
        row.appendChild(cell5);

        table.appendChild(row);
    }

    // 테이블 경계선 스타일 적용
    const tableRows = table.querySelectorAll('tr');
    tableRows.forEach(row => {
        row.style.border = "1px solid black";
    });

    // 부모 요소에 테이블 추가
    parentElement.appendChild(table);
};



document.addEventListener('DOMContentLoaded', ()=>{
    const listbtn = document.querySelector('#board-list');

    listbtn.addEventListener('click', ()=>{
        sendBoardList();
    });
});