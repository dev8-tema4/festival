const sendBoardList = function(){
    
    const packet = {
     cmd : 'boardlist'
    };

    const jsonStr = JSON.stringify(packet);     // js객체 -> json문자열
    sendMessage(jsonStr);
    }

// 서버로부터 응답처리
const requestboard = function (message) {
    // json문자열 -> js 객체로 변환
    const msgObj = JSON.parse(message);
  
    switch (msgObj.cmd) {
      case 'boardlist':
        console.log(msgObj.result)
    }
}

document.addEventListener('DOMContentLoaded', ()=>{
    const listbtn = document.querySelector('#board-list');

    listbtn.addEventListener('click', ()=>{
        sendBoardList();
    });
})

document.addEventListener("DOMContentLoaded", function () {
    // 카테고리 버튼 요소들을 선택합니다.
    const categoryButtons = document.querySelectorAll('.category-button');

    // 각 버튼에 클릭 이벤트 리스너를 추가합니다.
    categoryButtons.forEach(function (button) {
        button.addEventListener('click', function () {
            // 현재 활성화된 버튼인지 확인합니다.
            const isActive = button
                .classList
                .contains('active');

            // 모든 버튼의 활성화 상태를 초기화합니다.
            categoryButtons.forEach(function (btn) {
                btn
                    .classList
                    .remove('active');
            });

            if(!isActive){

            // 클릭한 버튼을 활성화합니다.
            button
                .classList
                .add('active');
            }

            const category = button.getAttribute('data-category')

            // 해당 카테고리의 게시글을 보여줍니다.
            if (!isActive) {
                showPostsByCategory(category);
            } else {
                showAllPosts();
            }
        });
    });

    const searchOptionSelect = document.getElementById('searchOptionSelect');

    // 검색 옵션을 선택했을 때의 이벤트 처리를 변경합니다.
    if (searchOptionSelect) { // 'searchOptionSelect'이 있는지 확인합니다.
        searchOptionSelect.addEventListener('change', function () {
            const selectedOption = searchOptionSelect.value;
            const selectedText = searchOptionSelect
                .options[searchOptionSelect.selectedIndex]
                .textContent;
            const searchOptionToggle = document.getElementById('searchOptionToggle');
            if (searchOptionToggle) { // 'searchOptionToggle'이 있는지 확인합니다.
                searchOptionToggle.textContent = selectedText;

                // 선택한 옵션에 따라 검색 타입을 설정합니다.
                setSearchType(selectedOption);
            }
        });
    }
});

function showPostsByCategory(category) {
    // 모든 게시글 요소들을 선택합니다.
    const postItems = document.querySelectorAll('.post-item');

    // 모든 게시글을 숨깁니다.
    postItems.forEach(function (post) {
        post.style.display = 'none';
    });

    // 해당 카테고리의 게시글만 보여줍니다.
    const categoryPosts = document.querySelectorAll(
        `.post-item[data-category="${category}"]`
    );
    categoryPosts.forEach(function (post) {
        post.style.display = 'block';
    });
}

function showAllPosts() {
    // 모든 게시글 요소들을 선택하여 보여줍니다.
    const postItems = document.querySelectorAll('.post-item');
    postItems.forEach(function (post) {
        post.style.display = 'block';
    });
}

// 모달 열기 함수
function openModal(){
    document.getElementById('myModal').style.display="block";
}

// 모달 닫기 함수
function closeModal(){
    document.getElementById('myModal').style.display="none";
}

// 모달 닫기 버튼 클릭시 이벤트 핸들러
// document.getElementsByClassName('close')[0].addEventListener('click', closeModal);

// 모달 배경 클릭 이벤트 핸들러
window.onclick = function(event){
    if(event.target == document.getElementById('myModal')){
        closeModal();
    }
}