/**
 * Created by USER on 6/25/2021.
 *//*




tasklistdetails= (function () {
    var form = $('#approvetask');

    function _baseURL() {
        return 'tasklistdetails/';
    }

    function getApplicationDetail() {
        var applicationNo = $('#application_Number').val();
        var url = _baseURL() + 'getApplicationDetail';
        $.ajax({
            url: url
            , type: 'GET',
            data: form.serialize(),
            enctype: 'multipart/form-data',
             success: function (res) {
                var data= res.ApplicationDTO;
                if (res.responseStatus == 1) {
                    populate(data);
                    $('.field-readonly').prop('disabled', true);
                }
            }
        });
    }

    return{
        getApplicationDetail:getApplicationDetail()
    }
});

$(document).ready(
    function () {
        tasklistdetails.getApplicationDetail();
    });
*/
