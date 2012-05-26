$(document).ready(function() {
    $("#ftpaccountstable").tablesorter(
    {
        widgets: [ 'zebra'],
        headers: {
            0: {
                sorter: false
            }
        }

    });

    $("#ids_all").click(function()
    {
        var checked_status = this.checked;
        $("input[name=ids]").each(function()
        {
            this.checked = checked_status;
        });
    });
});