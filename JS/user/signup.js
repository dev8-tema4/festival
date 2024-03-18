const sendSignup = function(){
  const userName = document.querySelector('#name').value;
  const email = document.querySelector('#email').value;
  const password = document.querySelector('#pwd1').value;
  const password2 = document.querySelector('#pwd2').value;
  const addr = document.querySelector('#addr').value;
  const phone = document.querySelector('#phone').value;

  const packet = {
    cmd: 'signup',
    name: userName,
    email: email,
    password: password,
    address: addr,
    phone: phone
  };

  const jsonStr = JSON.stringify(packet);     // js객체 -> json문자열
  sendMessage(jsonStr);
  console.log('a')
}

const signupSuccess = function(message) {
  // json문자열 -> js 객체로 변환
  const msgObj = JSON.parse(message);

  switch (msgObj.cmd) {
    case 'signup':
      if (msgObj.result === 'ok') {
        alert(`회원가입에 성공하셨습니다.`);
      }
  }
}

document.addEventListener('DOMContentLoaded', () => {
  const signupBtn = document.querySelector('#signupBtn');
  
  signupBtn.addEventListener('click', () => {
    sendSignup();
  });
});