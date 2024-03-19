const sendBoardList = function(){
    
    const packet = {
     cmd : 'boardlist'
    };

    const jsonStr = JSON.stringify(packet);     // js객체 -> json문자열
    sendMessage(jsonStr);
    }

// 서버로부터 응답처리
const requestboard = function (message) {
    // json문자열 -> js 객체로 변환
    const msgObj = JSON.parse(message);

    switch (msgObj.cmd) {
      case 'boardlist':
        const postList = document.querySelector('#postList');
        postList.innerHTML='';
        msgObj.result.forEach(element => {
            const li = document.createElement('li');
            li.innerHTML = element.subject;
            // li.innerHTML += `<br>${element.date}`        
            postList.appendChild(li);
        });
        console.log(msgObj.result)
    }
}

document.addEventListener('DOMContentLoaded', ()=>{
    const listbtn = document.querySelector('#board-list');

    listbtn.addEventListener('click', ()=>{
        sendBoardList();
    });
});