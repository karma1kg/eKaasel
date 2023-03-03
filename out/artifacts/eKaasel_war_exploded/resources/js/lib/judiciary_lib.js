
function isEmail(email) {
    var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    return regex.test(email);
}
function successMsg(msg) {
    alertify.successAlert(msg).set(
        {
            transition: 'zoom',

            onok: function () {
                $("body").removeClass("ajs-no-overflow");
            }
        }
    );
}

alertify.dialog(
    'successAlert', function factory() {
        return {
            build: function () {
                var successHeader = '<span class="fa fa-check-square-o successMsg gi-2x" ' +
                    'style="vertical-align:middle;">' +
                    '</span> Application Success';
                this.setHeader(successHeader);
            }
        };
    }, true, 'alert'
);

function errorMsg(msg, element) {
    alertify.errorAlert(msg).set(
        {
            transition: 'zoom',
            onok: function () {
                $("body").removeClass("ajs-no-overflow");
            }
        }
    );
}

//region *** Validation Message Alert Configuration ***
alertify.dialog(
    'errorAlert', function factory() {
        return {
            build: function () {
                var errorHeader = '<span class="ace-icon fa fa-exclamation-triangle red bigger-130 errorMsg gi-2x" ' +
                    'style="vertical-align:moddle;">' +
                    '</span> Application Error';
                this.setHeader(errorHeader);
            }
        };
    }, true, 'alert'
);

function warningMsg(msg, element) {
    alertify.warningAlert(msg).set(
        {
            transition: 'zoom',
            onok: function () {
                $("body").removeClass("ajs-no-overflow");
            }
        }
    );
}
alertify.dialog(
    'warningAlert', function factory() {
        return {
            build: function () {
                var successHeader = '<span class="fa fa-warning warningMsg gi-2x" ' +
                    'style="vertical-align:middle;">' +
                    '</span> Application Warning';
                this.setHeader(successHeader);
            }
        };
    }, true, 'alert'
);

function confirmMsg(msg, callback) {
    alertify.confirmAlert(msg, callback).set('labels', {ok: 'Yes', cancel: 'No'}).set('defaultFocus', 'cancel');
}
alertify.dialog(
    'confirmAlert', function factory() {
        return {
            build: function () {
                var confirmHeader = '<span class="fa fa-question-circle errorMsg gi-2x" ' +
                    'style="vertical-align:middle;">' +
                    '</span> Application Confirmation';
                this.setHeader(confirmHeader);
            }
        };
    }, true, 'confirm'
);
function formIndexing(tableBody, row, serialNo, iterator) {
    if (!iterator)
        iterator = 0;

    for (var i = 0; i < row.length; i++) {
        tableBody.children().eq(i).children().children().each(
            function () {
                if (this.name) {
                    this.name = this.name.replace(
                        /\[(\d+)\]/, function (str, p) {
                            return '[' + (i + iterator) + ']';
                        });
                }

                if ($(this).hasClass(serialNo)) {
                    $(this).val(i + 1);
                }
            }
        );
    }
}

function convertNumberToWord(amount) {
    var th = ['', 'Thousand', 'Million', 'Billion', 'Trillion'];
    var dg = ['Zero', 'One', 'Two', 'Three', 'Four', 'Five', 'Six', 'Seven', 'Eight', 'Nine'];
    var tn = ['Ten', 'Eleven', 'Twelve', 'Thirteen', 'Fourteen', 'Fifteen', 'Sixteen', 'Seventeen', 'Eighteen', 'Nineteen'];
    var tw = ['Twenty', 'Thirty', 'Forty', 'Fifty', 'Sixty', 'Seventy', 'Eighty', 'Ninety'];

    amount = amount.toString();
    amount = amount.replace(/[\, ]/g, '');
    if (amount != parseFloat(amount)) return 'not a number';

    var x = amount.indexOf('.');
    if (x == -1) x = amount.length;
    if (x > 15) return 'Too Big';

    var n = amount.split('');
    var str = '';
    var sk = 0;
    for (var i = 0; i < x;
         i++) {
        if ((x - i) % 3 == 2) {
            if (n[i] == '1') {
                str += tn[Number(n[i + 1])] + ' ';
                i++;
                sk = 1;
            } else if (n[i] != '0') {
                str += tw[n[i] - 2] + ' ';
                sk = 1;
            }
        } else if (n[i] != '0') {
            str += dg[n[i]] + ' ';
            if ((x - i) % 3 === 0) str += 'Hundred ';
            sk = 1;
        }
        if ((x - i) % 3 == 1) {
            if (sk) str += th[(x - i - 1) / 3] + ' ';
            sk = 0;
        }
    }
    if (x != amount.length) {
        var y = amount.length;
        str += 'Point ';
        for (var z = x + 1; z < y; z++) str += dg[n[z]] + ' ';
    }
    return str.replace(/\s+/g, ' ');
}

