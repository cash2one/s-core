$(document).ready(function() {
    $("#dictable").tablesorter(
    {
        widgets: [ 'zebra'],
        headers: {
            3: {
                sorter: false
            }
        }

    });
});