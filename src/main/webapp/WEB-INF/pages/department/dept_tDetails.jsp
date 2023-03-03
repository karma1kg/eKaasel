<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by kg.
  User: USER
  Date: 6/24/2021
  Time: 3:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<%@ page import="bt.gov.voice.dto.ApplicationDTO" %>
<%@ page import="java.util.List" %>
<html ><%List<ApplicationDTO> dtoList=(List<ApplicationDTO>) request.getAttribute("ClaimedLists");%>

<head>
   <%-- <meta name="decorator" content="/layout/dept-layout.jsp">--%>
    <title>eKaaSel</title>
</head>
<body>

<div class="container" id="targetId">
        <form class="card form-horizontal"  id="approvetask"  method="post" enctype="multipart/form-data">
            <input type="hidden" value="${cid}"/>
            <input type="hidden" name="application_Number" value="<%=dtoList.get(0).getApplication_Number()%>" id="idApplicationNo">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-12 col-lg-12">
                        <h4 class="page-head-line">eKaaSel
                            <small>
                                <i class="ace-icon fa fa-angle-double-right"></i>
                                Department Task List
                            </small>
                        </h4>
                    </div>
                </div>


                <div class="panel panel-default">
                    <div class="panel-heading">

                        <div class="bootstrap-admin-box-title">Application Details | Submission Date :
                            <input type="text" readonly value="<%=dtoList.get(0).getApplication_Number()%> ">
                            <span>|</span>
                            <input type="text" value="<%=dtoList.get(0).getApp_Submission_Date()%>" readonly>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-2"></div>
                            <div class="col-lg-1"></div>
                            <div class="col-lg-6"></div>
                        </div>
                        <div class="form-group control row" >
                            <label class="col-lg-2"><STRONG>CID No: </STRONG></label>
                            <div class="col-lg-1"></div>
                            <div class="col-lg-6">
                                <input type="text" class="form-control field numeric border-secondary center-block" readonly name="cidNo" value="<%=dtoList.get(0).getcID_Number()%>" id="cid" >
                            </div>
                            <div class="col-lg-3">
                                <img class="img-thumbnail img-fluid rounded"  src="<c:url value='/getCitizenImage${cid}'/>" alt="" style="width: 150px;height: 150px;">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-lg-2 "><STRONG>Full Name : </STRONG></label>
                            <div class="col-lg-1"></div>
                            <div class="col-lg-3">
                                <input type="text"class="form-control field numeric border-secondary" name="firstName"  readonly value="<%=dtoList.get(0).getFirst_Name()%>" id="fname" >
                            </div>
                            <div class="col-lg-3">
                                <input type="text"class="form-control field numeric border-secondary" name="middleName" value="<%=dtoList.get(0).getMiddle_Name()%>" readonly="readonly" id="mname" >
                            </div>
                            <div class="col-lg-3">
                                <input type="text" class="form-control field numeric border-secondary" name="lastName" value="<%=dtoList.get(0).getLast_Name()%>" readonly="readonly" id="lname">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-lg-2 "><STRONG>Address : </STRONG></label>
                            <div class="col-lg-1"></div>
                            <div class="col-lg-3">
                                <input type="text" class="form-control field numeric border-secondary" name="dzongkhagName" value="<%=dtoList.get(0).getDzongkhag()%>" readonly="readonly" id="dzo_id" >
                            </div>
                            <div class="col-lg-3">
                                <input type="text" class="form-control field numeric border-secondary" name="gewogName" value="<%=dtoList.get(0).getGewog()%>" readonly="readonly" id="gewog_id" >
                            </div>
                            <div class="col-lg-3">
                                <input type="text" class="form-control field numeric border-secondary" name="villageName" value="<%=dtoList.get(0).getVillage()%>" readonly="readonly" id="village_id">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-lg-2 "><STRONG>Mobile Number</STRONG></label>
                            <div class="col-lg-1"></div>
                            <div class="col-lg-9">
                                <input type="text" class="form-control field numeric border-secondary" readonly name="contact_No" value="<%=dtoList.get(0).getContact_No()%>" id="phone_id">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-lg-2 "><STRONG>E-mail Id : </STRONG></label>
                            <div class="col-lg-1"></div>
                            <div class="col-lg-9">
                                <input type="text" class="form-control field numeric border-secondary" readonly name="email_Id" value="<%=dtoList.get(0).getEmail_Id()%>" id="email" >
                            </div>
                        </div>

                        <div class="md-form form-group row">
                            <label class="col-lg-2"><STRONG>Grievance Description </STRONG></label>
                            <div class="col-lg-1"></div>
                            <div class="col-lg-9">
                                    <textarea style="height: 130px; width: 100%" name="appl_Type_Remark" class="form-control field numeric border-secondary scroll-bar textarea-scroll"  readonly id="public_area" height="auto" ><%=dtoList.get(0).getAppl_Type_Remark()%></textarea>
                            </div>
                        </div>



                        <input type="hidden" name="current_status" class="form-control field numeric border-secondary" readonly value="<%=dtoList.get(0).getCurrent_status()%>" id="currentStatus" >
                        <input type="hidden" name="previous_stats" class="form-control field numeric border-secondary" readonly value="<%=dtoList.get(0).getPrevious_stats()%>" id="previousStats" >
                        <div class="md-form form-group row">
                            <div id="cRemarks" style="display:none" >
                                <label class="col-lg-2" ><strong>PMO Remarks<span style="font-weight: bold"></span></strong></label>
                                <div class="col-lg-1"></div>
                                <div class="col-lg-9">
                                    <textarea id="pmo" class="form-control field numeric border-secondary scroll-bar textarea-scroll" readonly ><%=dtoList.get(0).getpMO_Remark()%></textarea>
                                    <span id="a"></span>
                                </div>
                            </div></div>

                        <div class="md-form form-group row">
                            <div id="clRemarks" style="display: none" >
                                <label class="col-lg-2" ><strong>PM Remarks<span style="font-weight: bold"></span></strong></label>
                                <div class="col-lg-1"></div>
                                <div class="col-lg-9">
                                    <textarea id="pm" class="form-control field numeric border-secondary scroll-bar textarea-scroll" readonly ><%=dtoList.get(0).getpM_Remark()%></textarea>
                                    <span id=""></span>
                                </div>
                            </div>
                        </div>
                            <input type="hidden" class="form-control field numeric border-secondary" readonly value="<%=dtoList.get(0).getMinistry_Remark()%>" id="pMRemId" >
                                <div class="md-form form-group row">
                                    <div id="mRemarks" style="display:none">
                                        <label class="col-lg-2" ><strong>Ministry Remarks<span style="font-weight: bold"></span></strong></label>
                                        <div class="col-lg-1"></div>
                                        <div class="col-lg-9">
                                            <textarea name="ministry_Remark" id="m" class="form-control field numeric border-secondary scroll-bar textarea-scroll" readonly ><%=dtoList.get(0).getMinistry_Remark()%></textarea>
                                            <span id="sth"></span>
                                        </div>
                                    </div>
                                </div>

                            <input type="hidden" class="form-control field numeric border-secondary" readonly value="<%=dtoList.get(0).getDivision_Remark()%>" id="divId" >
                            <input type="hidden" class="form-control field numeric border-secondary" readonly value="<%=dtoList.get(0).getDept_Remark()%>" id="deptid" >
                            <div class="md-form form-group row">
                                <div id="deptRemarks" style="display:none">
                                    <label class="col-lg-2" ><strong>Department Remarks<span style="font-weight: bold"></span></strong></label>
                                    <div class="col-lg-1"></div>
                                    <div class="col-lg-9">
                                        <textarea id="dept" class="form-control field numeric border-secondary scroll-bar textarea-scroll" readonly ><%=dtoList.get(0).getDept_Remark()%></textarea>
                                        <span></span>
                                    </div>
                                </div>
                            </div>

                            <div class="md-form form-group row">
                                <div id="divRemarks" style="display:none">
                                    <label class="col-lg-2" ><strong>Division Remarks<span style="font-weight: bold"></span></strong></label>
                                  <div class="col-lg-1"></div>
                                    <div class="col-lg-9">
                                        <textarea id="d" class="form-control field numeric border-secondary scroll-bar textarea-scroll" readonly><%=dtoList.get(0).getDivision_Remark()%></textarea>
                                        <span id="okey"></span>
                                    </div>
                                </div>
                            </div>

                        <div class="card" id="apple">
                            <div class="bg-blue card-status card-status-left"></div>
                            <div class="card-header bg-gray-dark-lighter">
                                <h5 class="card-title text-white">Attachments</h5>
                            </div>
                            <div class="card-body">
                                <div class="col-lg-12">
                                    <table class="table table-bordered table-center table-responsive-lg auto-index" id="publicAttTblId">
                                        <thead>
                                        <tr>
                                            <th>Sl No.</th>
                                            <th>Document Name</th>
                                            <th>Sent By</th>
                                            <th>View/download</th>
                                        </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                            <div class="form-group" id="buttonList">
                                    <div class="col-lg-3"></div>
                                    <div class="col-lg-9">
                                        <button type="button" class="btn btn-warning" onclick="checkDetails('close')" name="yesno" id="yesCheck" style="width: 200px"><i class="fa fa-close"></i>Close</button>
                                        <button class="btn btn-info" type="button" id="cmdMinistry" onclick="checkDetails('return'); " name="yesno" id="NoCheck"> <i class="fa fa-forward"></i> Return To Ministry</button>
                                        <button class="btn btn-info" type="button" id="cmdiv" onclick="checkDetails('forward'); " name="yesno" id="NoChecks"> <i class="fa fa-forward"></i> Forward To Division</button>

                                        <button class="btn btn-default active" type="button" id="cmdPrint" onclick="my_print();" style="width: 200px"><i class="fa fa-print"></i> Print</button>
                                    </div>
                                </div>

                                <div class="md-form form-group row" id="ifNo" style="display:none">
                                    <div class="form-group" id="pmoRemarksDiv">
                                        <label class="col-lg-3 control-label"id="tdEnterRemarks"><strong>Enter Close Remarks:<span style="color:red;font-weight: bold">*</span></strong></label>
                                        <div class="col-lg-1"></div>
                                        <div class="col-lg-9" id="tdPMORemarks">
                                            <textarea name="appl_Close_Remark" id="idPMORemarks" style="height: 70px" class="form-control"></textarea>
                                            <span class="text-danger" id="grievanceError"></span>
                                        </div>
                                    </div>

                                    <div class="form-group" id="trFile">
                                        <label class="col-lg-3 control-label"><strong>Supporting Documents(if any): </strong></label>
                                        <div class="col-lg-1"></div>
                                        <div class="col-lg-9">
                                            <input type="file" name="fileName" id="idFile" class="btn btn-primary" accept="image/jpeg,image/png,.doc,.docx,.pdf,.xlsx,.xls">
                                        </div>
                                    </div>
                                    <div class="form-group" id="trSubmitButton">
                                        <div class="col-lg-3"></div>
                                        <div class="col-lg-9">
                                            <button type="button" class="btn btn-primary" id="close_submit" onclick="closed_submit()"><i class="fa fa-floppy-o"></i> Submit </button>
                                            <a type="button" class="btn btn-primary" href="<c:url value='/dept_login/taskList'/>"> <i class="glyphicon glyphicon-arrow-left"></i> Back </a>                                            <!--<input type="button" class="btn btn-primary" value="Submit" id="cmdMinistrySubmit" onclick="fwdSubmit()"></input>-->
                                        </div>
                                    </div>
                                </div>

                                <div class="md-form form-group row" id="ifYes" style="display:none">
                                    <div  class="form-group">
                                        <label class="col-lg-3 control-label" ><strong>Enter your Remarks:<span style="color:red;font-weight: bold">*</span></strong></label>
                                      <div class="col-lg-1"></div>
                                        <div class="col-lg-9">
                                            <textarea name="dept_Remark" id="pmoRemarks" style="height: 70px" class="form-control"></textarea>
                                            <span class="text-danger" id="forward_error"></span>
                                        </div>
                                    </div>

                                    <div class="form-group" >
                                        <label class="col-lg-3 control-label"><strong>Supporting Documents(if any): </strong></label>
                                        <div class="col-lg-1"></div>
                                        <div class="col-lg-9">
                                            <input type="file" name="filler" class="btn btn-primary" accept="image/jpeg,image/png,.doc,.docx,.pdf,.xlsx,.xls">
                                        </div>
                                    </div>

                                    <div class="form-group row" id="trMinistrySelect">
                                        <label class="col-lg-3 control-label"><strong>Select Agency<span style="color:red;font-weight: bold">*</span></strong></label>
                                        <div class="col-lg-6">
                                            <select name="ministry_Id" class="form-control" id="select_cat">
                                                <option value="0" id="select">Select Ministry:</option>
                                                <c:forEach var="clist" items="${ministry_list}" varStatus="counter">
                                                    <option value="${clist.ministry_Id}">${clist.ministryName}${clist.ministry_Short_Desc}</option>
                                                </c:forEach>
                                            </select>
                                            <span id="selectErrorMsg" class="text-danger"></span>
                                        </div>
                                    </div>

                                    <div class="form-group" >
                                        <div class="col-lg-3"></div>
                                        <div class="col-lg-9">
                                            <button type="button" class="btn btn-primary"  onclick="forward_submit()"><i class="fa fa-floppy-o"></i> Submit </button>
                                            <a type="button" class="btn btn-primary" href="<c:url value='/dept_login/taskList'/>"> <i class="glyphicon glyphicon-arrow-left"></i> Back </a>                                            <!--<input type="button" class="btn btn-primary" value="Submit" id="cmdMinistrySubmit" onclick="fwdSubmit()"></input>-->
                                        </div>
                                    </div>
                                </div>


                                    <div class="md-form form-group row" id="division_id" style="display:none">
                                        <div class="form-group" >
                                            <label class="col-lg-3 control-label" ><strong>Enter your Remarks:<span style="color:red;font-weight: bold">*</span></strong></label>
                                            <div class="col-lg-1"></div>
                                            <div class="col-lg-9">
                                                <textarea name="dept_Remark" id="pmRemark" style="height: 70px" class="form-control"></textarea>
                                                <span class="text-danger" id="forward_errors"></span>
                                            </div>
                                        </div>

                                        <div class="form-group" >
                                            <label class="col-lg-3 control-label"><strong>Supporting Documents(if any): </strong></label>
                                            <div class="col-lg-1"></div>
                                            <div class="col-lg-9">
                                                <input type="file" name="fillers" class="btn btn-primary" accept="image/jpeg,image/png,.doc,.docx,.pdf,.xlsx,.xls">
                                            </div>
                                        </div>

                                        <div class="form-group row" id="trdivisionSelect">
                                            <label class="col-lg-3 control-label"><strong>Select Agency<span style="color:red;font-weight: bold">*</span></strong></label>
                                            <div class="col-lg-6">
                                                <select name="dept_Division_Id" class="form-control" id="select_cats">
                                                    <option value="0" id="select">Select Division:</option>
                                                    <c:forEach var="clis" items="${division_list}" varStatus="counter">
                                                        <option value="${clis.dept_Division_Id}">${clis.dept_Division}${clis.department_id}</option>
                                                    </c:forEach>
                                                </select>
                                                <span id="selectErrorMsgs" class="text-danger"></span>
                                            </div>
                                        </div>

                                        <div class="form-group" >
                                            <div class="col-lg-3"></div>
                                            <div class="col-lg-9">
                                                <button type="button" class="btn btn-primary"  onclick="forward_submits()"><i class="fa fa-floppy-o"></i> Submit </button>&nbsp;&nbsp;
                                                <a type="button" class="btn btn-primary" href="<c:url value='/dept_login/taskList'/>"> <i class="glyphicon glyphicon-arrow-left"></i> Back </a>                                                <!--<input type="button" class="btn btn-primary" value="Submit" id="cmdMinistrySubmit" onclick="fwdSubmit()"></input>-->
                                            </div>
                                        </div>
                                    </div>

                            </div>
                       </div>
                </div>
        </form>
    </div>
