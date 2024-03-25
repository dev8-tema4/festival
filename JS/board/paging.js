const pageboard = function (message) {
    // 결과를 출력할 부모 요소 선택
    const parentElement = document.getElementById('resultTable');
    const msgObj = JSON.parse(message);

    console.log(msgObj)

    // 이전에 생성된 테이블이 있다면 삭제
    while (parentElement.firstChild) {
        parentElement.removeChild(parentElement.firstChild);
    }

    // 테이블 열 제목 생성
    const table = document.createElement('table');
    table.style.border = "1px solid black"; // 테이블 경계선 추가
    table.style.width = "100%"; // 테이블 전체 너비 설정
    table.style.marginBottom = "20px"; // Add margin to the bottom

    const tableHeader = document.createElement('tr');
    const headers = ['INDEX', '제목', '이름', '등록일', '조회수', '카테고리']; // 열 제목들

    // 테이블 열 제목 추가
    headers.forEach(headerText => {
        const th = document.createElement('th');
        th.textContent = headerText;
        th.style.width = (headerText === '제목') ? "40%" : "10%"; // 제목 셀만 너비를 늘림
        th.style.color = "white"; // 흰색 글씨
        th.style.backgroundColor = "black"; // 검은색 바탕
        th.style.textAlign = "center"; // 헤더 셀 중앙 정렬
        tableHeader.appendChild(th);
    });
    table.appendChild(tableHeader);

    for (let i = 0; i < msgObj.result.length; i++) {
        const row = document.createElement('tr');
        row.setAttribute("id", msgObj.result[i].indexNum)

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

        const cell6 = document.createElement('td');
        cell6.textContent = msgObj.result[i].category;
        cell6.style.textAlign = "center";
        row.appendChild(cell6);

        table.appendChild(row);
    }

    // 테이블 경계선 스타일 적용
    const tableRows = table.querySelectorAll('tr');
    tableRows.forEach(row => {
        row.style.border = "1px solid black";
    });

    // 테이블을 부모 요소에 추가
    parentElement.appendChild(table);

    // Pagination buttons
    const paginationDiv = document.createElement('div');
    paginationDiv.className = 'pagination';
    paginationDiv.style.textAlign = 'center'; // 페이지네이션 버튼 가운데 정렬
    paginationDiv.style.marginTop = "20px"; // 위쪽에 여백 추가

    // 페이지네이션 버튼을 테이블 아래쪽에 추가
    parentElement.appendChild(paginationDiv);

    console.log(msgObj.pageCount);
    
    // 페이지네이션 버튼 생성 및 이벤트 처리
    for (let i = 1; i <= msgObj.pageCount; i++) {
        const pageButton = document.createElement('button');
        pageButton.textContent = i;
        pageButton.addEventListener('click', ((pageNumber) => {
            return () => {
                // 페이지네이션 클릭 이벤트 처리
                sendNextPageList(`${pageNumber}`);

                console.log(`Clicked page ${pageNumber}`);
            };
        })(i));
        paginationDiv.appendChild(pageButton);
    }

    // 각 행을 클릭할 때 이벤트 처리
    const trbtn = document.querySelectorAll('tr');
    console.log(trbtn)
    trbtn.forEach(element => {
        element.addEventListener('click', () => { sendView(element) })
    })
};
