<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 6/27/2021
  Time: 1:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Ekaasel</title>
    <meta name="decorator" content="/layout/ministry-layout.jsp">
</head>
<body>

<div class="card">
    <span style="font-size: 20px"><b style="color: #000000">&nbsp;&nbsp;&nbsp;&nbsp;Ekaasel </b> >> <b style="color: #000000">Report</b></span>
    <div class="card-body">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <div class="panel panel-default">
                <div class="panel-body">

                    <div class="form-group">
                        <label class="col-md-2"><b style="color: #000000">From Date :</b><span class="text-danger">*</span></label>

                        <div class="col-md-4">
                            <input type="date" class="form-control datepicker field" id="txtFromDate"
                                   name="app_Submission_Date">
                            <span class="text-danger" id="fromDateErrorMsg"></span>
                        </div>
                        <label class="col-md-2"><b style="color: #000000">To Date :</b><span class="text-danger">*</span></label>

                        <div class="col-md-4">
                            <input type="date" class="form-control datepicker field" id="txtToDate"
                                   name="action_date">
                            <span class="text-danger" id="toDateErrorMsg"></span>
                        </div>


                        <div class="mt-2 col-md-12"></div>
                        <div id="trMinistrySelect">
                            <div class="col-lg-2"></div>
                            <div class="col-lg-4">
                                <select name="ministry_Id" class="form-control" id="select_cat">
                                    <option value="0" id="select">Select Ministry:</option>
                                    <c:forEach var="clist" items="${ministries}" varStatus="counter">
                                        <option value="${clist.ministry_Id}">${clist.ministryName}${clist.ministry_Short_Desc}</option>
                                    </c:forEach>
                                </select>
                                <span id="min_selectErrorMsg" class="text-danger"></span>
                            </div>
                        </div>

                        <div class="col-lg-4">
                            <button type="button" onclick="report()" class="btn btn-success" id="btn_submit"><i class="glyphicon glyphicon-eye-open"> </i>  Generate Report</button>
                            <span class="text-danger" id="forward_error"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="card" style="display: none" id="applicationDetailTableId">
        <div class="card-body">
            <div class="table-responsive">
                <table id="publicAttTblId" class="table table-striped table-bordered table-hover" >
                    <thead>
                    <tr>
                        <th style="color: rgba(0, 0, 0, 0.69)">Application No.</th>
                        <th style="color: rgba(0, 0, 0, 0.69)">Application Date</th>
                        <th style="color: rgba(0, 0, 0, 0.69)">Grievance Description</th>
                        <th style="color: rgba(0, 0, 0, 0.69)">PMO Remarks</th>
                        <th style="color: rgba(0, 0, 0, 0.69)">PM Remarks</th>
                        <th style="color: rgba(0, 0, 0, 0.69)">Ministry Remarks</th>
                        <th style="color: rgba(0, 0, 0, 0.69)">Department Remarks</th>
                        <th style="color: rgba(0, 0, 0, 0.69)">Division Remarks</th>
                        <th style="color: rgba(0, 0, 0, 0.69)">Close Remarks</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script>
    var publicAttTblI=$('#publicAttTblId');
    function report(){
        var select_cat = $('#select_cat').val();
        var fromDate = $('#txtFromDate').val();
        var toDate =$('#txtToDate').val();
        //var alls=$('#alls').val();
        if (call_me()){
            var url = '${pageContext.request.contextPath}/pmoLogin/generate?select_cat='+select_cat+ '&fromDate='+fromDate+'&toDate='+toDate;
            $.ajax({
                url:url,
                type: 'GET',
                success: function (res) {
                    var docBtl = '';
                    var m = 0;
//                    var dto = res.dto;
//                    if(res.status ==1) {
//                        for (var i in dto) {
//                            if(dto[i].pM_Remark==null){
//                                dto[i].pM_Remark="";
//                            }
//                            if(dto[i].ministry_Remark==null){
//                                dto[i].ministry_Remark="";
//                            }
//                            if(dto[i].dept_Remark==null){
//                                dto[i].dept_Remark="";
//                            }
//                            if(dto[i].division_Remark==null){
//                                dto[i].division_Remark="";
//                            }
//                            if(dto[i].appl_Close_Remark==null){
//                                dto[i].appl_Close_Remark="";
//                            }
//
//                            ++m;
//                            docBtl = docBtl + "<tr>" +
//                            "<td>" + m + "</td>" +
//                            "<td>" + dto[i].application_Number + "</td>" +
//                            "<td>" + dto[i].app_Submission_Date + "</td>" +
//                            "<td>" + dto[i].appl_Type_Remark + "</td>" +
//                            "<td>" + dto[i].pMO_Remark + "</td>" +
//                            "<td>" + dto[i].pM_Remark + "</td>" +
//                            "<td>" + dto[i].ministry_Remark + "</td>" +
//                            "<td>" + dto[i].dept_Remark + "</td>" +
//                            "<td>" + dto[i].division_Remark + "</td>" +
//                            "<td>" + dto[i].appl_Close_Remark + "</td>" +"</tr>";
//                        }
//                        $('#applicationDetailTableId').show();
//                        $('#publicAttTblId').find('tbody').html(docBtl);
//                    }
//                    else{
//                        $('#applicationDetailTableId').hide();
//                        return false;
//                    }
                    _searchList(res.dto);
                    $('#applicationDetailTableId').show();
                }
            });
        }
    }

    function call_me(){
        var returnVal=true;
        var select_cat = $('#select_cat').val();
        var fromDate = $('#txtFromDate').val();
        var toDate =$('#txtToDate').val();
        if(select_cat==0){
            $('applicationDetailTableId').hide();
            $('#min_selectErrorMsg').addClass('error');
            $('#min_selectErrorMsg').html('Please select Ministry!').show();
            returnVal=false;
        }

        if(select_cat!=0){
            $('#min_selectErrorMsg').removeClass('error');
            $('#min_selectErrorMsg').text('');
        }

        if(fromDate==''){
            $('applicationDetailTableId').hide();
            $('#fromDateErrorMsg').addClass('error');
            $('#fromDateErrorMsg').html('Please select start date!').show();
            returnVal=false;
        }

        if(fromDate!=''){
            $('#fromDateErrorMsg').removeClass('error');
            $('#fromDateErrorMsg').text('');
        }

        if(toDate==''){
            $('applicationDetailTableId').hide();
            $('#toDateErrorMsg').addClass('error');
            $('#toDateErrorMsg').html('Please select end date!').show();
            returnVal=false;
        }

        if(toDate!=''){
            $('#toDateErrorMsg').removeClass('error');
            $('#toDateErrorMsg').text('');
        }
        return returnVal;
    }
    function _searchList(dto) {
        var columnDef = [
            {data: 'application_Number'},
            {data: 'app_Submission_Date'},
            {data: 'appl_Type_Remark'},
            {data: 'pMO_Remark'},

            {data: 'pM_Remark'},
            {data: 'ministry_Remark'},
            {data: 'dept_Remark'},
            {data: 'division_Remark'},
            {data: 'appl_Close_Remark'}
        ];

        if (dto.length > 0) {
            publicAttTblI.dataTable().fnDestroy();
            publicAttTblI.find('tbody').empty();
            publicAttTblI.dataTable({
                data: dto,
                columns: columnDef
            });
        } else {
            publicAttTblI.dataTable().fnDestroy();
            publicAttTblI.find('tbody').empty();
            publicAttTblI.dataTable({
                data: null,
                columns: columnDef
            });
        }
    }
</script>

</body>
</html>
