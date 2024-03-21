const maps = [
  "seoul_on",
  "jeonnam_on",
  "gyungbuk_on" ,
  "gyeongnam_on",
  "gwangju_on",
  "gangwon_on",
  "deagu_on",
  "gyeongki_on",
  "chungbuk_on",
  "chungnam_on",
  "daejeon_on",
  "jeongbuk_on",
  "ulsan_on",
  "busan_on",
  "jeju_on",
  "incheon_on"
]

document.addEventListener('DOMContentLoaded', () => {
  mapEvent();
  backgroundChange();
  // AOS.init();
  window.addEventListener('resize', () => {
    backgroundSize();
  })
})

// 4초마다 background-image변경
function backgroundChange(){
  let $background = document.querySelector('.background');
  console.log($background)
  let i = 1;
  setInterval(() => {
    if(i>4){
      i = 1;
    }
    $background.style.background = `url('../image/index/mainBackground${i}.jpg') no-repeat`
    $background.style.backgroundSize = 'cover';
    $background.style.transition = '2s';
    i++;
  }, 4000);
}

// 지도 hover eventListener
function mapEvent() {
  let areas = [];
  maps.forEach((e) => {
    areas.push(document.querySelector(`#${e}`));
  });
  const bigMap = document.querySelector('#bigMap');


  areas.forEach((area) =>{
    area.addEventListener('mouseover', () => {
      if(area.id==='seoul_on'){
        bigMap.setAttribute('src', `http://www.citygas.or.kr/img/gyeongki_on.png`);
      }else{
        bigMap.setAttribute('src', `http://www.citygas.or.kr/img/${area.id}.png`);
      }
      
    })
  })

  areas.forEach((area) =>{
    area.addEventListener('mouseout', () => {
      bigMap.setAttribute('src', 'http://www.citygas.or.kr/img/situation_img.png');
    })
  })
}

function backgroundSize(){
  let width = window.innerWidth;
  let height = window.innerHeight;
  console.log(width, height)
  let $background = document.querySelector('.background');
  $background.style.backgroundSize = 'cover';
}
document.addEventListener("DOMContentLoaded", function() {
  const textContainer = document.querySelector(".main-text");
  let texts = [
    "코로나는 끝났다.\n고로,나는 떠난다!",
    "우리는 더 크게 환호할 미래를 위해서\n더 큰 꿈을 꿉니다.",
    "모두가 Won하다! 모두강원하다!\n-강원특별자치도-",
    "넉넉해진 나자신.\n 느낌여행 \n-충청도-",
    "청도의 여름 그곳에 휴식이 있다.\n-청도군청-",
    "올 여름 경북에서 나만의 추억을 만들고 싶다.\n-경북도청-",
    "그렇다! 이번 여름에도 강원도다!\n-강원도청-",
    "여름, 경북으로 퇴근할게요.\n-경북도청-",
    "모두의 여름은 해마다 찾아오지만\n 특별한 풍경이 만든 한 번의 여름이 있다.\n-울산광역시-",
    "운명은 기회가 아닌 선택의 문제다.\n 인생은 고달프지만 포기하지마라!\n 너의꿈을 이루기 위해 언제나 \n자신을 믿고 끝까지 싸워라!\n-카넬로알바레즈-"
  ];

  let textIndex = 0;
  let charIndex = 0;
  let intervalId;

  function displayText() {
      textContainer.innerHTML = ""; // 기존 문구 삭제
      let text = texts[textIndex]; // 현재 문장 가져오기
      let typedText = ""; // 타이핑 효과를 위한 변수
      intervalId = setInterval(function() {
          if (charIndex < text.length) {
              if (text.charAt(charIndex) === "\n") {
                  typedText += "<br>"; // 줄 바꿈 문자면 HTML 줄 바꿈 태그로 변환하여 추가
              } else {
                  typedText += text.charAt(charIndex); // 그 외 문자는 그대로 추가
              }
              textContainer.innerHTML = typedText; // 텍스트만 표시
              charIndex++;
          } else {
              clearInterval(intervalId);
              setTimeout(nextText, 2000); // 2초 후에 다음 문장 타이핑 효과 시작
          }
      }, 100);
  }

  function nextText() {
      textIndex = (textIndex + 1) % texts.length; // 다음 문장으로 전환
      charIndex = 0; // 문자 인덱스 초기화
      displayText(); // 새로운 문장 표시
  }

  // 초기에 첫 번째 문장 표시
  displayText();
});
// 로고 표시
setTimeout(function() {
  document.querySelector('.logo-text').innerHTML = 'G&#8734;';
}, 1500);