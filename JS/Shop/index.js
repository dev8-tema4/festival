//장바구니에 추가하기
//me
const send = function () {
  const itemId = document.querySelectorAll(".price");

  itemId.forEach((item) => {
    item.addEventListener("click", (e) => {
      if(!sessionStorage.getItem('memberId')){
        alert('로그인이 필요합니다.');
        return;
      }
      const packet = {
        cmd: "addCart",
        memberId: sessionStorage.getItem("memberId"),
        itemId: item.getAttribute("id"),
        count: 1,
        price: parseInt(item.querySelector("p").textContent.replace('원', '').replace(',', ''))
      };
      const jsonStr = JSON.stringify(packet); // js객체 -> json문자열
      sendMessage(jsonStr);
    });
  });
};



document.addEventListener("DOMContentLoaded", function () {
  send();
})