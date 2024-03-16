document.addEventListener('DOMContentLoaded', () => {
  const festival = document.querySelector('.fastival');
  const titleList = document.querySelectorAll('.title');
  const contentList = document.querySelectorAll('.content');
  const videoList = document.querySelectorAll('.video');
  contentList.forEach((element) => {
    let plus = element.nextElementSibling;
    
    plus.addEventListener('click', () => {
      plus.remove();
      element.style.overflow = 'visible';
    });
  });
});