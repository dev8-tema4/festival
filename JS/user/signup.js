const sendCheckEmail = function(){
  const email = document.querySelector('#email').value;

  const packet = {
    cmd: 'checkemail',
    email: email
  }

  const jsonStr = JSON.stringify(packet);     // js객체 -> json문자열
  sendMessage(jsonStr);
}

const checkEmailReq = function(message){
  const checkemail = document.querySelector('#check-email');
  const msgObj = JSON.parse(message);

  switch (msgObj.cmd) {
    case 'checkemail':
      if (msgObj.result === 'ok') {
        checkemail.innerHTML = '&nbsp;&nbsp;&nbsp;사용가능한 이메일 입니다.'
      }else{
        checkemail.innerHTML = '&nbsp;&nbsp;&nbsp;이미 가입된 이메일입니다'
      }
  }
}

const sendSignup = function(){
  const userName = document.querySelector('#name').value;
  const email = document.querySelector('#email').value;
  const password = document.querySelector('#password').value;
  const password2 = document.querySelector('#password-check').value;
  const checkpassword = document.querySelector('#check-password');
  const addr = document.querySelector('#address').value;
  const phone = document.querySelector('#phone').value;

  if(password !== password2){
    checkpassword.textContent = '비밀번호가 다릅니다.';
    return;
  }

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
  const checkBtn = document.querySelector('#check-email-button');

  signupBtn.addEventListener('click', () => {
    sendSignup();
  });

  checkBtn.addEventListener('click', () => {
    sendCheckEmail();
  });
});