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
<%List<ApplicationDTO> dtoList=(List<ApplicationDTO>) request.getAttribute("ClaimedLists");%>

<%--<%List<FileAttachmentDTO> fileAttachmentDTOs=(List<FileAttachmentDTO>) request.getAttribute("myTasklist");%>--%>
<html >
<head>
  <%--  <meta name="decorator" content="/layout/ministry-layout.jsp">--%>
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
                                PSGRD Task List
                            </small>
                        </h4>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="bootstrap-admin-box-title">Application Details | Submission Date :
                            <input type="text" value="<%=dtoList.get(0).getApplication_Number()%>" readonly>
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
                        <div class="form-group row" >
                            <label class="col-lg-2"><STRONG>CID No : </STRONG></label>
                            <div class="col-lg-1"></div>
                            <div class="col-lg-6">
                                <input type="text" class="form-control field numeric border-secondary center-block" readonly name="cidNo" value="<%=dtoList.get(0).getcID_Number()%>" id="cid" >
                            </div>
                            <div class="col-lg-3">
                                <img class="img-thumbnail img-fluid rounded"  src="<c:url value='/getCitizenImage${cid}'/>" alt="" style="width: 150px;height: 150px;">
                            </div>
                        </div>
               <%--         <div class="container">
                            <div class="col-md-3">
                                <div class="col-lg-1"></div>  <span id="tdApplicantImage" class="pull-right"><img src="k.jpg"></span>
                            </div> </div>--%>


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
                                <input type="text" class="form-control field numeric border-secondary" name="villageName"
                                       value="<%=dtoList.get(0).getVillage()%>" readonly="readonly" id="village_id">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-lg-2 "><STRONG>Mobile Number : </STRONG></label>
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
                            <label class="col-lg-2"><STRONG>Grievance Description : </STRONG></label>
                            <div class="col-lg-1"></div>
                            <div class="col-lg-9">
                                    <textarea style="height: 130px; width: 100%" name="appl_Type_Remark" class="form-control field numeric border-secondary scroll-bar textarea-scroll"  readonly id="public_area" height="auto" ><%=dtoList.get(0).getAppl_Type_Remark()%></textarea>
                            </div>
                        </div>



                        <input type="hidden" name="current_status" class="form-control field numeric border-secondary" readonly value="<%=dtoList.get(0).getCurrent_status()%>" id="currentStatus" >
                        <input type="hidden" name="previous_stats" class="form-control field numeric border-secondary" readonly value="<%=dtoList.get(0).getPrevious_stats()%>" id="previousStats" >

                        <div class="md-form form-group row">
                            <div id="pmoRemarksId" style="display: none">
                                <label class="col-lg-2"><strong>PMO Remarks<span style="font-weight: bold"></span></strong></label>
                                <div class="col-lg-1"></div>
                                <div class="col-lg-9">
                                    <textarea class="form-control field numeric border-secondary scroll-bar textarea-scroll" id="cRemarks" readonly ><%=dtoList.get(0).getpMO_Remark()%></textarea>
                                    <span id="a"></span>
                                </div>
                            </div>
                        </div>

                        <div  class="md-form form-group row">
                            <div id="ministryRemarksId" style="display: none;">
                                <label class="col-lg-2" ><strong>PM Remarks<span style="font-weight: bold"></span></strong></label>
                                <div class="col-lg-1"></div>
                                <div class="col-lg-9">
                                    <textarea class="form-control field numeric border-secondary scroll-bar textarea-scroll" id="clRemarks" readonly ><%=dtoList.get(0).getpM_Remark()%></textarea>
                                    <span ></span>
                                </div>
                            </div>
                        </div>


                        <div class="md-form form-group row">
                            <div id="min_remarks" style="display: none">
                                <label class="col-lg-2"><strong>Ministry Remarks<span style="font-weight: bold"></span></strong></label>
                                <div class="col-lg-1"></div>
                                <div class="col-lg-9">
                                    <textarea class="form-control field numeric border-secondary scroll-bar textarea-scroll" id="min" readonly ><%=dtoList.get(0).getMinistry_Remark()%></textarea>
                                    <span id="b"></span>
                                </div>
                            </div>
                        </div>

                        <div  class="md-form form-group row">
                            <div id="check" style="display: none">
                                    <label class="col-lg-2"><strong>Department Remarks<span style="font-weight: bold"></span></strong></label>
                                <div class="col-lg-1"></div>
                                <div class="col-lg-9">
                                        <textarea id="idss" class="form-control field numeric border-secondary scroll-bar textarea-scroll" readonly ><%=dtoList.get(0).getDept_Remark()%></textarea>
                                        <span ></span>
                                </div>
                            </div>
                        </div>

                        <div class="md-form form-group row">
                            <div id="divRemarks" style="display:none">
                                <label class="col-lg-2" ><strong>Division Remarks<span style="font-weight: bold"></span></strong></label>
                                <div class="col-lg-1"></div>
                                <div class="col-lg-9">
                                    <textarea id="divrem" class="form-control field numeric border-secondary scroll-bar textarea-scroll" readonly><%=dtoList.get(0).getDivision_Remark()%></textarea>
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
                                <button class="btn btn-info" type="button"onclick="checkDetails('forward'); " name="yesno" id="NoCheck"> <i class="fa fa-forward"></i> Forward To Department</button>
                                <button class="btn btn-info" type="button"  onclick="checkDetails('change'); " name="yesno" id="pmoid"> <i class="fa fa-forward"></i> Return To PMO</button>
                                <button class="btn btn-default active" type="button" onclick="my_print();" id="cmdPrint" style="width: 200px"><i class="fa fa-print"></i> Print</button>
                            </div>
                        </div>

                        <div class="md-form form-group row" id="ifNo" style="display:none">
                            <div id="pmoRemarksDiv">
                                <label class="col-lg-3 control-label"id="tdEnterRemarks" ><strong>Enter Close Remarks<span style="color:red;font-weight: bold">*</span></strong></label>
                              <div class="col-lg-1"></div>
                                <div class="col-lg-9" id="tdPMORemarks">
                                    <textarea name="appl_Close_Remark" id="idPMORemarks" style="height: 70px" class="form-control"></textarea>
                                    <span class="text-danger" id="grievanceError"></span>
                                </div>
                            </div>
                            <div class="mt-2 col-md-12"></div>
                            <div id="trFile">
                                <label class="col-lg-3 control-label"><strong>Supporting Documents(if any): </strong></label>
                                <div class="col-lg-1"></div>
                                <div class="col-lg-9">
                                    <input type="file" name="fileName" value="" id="idFile" class="btn btn-primary" accept="image/jpeg,image/png,.doc,.docx,.pdf,.xlsx,.xls">
                                </div>
                            </div>
                            <div class="mt-2 col-md-12"></div>
                            <div id="trSubmitButton">
                                <div class="col-lg-3"></div>
                                <div class="col-lg-9">
                                    <button type="button" class="btn btn-primary" id="close_submit" onclick="closed_submit()"><i class="fa fa-floppy-o"></i> Submit </button>
                                    <a type="button" id="close_cancel" class="btn btn-primary" href="<c:url value='/ministryLogin/taskList'/>"> <i class="glyphicon glyphicon-arrow-left"></i> Back </a>
                                    <%--<button class="btn btn-warning" id="close_cancel"><i class="fa fa-ban" onclick="closed_cancel()"></i> Cancel</button>--%>
                                    <!--<input type="button" class="btn btn-primary" value="Submit" id="cmdMinistrySubmit" onclick="fwdSubmit()"></input>-->
                                </div>
                            </div>
                        </div>

                            <div class="md-form form-group row" id="ifYes" style="display:none">
                            <div >
                                <label class="col-lg-3 control-label" ><strong>Enter your Remarks:<span style="color:red;font-weight: bold">*</span></strong></label>
                                <div class="col-lg-1"></div>
                                <div class="col-lg-9">
                                    <textarea name="ministry_Remark" id="pmoRemarks" style="height: 70px" class="form-control"></textarea>
                                    <span class="text-danger" id="forward_error"></span>
                                </div>
                            </div>
                                <div class="mt-2 col-md-12"></div>
                            <div>
                                <label class="col-lg-3 control-label"><strong>Supporting Documents(if any): </strong></label>
                                <div class="col-lg-1"></div>
                                <div class="col-lg-9">
                                    <input type="file" name="filler" class="btn btn-primary" accept="image/jpeg,image/png,.doc,.docx,.pdf,.xlsx,.xls">
                                </div>
                            </div>
                                <div class="mt-2 col-md-12"></div>
                            <div id="trMinistrySelect" >
                                <label class="col-lg-3 control-label"><strong>Select Agency<span style="color:red;font-weight: bold">*</span></strong></label>
                                <%--<div class="col-lg-1"></div>--%>
                                <div class="col-lg-6">
                                    <select name="department_Id" class="form-control" id="select_cat">
                                        <option value="0" id="select">Select Department:</option>
                                        <c:forEach var="clist" items="${departments}" varStatus="counter">
                                            <option value="${clist.department_Id}">${clist.department_Name}${clist.department_Short_Desc}${clist.ministry_Id}</option>
                                        </c:forEach>
                                    </select>
                                    <span id="selectErrorMsg" class="text-danger"></span>
                                </div>
                            </div>
                                <div class="mt-2 col-md-12"></div>
                            <div >
                                <div class="col-lg-3"></div>
                                <div class="col-lg-9">
                                    <button type="button" class="btn btn-primary"  onclick="forward_submit()"><i class="fa fa-floppy-o"></i> Submit </button>
                                    <a type="button" id="forward_cancel" class="btn btn-primary" href="<c:url value='/ministryLogin/taskList'/>"> <i class="glyphicon glyphicon-arrow-left"></i> Back </a>
                                   <%-- <button class="btn btn-warning" ><i class="fa fa-ban" id="forward_cancel"></i> Cancel</button>--%>
                                    <!--<input type="button" class="btn btn-primary" value="Submit" id="cmdMinistrySubmit" onclick="fwdSubmit()"></input>-->
                                </div>
                            </div>
                        </div>

                            <div class="md-form form-group row" id="ifcheck1" style="display:none">
                                <div id="showme">
                                    <label class="col-lg-3 control-label" ><strong>Enter your Remarks:<span style="color:red;font-weight: bold">*</span></strong></label>
                                    <div class="col-lg-1"></div>
                                    <div class="col-lg-9">
                                        <textarea name="ministry_Remark" id="mremark" style="height: 70px" class="form-control"></textarea>
                                        <span class="text-danger" id="grievanceErrors"></span>
                                    </div>
                                </div>
                                <div class="mt-2 col-md-12"></div>
                                <div>
                                    <label class="col-lg-3 control-label"><strong>Supporting Documents(if any):</strong></label>
                                    <div class="col-lg-1"></div>
                                    <div class="col-lg-9">
                                        <input type="file" name="fillers" value="" class="btn btn-primary" accept="image/jpeg,image/png,.doc,.docx,.pdf,.xlsx,.xls">
                                    </div>
                                </div>
                                <div class="mt-2 col-md-12"></div>
                                <div id="">
                                    <div class="col-lg-3"></div>
                                    <div class="col-lg-9">
                                        <button type="button" class="btn btn-primary" onclick="return_pmo()"><i class="fa fa-floppy-o"></i> Submit </button>
                                        <a type="button" class="btn btn-primary" href="<c:url value='/ministryLogin/taskList'/>"> <i class="glyphicon glyphicon-arrow-left"></i> Back </a>
                                        <!--<input type="button" class="btn btn-primary" value="Submit" id="cmdMinistrySubmit" onclick="fwdSubmit()"></input>-->
                                    </div>
                                </div>
                            </div>

          </div>
                </div>
            </div>
        </form>
        </div>

