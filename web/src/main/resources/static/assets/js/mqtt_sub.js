function show() {
    $.get("/home/watering/wetGroundSensor/value", function (data) {
        $("#value").text("Wet: " + data + "%");
    })
}

$(document).ready(function(){
    show();
    setInterval('show()',10000);
});