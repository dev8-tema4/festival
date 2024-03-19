//게시글 작성 및 서버로 전송
const writePost = function(){
    //입력된 제목, 본문, 카테고리 가져오기
    const title = document.getElementById("titleInput").value;
    const content = document.getElementById("contentInput").value;
    const category = document.getElementById("categorySelect").value;

    //게시글 데이터를 JSON 형식으로 만들기
    const postData ={
        cmd: 'write', 
        title: title,
        content: content,
        category: category
    };

    //JSON 문자열로 변환
    const jsonStr = JSON.stringify(postData);

    //콘솔에 전송할 데이터 출력
    console.log("sending data to server:", postData)

    //서버로 전송
    sendMessage(jsonStr);
};
