// 메시지를 서버로 전송
const sendChatMsg = function(){
    const message = document.querySelector('#messageInput').value;
    const packet = {
        cmd: 'allchat',
        id:userId,
        msg:message
    }
    const jsonStr = JSON.stringify(packet);     // js객체 -> json문자열
    sendMessage(jsonStr);
}

// 통신 패킷 출력
const recievePacketMessage = function ($parentSelector, message) {
    // 이 요소 아래에 메시지 요소를 추가
    const parentElem = document.querySelector($parentSelector);

    // json문자열 -> js 객체로 변환
    const msgObj = JSON.parse(message);

    let msg = '';
    switch (msgObj.cmd) {
        case 'allchat':
            if ('result' in msgObj)
                msgObj.result === 'ok' ? '' : alert('잠시후 다시 시도해주세요');
        if ('id' in msgObj)
                msg = `${msgObj.id} => ${msgObj.msg}`;
            break;
    }

    const childElem = document.createElement('div');
    childElem.textContent = msg;
    if(msgObj.id === sessionStorage.getItem('id')){
        childElem.style.textAlign = 'right';
    }
    parentElem.appendChild(childElem);
}

// 아이콘 클릭시 채팅창 생성
const openChat = function(){
  const body = document.querySelector('body');

  const chatting = "<div class='chatting'>" +
  "<div class='send'>" +
  "<input type='text' id='messageInput' placeholder='메시지를 입력하세요'>" +
  "<button id='btnChatMsg'>전송</button>" +
  "</div>" +
  "<div id='messages'>" +
  "<!-- 메시지 출력 영역 -->" +
  "</div>" +
  "</div>";

  body.insertAdjacentHTML('afterbegin', chatting);
}

// 채팅창 삭제
const closeChat = function(){
  const chatting = document.querySelector('.chatting');

  chatting.remove();
}

document.addEventListener('DOMContentLoaded', ()=>{
  let btnChatMsg = null;
  const btnChatIcon = document.querySelector('.chatIcon');
  let setIcon = false;

  btnChatIcon.addEventListener('click', () => {
    if(sessionStorage.getItem('id') === null){
      alert('로그인이 필요한 서비스입니다.');         // 로그인이 되어있지 않으면 채팅창 이용불가
      return;
    }

    if(setIcon === false){
      openChat();
      setIcon = true;
      btnChatMsg = document.querySelector('.chatting');
      btnChatMsg.addEventListener('click', sendChatMsg);
    } else {
      closeChat();
      setIcon = false;
    }
  });
});