<script>

    function my_print(){
        window.print();
    }



    if($('#currentStatus').val()== 'Pmo_forward'&&($('#previousStats').val()== 'Ministry_PMO_Returns'||$('#previousStats').val() == 'PM_Return_PMO')){
        var d_divRemarks=$('#divRemarks');
        var d_pmo_remarks=$('#pmoRemarksId');
        var td_divRemarks=d_divRemarks.clone();
        var td_pmo_remarks=d_pmo_remarks.clone();
        d_divRemarks.replaceWith(td_pmo_remarks);
        d_pmo_remarks.replaceWith(td_divRemarks);
        if($('#divrem').val()!='null'||$('#cRemarks').val()!='null') {
            d_divRemarks.show();
            d_pmo_remarks.show();
        }
      /*  if($('#clRemarks').val()!='null'){
            $('#ministryRemarksId').show();
        }
        if($('#idss').val()!='null'){
            $('#check').show();
        }
        if($('#min').val()!='null'){
            $('#min_remarks').show();
        }*/
    }
    if($('#currentStatus').val() == 'Department_Ministry_Return'&&($('#previousStats').val() == 'Division_Department_forward'||$('#previousStats').val() == 'Ministry_Department_forward')) {
        var dt_divRemarks=$('#divRemarks');
        var dt_pmo_remarks= $('#check');
        var tdt_divRemarks=dt_divRemarks.clone();
        var tdt_pmo_remarks=dt_pmo_remarks.clone();
        dt_divRemarks.replaceWith(tdt_pmo_remarks);
        dt_pmo_remarks.replaceWith(tdt_divRemarks);
        if($('#divrem').val()!='null'||$('#idss').val()!='null') {
            dt_pmo_remarks.show();
            dt_divRemarks.show();
        }
        /*if($('#cRemarks').val()!='null'){
            $('#pmoRemarksId').show();
        }
        if($('#clRemarks').val()!='null'){
            $('#ministryRemarksId').show();
        }

        if($('#min').val()!='null'){
            $('#min_remarks').show();
        }*/
    }


    if($('#currentStatus').val() == 'Pmo_forward'||$('#currentStatus').val() == 'Department_Ministry_Return'){
        if($('#cRemarks').val()!='null'){
            $('#pmoRemarksId').show();
        }
        if($('#clRemarks').val()!='null'){
            $('#ministryRemarksId').show();
        }
        if($('#idss').val()!='null'){
            $('#check').show();
        }

        if($('#divrem').val()!='null'){
            $('#divRemarks').show();
        }
        if($('#min').val()!='null'){
            $('#min_remarks').show();
        }
    }

