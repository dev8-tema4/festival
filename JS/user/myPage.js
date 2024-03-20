//페이지가 로드될 때 실행될 함수

window.onload = function(){
    //서버로부터 사용자 정보를 가져와서 테이브에 출력하는 함수 호출

    if(sessionStorage.getItem("memberId")== null){
 // 로그인 페이지로 이동
    }eles{
        
    }
    getMyInfo();
    //비밀번호 변경 링크에 이벤트 리스너 등록
    document.getElementById("changePass").addEventListener("click", changePass);
};

//서버로부터 사용자 정보를 가져와서 테이블에 출력하는 함수
const getMyInfo = function(){
    const packet = {
        cmd: 'MyInfo'
    };

    const jsonStr = JSON.stringify(packet);     // js객체 -> json문자열
    sendMessage(jsonStr);
}


const renderMyInfo = function(userInfo){
    //테이블 셀에 사용자 정보를 출력
    document.getElementById("emailCell").textContent=userInfo.email;
    document.getElementById("nameCell").textContent=userInfo.name;
    document.getElementById("addressCell").textContent=userInfo.addr;
    document.getElementById("phoneCell").textContent=userInfo.phone;
};