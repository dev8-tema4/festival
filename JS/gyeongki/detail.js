// 이미지 슬라이드를 위한 배열
var images = [
  "https://kfescdn.visitkorea.or.kr/kfes/upload/contents/db/344555a0-5efb-4dc9-82e5-aae0d4e899f7_3.jpg",
  "https://kfescdn.visitkorea.or.kr/kfes/upload/contents/db/c9d13813-7b3c-49d0-be55-f934f1e0abaf_3.png",
  "https://kfescdn.visitkorea.or.kr/kfes/upload/contents/db/fac40458-d12f-4963-8036-38d07a5155f7_3.jpg"
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