</div>

<script>
    function my_print(){
        window.print();
    }
    function forward_cancel(){
        document.getElementById($('ifYes').val()).reset();
    }
    function closed_cancel(){
        document.getElementById("approvetask").reset();
    }
    function closed_cancels(){
        document.getElementById("approvetask").reset();
    }

    function checkDetails(type){
        if(type=='close'){
            $('#ifNo').show();
            $('#ifYes').hide();
            $('#division_id').hide();
        } else if(type=='return'){
            $('#ifYes').show();
            $('#ifNo').hide();
            $('#division_id').hide();
        }
        else {
            $('#ifYes').hide();
            $('#ifNo').hide();
            $('#division_id').show();
        }
    }


    function forward_submits(){
        if(f_calls()){
            var pmRemark=$('#pmRemark').val();
            var applicationForm =$("#approvetask");
            var document_type="Department to Division";
            var url='${pageContext.request.contextPath}/dept_login/forward_submits?document_type='+document_type;
                var options = {
                    target: '#targetId',
                    url: url,
                    type: 'POST',
                    enctype: "multipart/form-data",
                    data: applicationForm.serialize()
                };
                applicationForm.ajaxSubmit(options);
    }
    }
    function f_calls(){
        var returnVal=true;
        var pmRemark=$('#pmRemark');
        var select_cats=$('#select_cats').val();
        if(pmRemark==null||pmRemark==''){
            $('#pmRemark').addClass('error');
            $('#forward_errors').html('Please Enter Remarks').show();
            returnVal = false;
        }
        if(select_cats==0){
            $('#select_cats').addClass('error');
            $('#selectErrorMsgs').html('Please Select Category').show();
            returnVal=false;
        }
        return returnVal;
    }
    $('#pmRemark').on("keyup blur", function() {
        $('#pmRemark').on('keyup blur', function () {
            var pmRemark = $(this).val();
            if (pmRemark!=null||pmRemark!='') {
                $('#forward_errors').removeClass('error');
                $('#forward_errors').text('');
            }
        }); });
    $('#select_cats').on('keyup blur', function(){
        var select_cats=$(this).val();
        if(select_cats!=0){
            $('#selectErrorMsgs').removeClass('error');
            $('#selectErrorMsgs').text('');
        }
    });

    if($('#currentStatus').val()=='Ministry_Department_forward'&&($('#previousStats').val()=='Department_Ministry_Return'||$('#previousStats').val()=='Pmo_forward')) {
        var d_divRemarks = $('#divRemarks');
        var d_pmo_remarks = $('#mRemarks');
        var td_divRemarks = d_divRemarks.clone();
        var td_pmo_remarks = d_pmo_remarks.clone();
        d_divRemarks.replaceWith(td_pmo_remarks);
        d_pmo_remarks.replaceWith(td_divRemarks);
        if ($('#dept').val() != 'null' || $('#m').val() != 'null') {
            d_divRemarks.show();
            d_pmo_remarks.show();
        }
    }

   if($('#currentStatus').val()=='Ministry_Department_forward'||$('#currentStatus').val()=='Division_Department_forward'){
       if($('#pm').val() != 'null'){
           $('#clRemarks').show();
       }
       if($('#pmo').val()!='null'){
           $('#cRemarks').show();
       }
       if($('#m').val()!='null'){
           $('#mRemarks').show();
       }
        if($('#dept').val()!='null'){
        $('#deptRemarks').show();
        }
        if($('#d').val()!='null'){
        $('#divRemarks').show();
        }
    }




    function closed_submit(){
        if(c_call()){
            var idPMORemarks=$('#idPMORemarks');
            var applicationForm =$("#approvetask");
            var document_type="Department_closed_attachment";
            var url='${pageContext.request.contextPath}/dept_login/closed_submit?document_type='+document_type;
                var options = {
                    target: '#targetId',
                    url: url,
                    type: 'POST',
                    enctype: "multipart/form-data",
                    data: applicationForm.serialize(),
                    success: function (data) {
                        $('#acknowledgement').show();
                        $('#ackMsg').html(data.text);
                    }
                };
                applicationForm.ajaxSubmit(options);
        }
    }


    function forward_submit(){
        if(f_call()){
            var pmoRemarks=$('#pmoRemarks');
            var applicationForm =$("#approvetask");
            var document_type="Department to Ministry";
            var url='${pageContext.request.contextPath}/dept_login/saved_forward?document_type='+document_type;
                var options = {
                    target: '#targetId',
                    url: url,
                    type: 'POST',
                    enctype: "multipart/form-data",
                    data: applicationForm.serialize()
                };
                applicationForm.ajaxSubmit(options);
        }
    }


    function c_call(){
        var returnVal=true;
        var idPMORemarks=$('#idPMORemarks').val();
        if(idPMORemarks==null||idPMORemarks==''){
            $('#grievanceError').html('Please Enter Remarks');
            returnVal=false;
        }
        return returnVal;
    }

    function f_call(){
        var returnVal=true;
        var pmoRemarks=$('#pmoRemarks').val();
        var select_cat=$('#select_cat').val();
        if(pmoRemarks==null||pmoRemarks==''){
            $('#pmoRemarks').addClass('error');
            $('#forward_error').html('Please Enter Remarks').show();
            returnVal = false;
        }
        if(select_cat==0){
            $('#select_cat').addClass('error');
            $('#selectErrorMsg').html('Please Select Category').show();
            returnVal=false;
        }
        return returnVal;
    }

    $('#idPMORemarks').on("keyup blur", function() {
        $('#idPMORemarks').on('keyup blur', function () {
            var idPMORemarks = $(this).val();
            if (idPMORemarks!=null||idPMORemarks!='') {
                $('#grievanceError').removeClass('error');
                $('#grievanceError').text('');
            }
        });

    });

    $('#pmoRemarks').on("keyup blur", function() {
        $('#pmoRemarks').on('keyup blur', function () {
            var pmoRemarks = $(this).val();
            if (pmoRemarks!=null||pmoRemarks!='') {
                $('#forward_error').removeClass('error');
                $('#forward_error').text('');
            }
        }); });
    $('#select_cat').on('keyup blur', function(){
        var select_cat=$(this).val();
        if(select_cat!=0){
            $('#selectErrorMsg').removeClass('error');
            $('#selectErrorMsg').text('');
        }
    });


    $(document).ready(function() {
        var appNo=$('#idApplicationNo').val();
        var url='${pageContext.request.contextPath}/dept_login/fetchDocuments?appNo='+appNo;
        $.ajax({
            url:url,
            type: 'GET',
            success: function (res) {
                var docBtl = '';
                var m =0;
                if(res.length>0){
                    for(var i in res){
                        m++;
                        docBtl = docBtl + "<tr>"+
                        "<td>" +m+ "</td>"+
                        "<td>" + res[i].filename+ "</td>"+
                        "<td>" + res[i].documentType+ "</td>"+
                        "<td><a href='${pageContext.request.contextPath}/dept_login/donwloadFiles?uuid="+res[i].uuid +"'' target='_blank'> View </a></td>" +
                        "</tr>";
                    }
                    $('#publicAttTblId').find('tbody').append(docBtl);
                } else{
                    $('#apple').hide();
                    $('#publicAttTblId').hide();
                }
            }
        });
    } );

   /* function fetchDocuments(){
        var appNo=$('#idApplicationNo').val();
        var url='${pageContext.request.contextPath}/dept_login/fetchDocuments?appNo='+appNo;
        $.ajax({
            url:url,
            type: 'GET',
            success: function (res) {
                var docBtl = '';
                var attachmentDto = res.dto;
                var m =0;
                for(var i in attachmentDto){
                    m++;
                    docBtl = docBtl + "<tr>"+
                    "<td>" +m+ "</td>"+
                    "<td>" + attachmentDto[i].filename+ "</td>"+
                    "<td>" + attachmentDto[i].documentType+ "</td>"+
                    "<td><a href='${pageContext.request.contextPath}/dept_login/donwloadFiles?uuid="+attachmentDto[i].uuid +"'' target='_blank'> View </a></td>" +
                    "</tr>";
                }
                $('#publicAttTblId').find('tbody').append(docBtl);
            }
        });
    }

    function init(){
        fetchDocuments();
    }
    $(document).ready(function (){
                init();
            }
    );*/

</script>

</body>
</html>
