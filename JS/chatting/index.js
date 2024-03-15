const sendChatMsg = function(){
    const message = document.querySelector('#messageInput').value;
    const packet = {
        cmd: 'allchat',
        id:userId,
        msg:message
    }
    const jsonStr = JSON.stringify(packet);     // js객체 -> json문자열
    sendMessage(jsonStr);
}

// 통신 패킷 출력
const recievePacketMessage = function ($parentSelector, message) {
    // 이 요소 아래에 메시지 요소를 추가
    const parentElem = document.querySelector($parentSelector);

    // json문자열 -> js 객체로 변환
    const msgObj = JSON.parse(message);

    let msg = '';
    switch (msgObj.cmd) {
        case 'allchat':
            if ('result' in msgObj)
                msgObj.result === 'ok' ? '' : alert('잠시후 다시 시도해주세요');
        if ('id' in msgObj)
                msg = `${msgObj.id} => ${msgObj.msg}`;
            break;
    }

    const childElem = document.createElement('div');
    childElem.textContent = msg;
    if(msgObj.id === sessionStorage.getItem('id')){
        childElem.style.textAlign = 'right';
    }
    parentElem.appendChild(childElem);
}