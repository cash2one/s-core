$(document).ready(function() {
    $('#nav a')
            .css({backgroundPosition: "-20px 35px"})
            .mouseover(function() {
        $(this).stop().animate({backgroundPosition:"(-40px 124px)"}, {duration:500});
    })
            .mouseout(function() {
        $(this).stop().animate({backgroundPosition:"(40px 35px)"}, {duration:200, complete:function() {
            $(this).css({backgroundPosition: "-20px 35px"});
        }});
    });
});
