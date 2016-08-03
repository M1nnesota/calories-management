var ajaxUrl = 'ajax/profile/meals/';
var datatableApi;

function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + 'filter',
        data: $('#filter').serialize(),
        success: updateTableByData
    });
    return false;
}

$(function() {
    datatableApi = $('#datatable').DataTable({
        "ajax": {
            "url" : ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime",
                "render": function (data, type, row) {
                    if (type == 'display') {
                        return '<span>' + data.substring(0, 16).replace('T', ' ') + '</span>';
                    }
                    return data;
                }
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
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
            [
                0,
                "desc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            if (!data.exceed) {
                $(row).css("color", "green");
            } else {
                $(row).css("color", "red");
            }
        },
        "initComplete": function () {
            $('#filter').submit(function () {
                updateTable();
                return false;
            });
            jQuery('#startDate, #endDate').datetimepicker({
                timepicker:false,
                format:'Y-m-d'
            });
            jQuery('#startTime, #endTime').datetimepicker({
                datepicker:false,
                format:'H:i'
            });
            makeEditable();
        }
    });
});
