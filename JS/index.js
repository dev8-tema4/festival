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
  AOS.init();
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