function formatAsDate(date) {
    if (date)
        return $.datepicker.formatDate(globalConf.dateFormat(), new Date(date));
    return '';
}
function populate(data) {
    for (var i in data) {
        if (typeof (data[i]) === 'object') {
            //populate(data[i]);
        } else {
            $(
                "input[type='text'][name='" + i + "']," +
                " input[type='hidden'][name='" + i + "'], " +
                "input[type='checkbox'][name='" + i + "'], " +
                "select[name='" + i + "'], textarea[name='" + i + "']"
            )
                .val(data[i]);

            //$("input[type='radio'][name='" + i + "'][value='" + data[i] + "']").prop('checked', true);
            /*if ($("input[name='" + i + "']").hasClass('datepicker')) {
                $("input[name='" + i + "']").val($.datepicker.formatDate(globalConf.dateFormat(),(data[i])));
            }*/

        }
    }

    $('form').find('input[type="checkbox"]').each(
        function () {
            if ($(this).siblings('input[type="hidden"]').val() == "true" ||
                $(this).siblings('input[type="hidden"]').val() == 1) {
                $(this).prop('checked', true);
            } else {
                $(this).prop('checked', false);
            }
        }
    );
}

function allowKeys(e) {
    //Allow: backspace, delete, tab, escape, enter
    if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110]) !== -1 ||
            //Allow: Ctr+A
        (e.keyCode == 65 && e.ctrlKey === true) ||
            //Allow: home, end, left, right, down, up
        (e.keyCode >= 35 && e.keyCode <= 40)) {
        return true;
    }
    return false;
}

function parseEntryAsDate(id) {
    id = '#' + id;
    var val = $(id).val();
    val = val.trim();
    if (val) {
        var date = null;
        if (val.length > 8) {
            date = $.datepicker.parseDate(globalConf.dateFormat(), val);
            if (date.getTime()) {
                val = $.datepicker.formatDate(globalConf.dateFormat(), new Date(date));
                $(id).val(val);
                return true;
            }
        }
        date = parseAsDate(val);
        if (!date) {
            $(id).val('');
            return;
        }
        $(id).datepicker("setDate", date);
        val = $.datepicker.formatDate(globalConf.dateFormat(), date);
        $(id).val(val);
        return true;
    }
}

function parseAsDate(val) {
    if (val) {
        var dateRegex = /((3[01])|([012]\d)|[1-9])((1[012])|(0?[1-9]))?((19|20)?\d\d?)?/;
        var dateMatch = val.match(dateRegex);
        if (!dateMatch || !dateMatch[1]) {
            return;
        }
        var day = dateMatch[1];
        var date = new Date();
        //if (currentUser.systemOpenDate) {
        //    date = new Date(currentUser.systemOpenDate);
        //}
        var month = date.getMonth();
        var year = date.getFullYear();
        if (dateMatch[4]) {
            month = dateMatch[4];
        }
        if (dateMatch[7]) {
            year = dateMatch[7];
            year = parseInt(year);
            if (dateMatch[7].length < 3) {
                if (year > 70)
                    year = 1900 + year;
                else
                    year = 2000 + year;
            }
        }
        date = new Date(year, month - 1, day);
        return date;

    }
}
//region *** bcseaGlobal Object ***
bcseaGlobal = (function () {
    "use strict";

    function baseUrl() {
        //TODO for production environment
       //return window.location.protocol + '//' + window.location.host + '/judiciary/';
       //return '/';
    }

    function ajax(url, type, data, callback) {
        $.ajax({
            url: url,
            type: type,
            data: data,
            success: callback
        });
    }


    return {
        baseUrl: baseUrl,
        ajax: ajax,
        formIndexing: formIndexing
    };
})();
//endregion *** bcseaGlobal Object ***

//*********************************************************************************************************

