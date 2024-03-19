// 메시지를 서버로 전송
const sendChatMsg = function(){
    const message = document.querySelector('#messageInput').value;
    const packet = {
        cmd: 'allchat',
        memberId: sessionStorage.getItem('memberId'),
        name: sessionStorage.getItem('name'),
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
        case 'allchat':{
          if ('result' in msgObj)
            msgObj.result === 'ok' ? '' : alert('잠시후 다시 시도해주세요');
          if ('memberId' in msgObj)
            msg = `${msgObj.name} => ${msgObj.msg}`;
            break;
        }
    }

    const childElem = document.createElement('div');
    childElem.textContent = msg;
    childElem.style.padding = 4 + 'px';
    if(msgObj.memberId === sessionStorage.getItem('memberId')){
        childElem.style.textAlign = 'right';
    }
    parentElem.appendChild(childElem);
    parentElem.scrollTop = parentElem.scrollHeight;
}

// 아이콘 클릭시 채팅창 생성
const createChat = function(){
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

// 채팅창 나타남
const openChat = function(){
  const chatting = document.querySelector('.chatting');
  chatting.style.display = 'flex';
}
// 채팅창 안보이게
const closeChat = function(){
  const chatting = document.querySelector('.chatting');

  chatting.style.display = 'none';
}

document.addEventListener('DOMContentLoaded', ()=>{
  let btnChatMsg = null;
  const btnChatIcon = document.querySelector('.chat_icon');
  let chat = false;
  let visibleChat = false;

  btnChatIcon.addEventListener('click', () => {
    if(sessionStorage.getItem('memberId') === null){
      alert('로그인이 필요한 서비스입니다.');         // 아이콘 클릭시 로그인이 되어있지 않으면 return
      return;
    }
    
    if(chat === false){ // 아이콘 클릭시 채팅창이 생성되어있지 않으면 생성
      createChat();
      chat = true;
      visibleChat = true;
      btnChatMsg = document.querySelector('#btnChatMsg');
      btnChatMsg.addEventListener('click', sendChatMsg);
    }else{
      if(visibleChat === true){ // 채팅창이 이미 생성되어있으면 아이콘 클릭시 감추고 보여주기
        closeChat();
        visibleChat = false;
      }else{
        openChat();
        visibleChat = true;
      }
    }
  });
});