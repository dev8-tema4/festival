document.addEventListener("DOMContentLoaded", () => {
	setTimeout(reqAllCart, 100);
});

const reqAllCart = function () {
    const packet = {
        cmd: "getAllCart",
        memberId: parseInt(sessionStorage.getItem("memberId"))
    };

		console.log(packet)

    const jsonStr = JSON.stringify(packet); // js객체 -> json문자열
    sendMessage(jsonStr);
};

const resAllCart = function (message) {
    const msgObj = JSON.parse(message);
	const cartHeader = document.querySelector('.cart_header');
	// msgObj.forEach(element => {
	// 	let allCart = `<tr>
    //                     <td>
    //                         <div class="itemInfo1">
    //                             <div class="itemImg">
    //                                 <p>${element.name}</p><br>
    //                                 <div class="moreInfo1">
    //                                     <em>필수</em><span>1.0Kg - 1개</span>
    //                                 </div>
    //                             </div>
    //                         </div>
    //                     </td>

    //                     <td>
    //                         <div class="itemCount1">${element.count}
    //                         <span class="itemOption"><a href="">옵션/수량 변경</a></span>
    //                     </div>
    //                     </td>

    //                     <td>
    //                         <div class="itemPrice1">${element.price}원<span class="itemOption1">바로구매</span></div>
    //                     </td>

    //                     <td>
    //                         <div class="itemDeliv1">4,000원<br><small>택배</small></div>
    //                     </td>
    //                 </tr>`
	// 	cartHeader.insertAdjacentHTML('afterend', allCart);
	// });
	
	console.log(msgObj.getAllCart);
};
