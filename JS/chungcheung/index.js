document.addEventListener('DOMContentLoaded', () => {
    const festival = document.querySelector('.festival1');
    const header = document.querySelector('.header');
  
    window.addEventListener('scroll', () => {
      console.log(festival.getBoundingClientRect().top)
      if(festival.getBoundingClientRect().top <= 0){
        header.style.backgroundColor = 'white';
        header.style.color = 'black';
      }else{
        header.style.backgroundColor = '';
        header.style.color = 'white';
      }
    });
  });