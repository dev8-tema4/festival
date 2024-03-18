

const sendLogIn = function () {
  console.log('a')
  const email = document.querySelector('#emailInput').value;
  const password = document.querySelector('#pwdInput').value;

  const packet = {
    cmd: 'login',
    email: email,
    password: password
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
        sessionStorage.setItem('email', msgObj.email);
        sessionStorage.setItem('name', msgObj.name);
        alert(`${msgObj.name}님 환영합니다.`);
      }
  }
}

document.addEventListener('DOMContentLoaded', () => {
  const loginBtn = document.querySelector('#loginBtn');
  
  loginBtn.addEventListener('click', () => {
    sendLogIn();
  });
});