document.addEventListener("DOMContentLoaded", () => {
	setTimeout(reqAllCart, 100);
	order();

});

const reqAllCart = function () {
    const packet = {
        cmd: "getAllCart",
        memberId: parseInt(sessionStorage.getItem("memberId"))
    };

    const jsonStr = JSON.stringify(packet); // js객체 -> json문자열
    sendMessage(jsonStr);
};

const resAllCart = function (message) {
  const msgObj = JSON.parse(message);
	const cartHeader = document.querySelector('.cart_header');
	let orderItemIdStr = '';
	let totalPrice = document.querySelector('.totalPrice');
	let sumPrice = document.querySelector('.sumPrice1')
	let total = 0;

	for(let i=0; i<msgObj.length; i++){
		orderItemIdStr += `${msgObj[i].orderItemId}, `;
		
		let allCart = `<tr>
		<td>
			<div class="itemInfo1">
				<div class="itemImg">
					<p>${msgObj[i].name}</p><br>
					<div class="moreInfo1">
						<em>필수</em><span>1.0Kg - 1개</span>
					</div>
				</div>
			</div>
		</td>

		<td>
			<div class="itemCount1">${msgObj[i].count}
			<span class="itemOption">옵션/수량 변경</span>
		</div>
		</td>

		<td>
			<div class="itemPrice1">${msgObj[i].price}원<span class="itemOption1">바로구매</span></div>
		</td>

		<td>
			<div class="itemDeliv1">무료<br><small>택배</small></div>
		</td>
	</tr>`
	cartHeader.insertAdjacentHTML('afterend', allCart);
	total += msgObj[i].count * msgObj[i].price;

	totalPrice.textContent = `${total}원`;
	sumPrice.textContent = `${total}원`; 
	}
	sessionStorage.setItem('orderItemId', orderItemIdStr);
};

const order = function () {
	const order = document.querySelector('#order');
	
	order.addEventListener('click', () => {
		const packet = {
            cmd: "buyCartItem",
            orderItemId: sessionStorage.getItem('orderItemId'),
        };

		const jsonStr = JSON.stringify(packet); // js객체 -> json문자열
		sendMessage(jsonStr);
	});
}

const orderSuccess = function (message) {
	// json문자열 -> js 객체로 변환
    const msgObj = JSON.parse(message);

    switch (msgObj.result) {
        case 'ok':
            alert('주문이 완료되었습니다.');
            location.reload(true);
            break;
    }
}