const SERVER_IP = '192.168.0.45';
const SERVER_PORT = 9000;
const server_address = `ws://${SERVER_IP}:${SERVER_PORT}`;  // ws://127.0.0.1:9000

const socket = new WebSocket(server_address);

socket.opopen = function (e) {
    const log_msg = '[open] 연결이 설정되었습니다.';

    displayMessage('#messages', log_msg);
}

socket.onclose = function (e) {
    let log_msg = '';
    if (e.wasClean)
        log_msg = `[close] 연결이 정상적으로 종료되었습니다. 코드=${e.code}, 원인=${e.reason}`;
    else
        log_msg = `[close] 연결이 비정상적으로 종료되었습니다.. 코드=${e.code}, 원인=${e.reason}`;
}

socket.onerror = function (error) {
    const log_msg = `[error] ${error.message}`;

    displayMessage('#messages', log_msg);
}

socket.onmessage = function (e) {
    const msgObj = JSON.parse(e.data);
    
    switch(msgObj.cmd){
        case 'login':
            loginSuccess(e.data);
            break;
        case 'allchat':
            recievePacketMessage('#messages', e.data);
            break;
        case 'checkemail':
            checkEmailReq(e.data);
            break;
        case 'signup':
            signupSuccess(e.data);
            break;
    }
}

const sendMessage = function(message){
    socket.send(message);    // 서버로 전송
}