//region *** Document Ready Method ***
$(document).ready(
    function () {
        //Local variable for show errors on pop instead of tooltip
        var submitted = false;

        //region *** Restriction Event ***
        $('body').on('keypress', '.alphanumeric', function (e) {
                if (allowKeys(e)) {
                    return true;
                }
                var regex = new RegExp("^[a-zA-Z0-9]+$");
                var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
                if (regex.test(str)) {
                    return true;
                }

                e.preventDefault();
                return false;
            }
        );

        $('body').on('keypress', '.phone', function (e) {
                var evt = (e) ? e : window.event;
                var charCode = (evt.which) ? evt.which : evt.keyCode;
                if (charCode != 43 && charCode > 31 && (charCode < 48 || charCode > 57)) {
                    return false;
                }
                return true;
            }
        );

        $('body').on('keydown', '.numeric', function (e) {
                if (allowKeys(e)) {
                    return;
                }

                // Ensure that it is number and stop the key press
                if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
                    e.preventDefault();
                }
            }
        );

        function isAmount(event, element) {
            var charCode = (event.which) ? event.which : event.keyCode;
            if (
                (charCode != 46 || $(element).val().indexOf('.') != -1) &&
                (charCode != 9) &&
                (charCode < 48 || charCode > 57) &&
                (charCode != 8))
                return false;
            else
                return true;
        }

        $('body').on('keypress', '.amount', function (e) {
                return isAmount(e, this);
            }
        );


        $('body').on('keydown', '.percentage', function (e) {
                if (allowKeys(e)) {
                    return;
                }

                if ((e.which >= 96 && e.which <= 105) || (e.which >= 48 && e.which <= 57) || e.which === 190 ||
                    e.which === 110) {

                } else {
                    e.preventDefault();
                }
            }
        );

        $('body').on('blur', '.percentage', function (e) {
                var $this = $(this);
                if ($this.val()) {
                    var value = $this.val();

                    var regex = new RegExp("^[0-9]{1,3}(\\.([0-9]{1,2})?)?$");
                    if (!regex.test(value)) {
                        errorMsg('Incorrect Format. Format is ###.##');
                        $this.val('');
                        return;
                    }
                }
                if (value > 100) {
                    errorMsg('Please insert interest rate between zero(0) and hundred(100)');
                    $this.val('');
                    return;
                }
            }
        );
        //endregion *** Restriction Event ***
        $('body').on('keydown', '.numeric', function (e) {
                if (allowKeys(e)) {
                    return;
                }
                // Ensure that it is number and stop the key press
                if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
                    e.preventDefault();
                }
            }
        );
        //Date picker
        var datePickerOptions = {
            dateFormat: globalConf.dateFormat(),
            changeMonth: true,
            changeYear: true,
            yearRange: 'c-65:c+100',
            beforeShow: function (input, inst) {
                if ($(input).prop("readonly")) {
                    return false;
                }
            }
        };

        $(".datepicker").datepicker(datePickerOptions);
        $('body').on('focus', '.datepicker', function () {
            if ($(this).hasClass('dynamic')) {
                $(this).datepicker(datePickerOptions);
            }
        });

        $('body').on('keydown', 'input ,a , select', function (e) {
            var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
            if (key == 13 || key == 9 & e.target.type != 'submit' & $(e.target).hasClass('datepicker')) {
                var id = $(e.target).attr('id');
                parseEntryAsDate(id);
            }
            if (key == 13 & e.target.type != 'submit') {
                e.preventDefault();

                if (e.target.tagName.toLocaleLowerCase() === 'a' || e.target.type === 'button') {
                    $(this).click();
                }

                var manualNextIndex = $(this).attr("data-nextIndex");
                if (manualNextIndex) {
                    var $manualNext = $('[tabindex=' + manualNextIndex + ']');
                    $manualNext.focus();
                    return false;
                }

                var curIndex = this.tabIndex;
                var i = +curIndex + 1;
                var $next = null;
                var allNext = $('[tabindex=' + i + ']:not(body):not([readonly]):not(:disabled)');
                if (allNext.length) {
                    $next = allNext[0];
                }
                if (!$next) {
                    var nextPossibleTabIndex = 10000;
                    allNext = $('[tabindex]:not(body):not([readonly]):not(:disabled)').filter(function (ix, el) {
                        var tb = el.tabIndex;
                        if (+tb > +curIndex) {
                            nextPossibleTabIndex = nextPossibleTabIndex > +tb ? +tb : nextPossibleTabIndex;
                            return true;
                        } else {
                            return false;
                        }
                    });
                    if (allNext.length) {
                        $next = $('[tabindex=' + nextPossibleTabIndex + ']');
                    }
                }

                if (!$next) {
                    $next = $('[tabindex]:not(body):not([readonly]):not(:disabled)')[0];
                }
                if ($next.attr('type') == 'reset') {
                    $('input[type="submit"]').focus();
                } else {
                    $($next).focus();
                }
            }
            //else if ((!e.shiftKey && e.keyCode >= 48 && e.keyCode <= 57) || (e.keyCode >= 96 && e.keyCode <= 105)) {
            //    var val = $(this).val();
            //    val = val.trim();
            //    if (val) {
            //        if (val.length > 7) {
            //            e.preventDefault();
            //            val = val.substr(0, 8);
            //            $(this).val(val);
            //        }
            //    }
            //    $(this).datepicker('hide');
            //} else if ($.inArray(e.keyCode, [46, 8, 9, 27, 13]) !== -1 ||
            //    (e.keyCode == 65 && e.ctrlKey === true) || (e.keyCode >= 35 && e.keyCode <= 40)) {
            //    return true;
            //} else {
            //    e.preventDefault();
            //}
        });
    }
);
//endregion *** Document Ready Method ***