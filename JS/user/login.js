let userId = '';
const sendLogIn = function () {
  const id = document.querySelector('#idInput').value;
  const pass = document.querySelector('#passInput').value;
  userId = id;      // 나중에 msg전송 때 사용하기 위해
  const packet = {
    cmd: 'login',
    id: id,
    pass: pass
  };
  const jsonStr = JSON.stringify(packet);     // js객체 -> json문자열
  sendMessage(jsonStr);
}

// 서버로부터 응답처리
const loginSuccess = function (message) {
  // json문자열 -> js 객체로 변환
  const msgObj = JSON.parse(message);

  switch (msgObj.cmd) {
    case 'login':
      if (msgObj.result === 'ok') {
        sessionStorage.setItem('id', msgObj.id);
        alert(`${msgObj.id}님 환영합니다.`);
      }
  }
}