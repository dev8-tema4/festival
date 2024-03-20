const loginCheck = function(){
  const loginBtn = document.querySelector('.drop1');
  const signupBtn = document.querySelector('.drop2');
  const logoutBtn = document.querySelector('.logout');
  const mypageBtn = document.querySelector('.mypage');

  if(sessionStorage.getItem('memberId')){
    loginBtn.style.display = 'none';
    signupBtn.style.display = 'none';
    logoutBtn.style.display = 'block';
    mypageBtn.style.display = 'block';
    logout();
  }
}

const logout = function(){
  const logoutBtn = document.querySelector('.logout');
  logoutBtn.addEventListener('click', () => {
    sessionStorage.clear();
    location.href = '/HTML/';
  });
}

document.addEventListener('DOMContentLoaded', () => {
  loginCheck();
})
