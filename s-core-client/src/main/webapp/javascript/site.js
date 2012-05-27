$(document).ready(function() {
    $('#edit_domain_link').click(function() {
        $('#domain_info').css("display", "none");
        $('#domainformdiv').css("display", "inline");
    });
    $('#save_domain_button').click(function() {
        $('#domain_info').css("display", "inline");
        $('#domainformdiv').css("display", "none");
    });
    $('#edit_hosting_link').click(function() {
        $('#hosting_info').css("display", "none");
        $('#hostingformdiv').css("display", "inline");
    });
    $('#save_hosting_button').click(function() {
        $('#hosting_info').css("display", "inline");
        $('#hostingformdiv').css("display", "none");        
    });

    $('#edit_engine_link').click(function() {
        $('#engine_info').css("display", "none");
        $('#engineformdiv').css("display", "inline");
    });
    $('#save_engine_button').click(function() {
        $('#engine_info').css("display", "inline");
        $('#engineformdiv').css("display", "none");        
    });

});

