//게시글 작성 및 서버로 전송
const writePost = function(){
    //입력된 제목, 본문, 카테고리 가져오기
    const subject = document.getElementById("titleInput").value;
    const content = document.getElementById("contentInput").value;
    const category = document.getElementById("categorySelect").value;
    const memberId = sessionStorage.getItem('memberId');
    const name = sessionStorage.getItem('name');
    console.log(memberId, name)

    //게시글 데이터를 JSON 형식으로 만들기
    const packet ={
        cmd: 'write', 
        subject: subject,
        content: content,
        category: category,
        memberId: memberId,
        name:name
    };

    //JSON 문자열로 변환
    const jsonStr = JSON.stringify(packet);

    //콘솔에 전송할 데이터 출력
    console.log("sending data to server:", packet)

    //서버로 전송
    sendMessage(jsonStr);

    // 입력 필드 초기화
    document.getElementById("titleInput").value = "";
    document.getElementById("contentInput").value = "";
    document.getElementById("categorySelect").selectedIndex = 0;
};
