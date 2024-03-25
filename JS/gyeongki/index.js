document.addEventListener('DOMContentLoaded', () => {
  const festival = document.querySelector('.festival1');
  const header = document.querySelector('.header');
  const a = header.querySelectorAll('a');

  window.addEventListener('scroll', () => {
    if(festival.getBoundingClientRect().top <= 0){
      header.style.backgroundColor = 'white';
      a.forEach((e) => {
        e.style.color = 'black';
      })
      header.style.color = 'black';
    }else{
      header.style.backgroundColor = '';
      a.forEach((e) => {
        e.style.color = 'white';
      })
    }
  });
});