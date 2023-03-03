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

    <title>eKaaSel</title>

</head>
<body>

<div class="container" id="targetId">
    <div class="card">
            <div class="card-body">
                <form class="card form-horizontal"  id="approvetask"  action="#" method="post" enctype="multipart/form-data">
                    <input type="hidden" value="${cid}"/>
                    <input type="hidden" name="application_Number" value="<%=dtoList.get(0).getApplication_Number()%>" id="idApplicationNo"/>
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




                        <div class="md-form form-group row">
                            <div id="pmoRemarksId" style="display: none">
                                <label class="col-lg-2"  ><strong>PMO Remarks<span style="font-weight: bold"></span></strong></label>
                                <div class="col-lg-1"></div>
                                <div class="col-lg-9">
                                    <textarea class="form-control field numeric border-secondary scroll-bar textarea-scroll" id="cRemarks" readonly ><%=dtoList.get(0).getpMO_Remark()%></textarea>
                                    <span id="a"></span>
                                </div>
                            </div>
                        </div>

                        <input type="hidden" name="current_status" class="form-control field numeric border-secondary" readonly value="<%=dtoList.get(0).getCurrent_status()%>" id="currentStatus" >
                        <input type="hidden" name="previous_stats" class="form-control field numeric border-secondary" readonly value="<%=dtoList.get(0).getPrevious_stats()%>" id="previousStats" >

                            <div class="md-form form-group row" >
                                <div id="pmRemarksId" style="display: none;">
                                    <label class="col-lg-2"><strong>PM Remarks<span style="font-weight: bold"></span></strong></label>
                                    <div class="col-lg-1"></div>
                                    <div class="col-lg-9">
                                        <textarea class="form-control field numeric border-secondary scroll-bar textarea-scroll" id="clRemarks"  readonly ><%=dtoList.get(0).getpM_Remark()%></textarea>
                                        <span id=""></span>
                                    </div>
                                </div></div>

                                <div class="md-form form-group row">
                                <div id="check1" style="display:none;">
                                        <label class="col-lg-2"><strong>Ministry Remarks<span style="font-weight: bold"></span></strong></label>
                                        <div class="col-lg-1"></div>
                                        <div class="col-lg-9">
                                            <textarea id="idss1" class="form-control field numeric border-secondary scroll-bar textarea-scroll" style="height: auto" class="form-control" readonly ><%=dtoList.get(0).getMinistry_Remark()%></textarea>
                                            <span></span>
                                        </div>
                               </div>
                                </div>

                        <div class="md-form form-group row">
                                <div id="check" style="display: none">
                                        <label class="col-lg-2" ><strong>Department Remarks<span style="font-weight: bold"></span></strong></label>
                                    <div class="col-lg-1"></div>
                                    <div class="col-lg-9">
                                            <textarea id="idss" style="height: auto" class="form-control field numeric border-secondary scroll-bar textarea-scroll" readonly ><%=dtoList.get(0).getDept_Remark()%></textarea>
                                            <span ></span>
                                        </div>
                                   </div>
                        </div>

                        <div class="md-form form-group row">
                            <div id="divRemarks" style="display:none">
                                <label class="col-lg-2" ><strong>Division Remarks<span style="font-weight: bold"></span></strong></label>
                                <div class="col-lg-1"></div>
                                <div class="col-lg-9">
                                    <textarea id="divRemark" class="form-control field numeric border-secondary scroll-bar textarea-scroll" readonly><%=dtoList.get(0).getDivision_Remark()%></textarea>
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
                                        <button class="btn btn-info" type="button" onclick="checkDetails('forward')" name="yesno" id="NoCheck"> <i class="fa fa-forward"></i> Return To Department </button>
                                        <button class="btn btn-default active" type="button" id="cmdPrint" onclick="my_print();" style="width: 200px"><i class="fa fa-print"></i> Print</button>
                                    </div>
                                </div>

                                <div class="md-form form-group row" id="ifNo" style="display:none">
                                    <div id="pmoRemarksDiv">
                                        <label class="col-lg-3 control-label"id="tdEnterRemarks" ><strong>Enter Close Remarks:<span style="color:red;font-weight: bold">*</span></strong></label>
                                        <div class="col-lg-1"></div>
                                        <div class="col-lg-9" id="tdPMORemarks">
                                            <textarea name="appl_Close_Remark" id="idPMORemarks" style="height: 70px" class="form-control"></textarea>
                                            <span class="text-danger" id="grievanceError"></span>
                                        </div>
                                    </div>
                                    <div class="mt-2 col-md-12"></div>
                                    <div class="form-group" id="trFile">
                                        <label class="col-lg-3 control-label"><strong>Supporting Documents(if any): </strong></label>
                                        <div class="col-lg-1"></div>
                                        <div class="col-lg-9">
                                            <input type="file" name="filling" value="" id="idFile" class="btn btn-primary" accept="image/jpeg,image/png,.doc,.docx,.pdf,.xlsx,.xls">
                                        </div>
                                    </div>
                                    <div class="mt-2 col-md-12"></div>
                                    <div id="trSubmitButton">
                                        <div class="col-lg-3"></div>
                                        <div class="col-lg-9">
                                            <button type="button" class="btn btn-primary" id="close_submit" onclick="closed_submit()"><i class="fa fa-floppy-o"></i> Submit </button>
                                            <a type="button" class="btn btn-primary" href="<c:url value='/divLogin/taskList'/>"> <i class="glyphicon glyphicon-arrow-left"></i> Back </a>                                            <!--<input type="button" class="btn btn-primary" value="Submit" id="cmdMinistrySubmit" onclick="fwdSubmit()"></input>-->
                                        </div>
                                    </div>
                                </div>
                          <div class="mt-2 col-md-12"></div>

                                <div class="md-form form-group row" id="ifYes" style="display:none">
                                    <div>
                                        <label class="col-lg-3 control-label" ><strong>Enter your Remarks:<span style="color:red;font-weight: bold">*</span></strong></label>
                                        <div class="col-lg-1"></div>
                                        <div class="col-lg-9">
                                            <textarea name="division_Remark" id="pmoRemarks" style="height: 70px" class="form-control"></textarea>
                                            <span class="text-danger" id="forward_error"></span>
                                        </div>
                                    </div>
                                    <div class="mt-2 col-md-12"></div>
                                    <div >
                                        <label class="col-lg-3 control-label"><strong>Supporting Documents(if any): </strong></label>
                                        <div class="col-lg-1"></div>
                                        <div class="col-lg-9">
                                            <input type="file" name="fillers" class="btn btn-primary" accept="image/jpeg,image/png,.doc,.docx,.pdf,.xlsx,.xls">
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
                                    <div>
                                        <div class="col-lg-3"></div>
                                        <div class="col-lg-9">
                                            <button type="button" class="btn btn-primary"  onclick="forward_submit()"><i class="fa fa-floppy-o"></i> Submit </button>
                                            <a type="button" class="btn btn-primary" href="<c:url value='/divLogin/taskList'/>"> <i class="glyphicon glyphicon-arrow-left"></i> Back </a>                                            <!--<input type="button" class="btn btn-primary" value="Submit" id="cmdMinistrySubmit" onclick="fwdSubmit()"></input>-->
                                            <!--<input type="button" class="btn btn-primary" value="Submit" id="cmdMinistrySubmit" onclick="fwdSubmit()"></input>-->
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                 </form>
            </div>
    </div>
