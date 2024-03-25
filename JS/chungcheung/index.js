// 이미지 슬라이드를 위한 배열
var images = [
  "https://kfescdn.visitkorea.or.kr/kfes/upload/contents/db/87dfeda1-084e-41a9-b76e-09f2d502443a_3.jpg",
  "https://search.pstatic.net/common/?src=http%3A%2F%2Fimgnews.naver.net%2Fimage%2F5232%2F2024%2F02%2F21%2F0000216857_001_20240221130801462.jpg&type=sc960_832",
  "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDAzMTNfNDgg%2FMDAxNzEwMzI4ODUxMzc0.44kUiI9ExiB8HPK6o2gWgZgkoaKbkQKsRKbALvMhCtQg.UZVHMRtKXAKQrirUNbVeVN_coVUIgmPK0ATTkiO4uV4g.PNG%2Fimage.png&type=sc960_832"
];

// 현재 이미지 인덱스
var currentIndex = 0;

// 이미지 슬라이드 함수
function slideImages() {
  // 이미지 박스 요소 가져오기
  var box1 = document.getElementById("box1");

  // 현재 인덱스에 해당하는 이미지 설정
  box1.style.backgroundImage = "url('" + images[currentIndex] + "')";

  // 다음 이미지 인덱스로 이동
  currentIndex++;

  // 이미지 인덱스가 배열 길이보다 크면 처음으로 돌아감
  if (currentIndex >= images.length) {
      currentIndex = 0;
  }

  // 3초마다 이미지 슬라이드 함수 호출
  setTimeout(slideImages, 3000);
}

// 페이지 로드 후 이미지 슬라이드 시작
window.onload = function() {
  slideImages();
};