function return_pmo(){
    if (callme()){
        var mremark=$('#mremark').val();
        var applicationForm =$("#approvetask");
        var doc_type="Ministry to Pmo";
        var url='${pageContext.request.contextPath}/ministryLogin/mp_Returns?document_type='+doc_type;
        /*if(files!=null||files==null){*/
        if(mremark=!null|| mremark!='') {
            var options = {
                target: '#openLayout_min',
                url: url,
                type: 'POST',
                enctype: "multipart/form-data",
                data: applicationForm.serialize()
            };
            applicationForm.ajaxSubmit(options);
        }
    }
}
    function callme(){
        var returnVal=true;
        var mremark=$('#mremark').val();
        if(mremark==null || mremark==''){
            $('#mremark').addClass('error');
            $('#grievanceErrors').html('please enter remarks').show();
            returnVal = false;
        }
      return returnVal;
    }

    $('#mremark').on("keyup blur", function() {
        $('#mremark').on('keyup blur', function () {
            var mremark = $(this).val();
            if (mremark!=null||mremark!='') {
                $('#grievanceErrors').removeClass('error');
                $('#grievanceErrors').text('');
            }
        }); });

    function closed_submit(){
        if(c_call()){
            var idPMORemarks=$('#idPMORemarks');
            var applicationForm =$("#approvetask");
            var doc_type="Ministry_closed_attachement";
            var url='${pageContext.request.contextPath}/ministryLogin/closed_submit?document_type='+doc_type;
            /*if(files!=null||files==null){*/
            if(idPMORemarks=!null|| idPMORemarks!='') {
                var options = {
                    target: '#openLayout_min',
                    url: url,
                    type: 'POST',
                    enctype: "multipart/form-data",
                    data: applicationForm.serialize()
                };
                applicationForm.ajaxSubmit(options);
            }
        }
    }

    function forward_submit(){
        if(f_call()){
            var pmoRemarks=$('#pmoRemarks');
            var applicationForm =$("#approvetask");
            var document_type='Ministry_department_attachment';
            var url='${pageContext.request.contextPath}/ministryLogin/forward_submit?document_type='+document_type;
            if(pmoRemarks=!null|| pmoRemarks!='') {
                var options = {
                    target: '#openLayout_min',
                    url: url,
                    type: 'POST',
                    enctype: "multipart/form-data",
                    data: applicationForm.serialize()
                };
                applicationForm.ajaxSubmit(options);
            }
        }
    }


   /* if($('#idss').val()=='null' || $('#idss').val()==''){
        $('#yesCheck').show();
        $('#NoCheck').show();
        $('#pmoid').hide();
    }
 else {
        $('#yesCheck').show();
        $('#NoCheck').hide();
        $('#pmoid').show();
    }*/

    function checkDetails(type){
            if (type == 'close') {
               $('#ifNo').show();
               $('#ifYes').hide();
                $('#ifcheck1').hide();
           } else if(type=='forward'){
               $('#ifYes').show();
               $('#ifNo').hide();
                $('#ifcheck1').hide();
           }
           else{
               $('#ifYes').hide();
               $('#ifNo').hide();
                $('#ifcheck1').show();
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
        var url='${pageContext.request.contextPath}/ministryLogin/fetchDocuments?appNo='+appNo;
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
                        "<td><a href='${pageContext.request.contextPath}/ministryLogin/donwloadFiles?uuid="+res[i].uuid +"'' target='_blank'> View </a></td>" +
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
        var url='${pageContext.request.contextPath}/ministryLogin/fetchDocuments?appNo='+appNo;
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
                    "<td><a href='${pageContext.request.contextPath} /ministryLogin/donwloadFiles?uuid="+attachmentDto[i].uuid +"'' target='_blank'> View </a></td>" +
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