</div>


<script>
    function my_print(){
        window.print();
    }
    function forward_cancel(){
        document.getElementById("ifYes").reset();
    }
    function closed_cancel(){
        document.getElementById("ifNo").reset();
    }



//if($('#currentStatus').val() == 'Department_division_forward'&&$('#previousStats').val()=='Division_Department_forward'){


    if($('#currentStatus').val() == 'Department_division_forward'){
        if($('#cRemarks').val() != "null"){
            $('#pmoRemarksId').show();
        }
        if($('#clRemarks').val()!='null'){
            $('#pmRemarksId').show();
        }
        if($('#idss1').val()!='null'){
            $('#check1').show();
        }
       if($('#idss').val()!='null'){
           $('#check').show();
       }
        if($('#divRemark').val()!='null'){
            $('#divRemarks').show();
        }
    }
    if($('#currentStatus').val() == 'Department_division_forward'&&$('#divRemark').val() != 'null') {
        var d_divRemarks = $('#divRemarks');
        var d_pmo_remarks = $('#check');
        var td_divRemarks = d_divRemarks.clone();
        var td_pmo_remarks = d_pmo_remarks.clone();
        d_divRemarks.replaceWith(td_pmo_remarks);
        d_pmo_remarks.replaceWith(td_divRemarks);
        if ($('#divRemark').val() != 'null' || $('#idss').val() != 'null') {
            d_divRemarks.show();
            d_pmo_remarks.show();
        }
    }

    function closed_submit(){
        if(c_call()){
            var idPMORemarks=$('#idPMORemarks');
            var applicationForm =$("#approvetask");
            var doc_type="Division_closed_attachment";
            var url='${pageContext.request.contextPath}/divLogin/closed_submit?document_type='+doc_type;
            /*if(files!=null||files==null){*/
            if(idPMORemarks=!null|| idPMORemarks!='') {
                var options = {
                    target: '#openLayout_div',
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
            var document_type='Division to Department';
            var url='${pageContext.request.contextPath}/divLogin/forward_submit?document_type='+document_type;
            /*if(files!=null||files==null){*/
            if(pmoRemarks=!null|| pmoRemarks!='') {
                var options = {
                    target: '#openLayout_div',
                    url: url,
                    type: 'POST',
                    enctype: "multipart/form-data",
                    data: applicationForm.serialize()
                };
                applicationForm.ajaxSubmit(options);
            }
        }
    }

    function call_this(){
        var returnVal=true;
        var ids=$('#ids').val();
        if(ids==null||ids==''){
            $('#ids').addClass('error');
            $('#error').html('Please Enter Remarks').show();
            returnVal = false;
        }
        return returnVal;
    }


    function checkDetails(type) {
        if (type == 'close') {
            $('#ifNo').show();
            $('#ifYes').hide();
        }
        else {
            $('#ifYes').show();
            $('#ifNo').hide();
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
        var url='${pageContext.request.contextPath}/divLogin/fetchDocuments?appNo='+appNo;
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
                        "<td><a href='${pageContext.request.contextPath}/divLogin/donwloadFiles?uuid="+res[i].uuid +"'' target='_blank'> View </a></td>" +
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
        var url='${pageContext.request.contextPath}/divLogin/fetchDocuments?appNo='+appNo;
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
                    "<td><a href='${pageContext.request.contextPath} /divLogin/donwloadFiles?uuid="+attachmentDto[i].uuid +"'' target='_blank'> View </a></td>" +
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