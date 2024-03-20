    var mapOptions = {
        center : new naver.maps.LatLng(34.804655,  126.416186),
        zoom: 12
    };

    var map = new naver.maps.Map('map', mapOptions);

    var marker = new naver.maps.Marker({
        position: new naver.maps.LatLng(34.804655,  126.416186),
        map: map
    });

    var infoWindow = new naver.maps.InfoWindow({
        content: '<h2>유달산봄축제</h2><p>전남 목포시 유달로 122</p>'
    });

    var isOpened=false;
    naver.maps.Event.addListener(marker,'click',function(){
        if(isOpened){
            infoWindow.close();
            isOpened = false;
        }else{
        infoWindow.open(map, marker);
        isOpened=true;
        }
    });
    