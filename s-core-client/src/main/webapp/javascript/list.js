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
    $("#sitestable").tablesorter(
    {
        widgets: [ 'zebra'],
        headers: {
            0: {
                sorter: false
            }
        }

    });

    function selectSite(row) {
        $("#creation").css("display", "inline");
        $('#removebutton').disabled = false;
        var sid = row.id.substring(4);
        var tds = row.getElementsByTagName("td");
        $('#siteid').val(sid);
        $('#id').val(sid);
        $('#name').val(dojo.dom.textContent(tds[0]));
        $('#URL').val(dojo.dom.textContent(tds[1]));
        $('#category').val(dojo.dom.textContent(tds[2]));
        $('#description').val(dojo.dom.textContent(tds[3]));

        $('#removeid').val(sid);
    }

    ;


    dojo.event.topic.subscribe("/save", function(data, type, request) {
        if (type == "load") {
            $("#creation").css("display", "none");
            $('#removebutton').disabled = true;
            $('#siteid').val('');
            $('#id').val('');
            $('#name').val('');
            $('#URL').val('');
            $('#category').val('');
            $('#description').val('');
            $('#removeid').val('');
        }
    });

    dojo.event.topic.subscribe("/new", function(data, type, request) {
        if (type == "load") {
            $("#creation").css("display", "inline");
            $('#removebutton').disabled = true;
            $('#siteid').val('');
            $('#id').val('');
            $('#name').val('');
            $('#URL').val('');
            $('#category').val('');
            $('#description').val('');
            $('#removeid').val('');
        }
    });


    $("#sitelist").jqGrid({ url:'list.ajax.action', datatype: "json", colNames:['ID','Name', 'URL', 'Category','Description','YAP','Button?'], colModel:[
        {
            name:'id',
            index:'id',
            width:55
        },
        {
            name:'name',
            index:'name',
            width:90
        },
        {
            name:'URL',
            index:'URL',
            width:100
        },
        {
            name:'category',
            index:'category',
            width:80,
            align:"right"
        },
        {
            name:'description',
            index:'description',
            width:80,
            align:"right"
        },
        {
            name:'yandexIndexPageCount',
            index:'yandexIndexPageCount',
            width:80,
            align:"right"
        },
        {
            name:'buttonUploaded',
            index:'buttonUploaded',
            width:150,
            sortable:false
        }
    ], rowNum:10, rowList:[10,20,30], pager: '#sitepager', sortname: 'id', viewrecords: true, sortorder: "desc", caption:"Satellites" });
    $("#sitelist").jqGrid('navGrid', '#sitepager', {edit:false,add:false,del:false});
});