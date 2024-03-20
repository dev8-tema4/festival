//장바구니에 추가하기
//me
const send = function () {
  const itemId = document.querySelectorAll(".price");

  itemId.forEach((item) => {
    item.addEventListener("click", (e) => {
      const packet = {
        cmd: "addCart",
        memberId: sessionStorage.getItem("memberId"),
        itemId: item.getAttribute("id"),
        count: 1,
        price: parseInt(item.querySelector("p").textContent)
      };
      const jsonStr = JSON.stringify(packet); // js객체 -> json문자열
      console.log(jsonStr);
      sendMessage(jsonStr);
    });
  });
};

  document.addEventListener("DOMContentLoaded", function () {
    send();
  })