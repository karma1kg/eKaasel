<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 6/27/2021
  Time: 1:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html >
<head>
    <meta name="decorator" content="/layout/ministry-layout.jsp">
    <title>eKaaSel</title>
</head>

<body>


<div class="container">
    <div class="row">
        <div class="col-md-12 col-lg-12">
            <h4 class="page-head-line">eKaaSel
                <small>
                    <i class="ace-icon fa fa-angle-double-right"></i>
                    Check Grievance Status
                </small>
            </h4>
        </div>
    </div>

    <div class="card card-body">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="form-group" id="checkAppId">
                        <label class="col-lg-3 control-label"><STRONG>Your Grievance Reference No:</STRONG></label>
                        <div class="col-lg-4">
                            <input type="text" name="applicationNo"  id="applicationNo" class="form-control" >
                            <span id="errormessage" class="text-danger"></span>
                        </div>
                        <div class="col-lg-4">
                            <button type="button" class="btn btn-success" id="checkStatusBtn" onclick="checkStatus()"><i class="glyphicon glyphicon-eye-open"></i> Check Status </button>
                            <span class="text-danger" id="forward_error"></span>
                        </div>
                    </div>

                    <div class="card" style="display: none" id="applicationDetailTableId">
                        <div class="bg-blue card-status card-status-left"></div>
                        <div class="card-body">
                            <div class="col-lg-12">
                                <table class="table table-bordered table-center table-responsive-lg auto-index" id="publicAttTblId">
                                    <thead>
                                    <tr>
                                        <th>Sl No.</th>
                                        <th>Application Number</th>
                                        <th>Action Date</th>
                                        <th>Application Status</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div class="card" style="display: none;" id="closedTable">
                        <div class="bg-blue card-status card-status-left"></div>
                        <div class="card-body">
                            <div class="col-lg-12">
                                <table class="table table-bordered table-center table-responsive-lg auto-index" id="tableId">
                                    <thead>
                                    <tr>
                                        <th>Sl. No.</th>
                                        <th>Application Close Remark</th>
                                    </tr>
                                    </thead>
                                    <tbody id="apple">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<script>

    function checkStatus(){
        var applicationNo=$('#applicationNo').val();
        if(applicationNo==''||applicationNo==null){
            $('applicationDetailTableId').hide();
            $('#errormessage').addClass('error');
            $('#errormessage').html('Please enter reference Number!').show();
            $('#closedTable').hide();
            return false;
        }

        if(applicationNo!=''||applicationNo!=null){
            $('#errormessage').removeClass('error');
            $('#errormessage').text('');
        }
        var url='${pageContext.request.contextPath}/ministryLogin/view_status?appNo='+applicationNo;
        $.ajax({
            url:url,
            type: 'GET',
            success: function (res) {
                var docBtl = '';
                var m = 0;
                var dto = res.dto;
                if(res.status ==1) {
                    for (var i in dto) {
                        /* if(res[i].current_status=='Pmo_forward_to_Pm'||res[i].current_status=='SUBMITTED'||res[i].current_status=='PM_Return_PMO'
                         ||res[i].current_status=='Ministry_PMO_Returns'||res[i].current_status=='Pmo_forward'||res[i].current_status=='Ministry_Department_forward'||res[i].current_status=='Division_Department_return'
                         ||res[i].current_status=='Department_division_forward'||res[i].current_status=='Department_Ministry_Return'||res[i].current_status=='PMO_Closed') {*/
                        /* if(i==res[i].length-1){
                         break;
                         }*/
                        ++m;
                        docBtl = docBtl + "<tr>" +
                        "<td>" + m + "</td>" +
                        "<td>" + dto[i].application_number + "</td>" +
                        "<td>" + dto[i].action_date + "</td>" +
                        "<td>" + dto[i].action_trail + "</td>" + "</tr>";
                        //}
                    }
                    $('#applicationDetailTableId').show();
                    $('#publicAttTblId').find('tbody').html(docBtl);
                }
                else{
                    $('#applicationDetailTableId').hide();
                    // $('#publicAttTblId').hide();
                    $('#errormessage').addClass('error');
                    $('#errormessage').html('Please enter valid reference Number!').show();
                    $('#closedTable').hide();
                    return false;
                }

                var dob = '';
                var cm=0;
                if((res.status==1)){
                    for (var c in dto) {
                        if(dto[c].appl_Close_Remark!=null&&dto[c].appl_Close_Remark!=''){
                            ++cm;
                            dob = dob + "<tr>" +
                            "<td>" + cm + "</td>" +
                            "<td>" + dto[c].appl_Close_Remark + "</td>" +
                            "</tr>";
                            $('#closedTable').show();
                            $('#tableId').find('tbody').html(dob);
                       break;
                        }

                        else{
                            $('#closedTable').hide();
                        }
                    }

                }
                //else if(res.status!=1){


            }
        });
    }
</script>
</body>
</html>



