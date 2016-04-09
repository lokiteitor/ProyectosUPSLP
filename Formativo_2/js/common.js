function getAlmancen () {
    var id = '' ;
    for (var i = 0; i < 8; i++) {
        
        if (i < 2 ) {
            id += String.fromCharCode(65 + parseInt(Math.random() * 25 ));
        }else if (i == 3) {
            id += '-';
        }
        else{
            id += parseInt(Math.random() * 10);
        }
    }; 

    return id;
}