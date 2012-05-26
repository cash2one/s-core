$(document).ready(function() {
    $("#keywordlist").tablesorter(
    {
        widgets: [ 'zebra']
    });
    $('.keywordscheckbox').shiftcheckbox();
});