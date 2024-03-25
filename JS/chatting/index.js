// 메시지를 서버로 전송
// const sendChatMsg = function(){
//     const message = document.querySelector('#messageInput').value;
//     const packet = {
//         cmd: 'allchat',
//         memberId: sessionStorage.getItem('memberId'),
//         name: sessionStorage.getItem('name'),
//         msg:message
//     }
//     const jsonStr = JSON.stringify(packet);     // js객체 -> json문자열
//     sendMessage(jsonStr);
// }

const sendChatMsg = function(){
  // 입력된 메시지 가져오기
  const messageInput = document.querySelector('#messageInput');
  const message = messageInput.value;
  
  // 패킷 생성
  const packet = {
      cmd: 'allchat',                                // 명령어: 채팅 메시지 전송
      memberId: sessionStorage.getItem('memberId'),  // 사용자 ID
      name: sessionStorage.getItem('name'),          // 사용자 이름
      msg: message                                    // 채팅 메시지 내용
  };

  // JSON 문자열로 변환
  const jsonStr = JSON.stringify(packet); // JS 객체 -> JSON 문자열

  // 메시지 전송 함수 호출
  sendMessage(jsonStr); // sendMessage 함수가 정의되어 있어야 함

  // 메시지 전송 후 텍스트 창 비우고 포커스 설정
  messageInput.value = ''; // 텍스트 창 비우기
  messageInput.focus();    // 텍스트 창에 포커스 주기
}


const recievePacketMessage = function ($parentSelector, message) {
  // 이 요소 아래에 메시지 요소를 추가
  const parentElem = document.querySelector($parentSelector);

  // json문자열 -> js 객체로 변환
  const msgObj = JSON.parse(message);

  let msg = '';
  let chatClass = '';
  const currentTime = new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }); // 현재 시간 가져오기 (시간과 분만 표시)

  switch (msgObj.cmd) {
      case 'allchat': {
          if ('result' in msgObj)
              msgObj.result === 'ok' ? '' : alert('잠시후 다시 시도해주세요');
          if ('memberId' in msgObj) {
              msg = `${msgObj.name} : ${msgObj.msg}`;
              chatClass = msgObj.memberId === sessionStorage.getItem('memberId') ? 'sent' : 'received';
          }
          break;
      }
  }

  const childElem = document.createElement('div');
  childElem.innerHTML = `${msg} <div id="time">(${currentTime})</div>`;
  childElem.classList.add('chat-bubble', chatClass);
  parentElem.appendChild(childElem);
  parentElem.scrollTop = parentElem.scrollHeight;

}


// 아이콘 클릭시 채팅창 생성
const createChat = function(){
  const body = document.querySelector('body');
  
  const chatting = "<div class='chatting'>" +
  "<div class='send'>" +
  "<input type='text' id='messageInput' placeholder='메시지를 입력하세요'> &nbsp; " +
  "<button id='btnChatMsg'>전송</button>" +
  "</div>" +
  "<div id='messages'>" +
  "<!-- 메시지 출력 영역 -->" +
  "</div>" +
  "</div>";
  body.insertAdjacentHTML('afterbegin', chatting);
  const messageInput = document.querySelector('#messageInput');
  messageInput.focus();    // 텍스트 창에 포커스 주기
}

// 채팅창 나타남
const openChat = function(){
  const messageInput = document.querySelector('#messageInput');
  const chatting = document.querySelector('.chatting');
  chatting.style.display = 'flex';
  messageInput.focus();    // 텍스트 창에 포커스 주기

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
            // 텍스트 창에 포커스 주기
        visibleChat = true;
      }
    }
  });
});