document.addEventListener("DOMContentLoaded", (event) => {
  var slideIndex1 = 0;
  var slideIndex2 = 0;
  var slideIndex3 = 0;
  showSlides1();
  showSlides2();
  showSlides3();

  function showSlides1() {
    var i;
    var slides = document.getElementsByClassName("mySlides1");

    for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";
    }
    slideIndex1++;
    if (slideIndex1 > slides.length) {
      slideIndex1 = 1;
    }
    slides[slideIndex1 - 1].style.display = "block";

    setTimeout(showSlides1, 2000); // 2초마다 이미지가 체인지됩니다
  }

  function showSlides2() {
    var i;
    var slides = document.getElementsByClassName("mySlides2");

    for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";
    }
    slideIndex2++;
    if (slideIndex2 > slides.length) {
      slideIndex2 = 1;
    }
    slides[slideIndex2 - 1].style.display = "block";

    setTimeout(showSlides2, 2000); // 2초마다 이미지가 체인지됩니다
  }

  function showSlides3() {
    var i;
    var slides = document.getElementsByClassName("mySlides3");

    for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";
    }
    slideIndex3++;
    if (slideIndex3 > slides.length) {
      slideIndex3 = 1;
    }
    slides[slideIndex3 - 1].style.display = "block";

    setTimeout(showSlides3, 2000); // 2초마다 이미지가 체인지됩니다
  }
  AOS.init({
    offset: 200, // 스크롤 전에 애니메이션이 시작되도록 하는 오프셋 설정 (선택 사항)
    duration: 1000, // 애니메이션의 지속 시간 설정 (선택 사항)
    easing: 'ease-in-out', // 애니메이션의 이징 설정 (선택 사항)
  });
});
