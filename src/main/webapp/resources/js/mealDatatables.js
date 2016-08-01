
var ajaxUrl = 'ajax/profile/meals/';
var datatableApi;

function updateTable() {
    $.get(ajaxUrl, updateTableByData);
}

$(function() {
    datatableApi = $('datatable').DataTable({
        "ajax": {
            "url" : ajaxUrl,
            "dataSrc" : ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime",
                "render": function (date, type, row) {
                    if (type == 'display') {
                        var dateObject = new Date(date);
                        return '<span>' + dateObject.getDay() + '-' + dateObject.getMonth() + '-' + dateObject.getYear() +
                            ' ' + dateObject.getHours() + ':' + dateObject.getMinutes() + '</span>';
                    }
                    return date;
                }
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "data": "exceed"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }
        ],
        "order": [
            0,
            "desc"
        ],
        "createdRow": function (row, data, dataIndex) {
            if (!data.exceed) {
                $(row).css("color", "green");
            } else {
                $(row).css("color", "red");
            }
        },
        "initComplete": makeEditable
    })
});
