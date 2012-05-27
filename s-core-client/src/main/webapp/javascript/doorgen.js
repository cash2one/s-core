$(document).ready(function() {
    $("#ids_all").click(function()
    {
        var checked_status = this.checked;
        $("input[name=ids]").each(function()
        {
            this.checked = checked_status;
        });
    });
    $('.idcheckbox').shiftcheckbox();
});