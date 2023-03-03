<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by kg.
  User: USER
  Date: 6/24/2021
  Time: 3:25 PM
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<%@ page import="bt.gov.voice.dto.ApplicationDTO" %>
<%@ page import="java.util.List" %>
<%List<ApplicationDTO> dtoList=(List<ApplicationDTO>) request.getAttribute("ClaimedLists");%>

<%--<%List<FileAttachmentDTO> fileAttachmentDTOs=(List<FileAttachmentDTO>) request.getAttribute("myTasklist");%>--%>
<html>
<head>
    <title>eKaaSel</title>
</head>
<body>
<div class=" card-body" id="targetId">
    <form class=" form-horizontal"  id="approvetask"  method="post" enctype="multipart/form-data">
        <input type="hidden" value="${cid}"/>
        <input type="hidden" name="application_Number" value="<%=dtoList.get(0).getApplication_Number()%>" id="idApplicationNo">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="bootstrap-admin-box-title">Application Details | Submission Date :
                                <input readonly value="<%=dtoList.get(0).getApplication_Number()%>" >
                                <span>|</span>
                                <input readonly value="<%=dtoList.get(0).getApp_Submission_Date()%>" >
                            </div>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-2"></div>
                                <div class="col-lg-1"></div>
                                <div class="col-lg-6"></div>
                            </div>
                                    <div class="form-group control row" >
                                        <label class="col-lg-2" ><STRONG>CID No : </STRONG></label>
                                        <div class="col-lg-1"></div>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control field numeric border-secondary center-block" readonly name="cidNo" value="<%=dtoList.get(0).getcID_Number()%>" id="cid" >
                                        </div>

                                            <div class="col-lg-3">
                                                <img class="img-thumbnail img-fluid rounded"  src="<c:url value='/getCitizenImage${cid}'/>" alt="" style="width: 150px;height: 150px;">
                                            </div>

                                    </div>

                            <div class="form-group row">
                            <label class="col-lg-2"><STRONG>Full Name : </STRONG></label>
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
                                    <textarea style="height: 130px; width: 100%" class="form-control field numeric border-secondary scroll-bar textarea-scroll" readonly id="public_area"><%=dtoList.get(0).getAppl_Type_Remark()%></textarea>
                                </div>
                            </div>


                            <input type="hidden" name="current_status" class="form-control field numeric border-secondary" readonly value="<%=dtoList.get(0).getCurrent_status()%>" id="currentStatus" >
                            <input type="hidden" name="previous_stats" class="form-control field numeric border-secondary" readonly value="<%=dtoList.get(0).getPrevious_stats()%>" id="previousStats" >
                            <div class="md-form form-group row">
                                <div id="cRemarks" style="display: none">
                                    <label class="col-lg-2" ><strong>PMO Remarks<span style="font-weight: bold"></span></strong></label>
                                    <div class="col-lg-1"></div>
                                    <div class="col-lg-9">
                                        <textarea id="pmoid" class="form-control field numeric border-secondary scroll-bar textarea-scroll" readonly ><%=dtoList.get(0).getpMO_Remark()%></textarea>
                                        <span id="a"></span>
                                    </div>
                                </div>
                            </div>

                                <div class="md-form form-group row">
                                    <div id="clRemarks" style="display: none;">
                                        <label class="col-lg-2" ><strong>PM Remarks<span style="font-weight: bold"></span></strong></label>
                                        <div class="col-lg-1"></div>
                                        <div class="col-lg-9">
                                            <textarea id="pmid" class="form-control field numeric border-secondary scroll-bar textarea-scroll" readonly ><%=dtoList.get(0).getpM_Remark()%></textarea>
                                            <span id=""></span>
                                        </div>
                                    </div>
                                </div>



                            <div class="md-form form-group row">
                                <div id="mRemarks" style="display: none;">
                                    <label class="col-lg-2" ><strong>Ministry Remarks<span style="font-weight: bold"></span></strong></label>
                                    <div class="col-lg-1"></div>
                                    <div class="col-lg-9">
                                        <textarea id="mid" class="form-control field numeric border-secondary scroll-bar textarea-scroll" readonly ><%=dtoList.get(0).getMinistry_Remark()%></textarea>
                                        <span></span>
                                    </div>
                                </div>
                            </div>

                            <div class="md-form form-group row">
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
                                    <label class="col-lg-2"><strong>Division Remarks<span style="font-weight: bold"></span></strong></label>
                                    <div class="col-lg-1"></div>
                                    <div class="col-lg-9">
                                        <textarea id="mydiv" class="form-control field numeric border-secondary scroll-bar textarea-scroll" readonly><%=dtoList.get(0).getDivision_Remark()%></textarea>
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
                                    <button class="btn btn-info" type="button" id="cmdMinistry" onclick="checkDetails('forward')" name="yesno"> <i class="fa fa-forward"></i> Forward To Ministry</button>
                                    <button class="btn btn-info" type="button" id="pm" onclick="checkDetails('forwardpm'); " name="yesno"> <i class="fa fa-forward"></i> Forward To PM</button>
                                    <button class="btn btn-default active" type="button" id="cmdPrint" style="width: 200px" onclick="my_print()"><i class="fa fa-print"></i> Print</button>
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
                                <div id="id1" >
                                    <label class="col-lg-3 control-label"><b>Supporting Documents(if any):</b></label>
                                   <div class="col-lg-1"></div>
                                    <div class="col-lg-9">
                                        <%--<input type="hidden" class="alert badge-primary"  name='attachedFileType' value="publicAttachment">--%>
                                            <input type="file"  name="fileName" id="filesd" class="btn btn-primary" accept="image/jpeg,image/png,.doc,.docx,.pdf,.xlsx,.xls">
                                    </div>
                                </div>
                                <div class="mt-2 col-md-12"></div>
                                <div id="trSubmitButton">
                                    <div class="col-lg-3"></div>
                                    <div class="col-lg-9">
                                        <button type="button" class="btn btn-primary" id="close_submit" onclick="closed_submit()"><i class="fa fa-floppy-o"></i> Submit </button>
                                        <a type="button" class="btn btn-primary" href="<c:url value='/pmoLogin/taskList'/>"> <i class="glyphicon glyphicon-arrow-left"></i> Back </a>
                                        <!--<input type="button" class="btn btn-primary" value="Submit" id="cmdMinistrySubmit" onclick="fwdSubmit()"></input>-->
                                    </div>
                                </div>
                            </div>

                            <div class="md-form form-group row" id="ifYes" style="display:none">
                                <div>
                                    <label class="col-lg-3 control-label" ><strong>Enter your Remarks:<span style="color:red;font-weight: bold">*</span></strong></label>
                                   <div class="col-lg-1"></div>
                                    <div class="col-lg-9">
                                        <textarea name="pMO_Remark" id="pmoRemarks" style="height: 70px" class="form-control"></textarea>
                                        <span class="text-danger" id="forward_error"></span>
                                    </div>
                                </div>
                                <div class="mt-2 col-md-12"></div>
                                <div >
                                    <label class="col-lg-3 control-label"><strong>Supporting Documents(if any): </strong></label>
                                    <div class="col-lg-1"></div>
                                    <div class="col-lg-9">
                                        <input type="file" name="fil" class="btn btn-primary" accept="image/jpeg,image/png,.doc,.docx,.pdf,.xlsx,.xls">
                                    </div>
                                </div>
                                <div class="mt-2 col-md-12"></div>
                            <div id="trMinistrySelect">
                                <label class="col-lg-3 control-label"><strong>Select Agency<span style="color:red;font-weight: bold">*</span></strong></label>
                                <div class="col-lg-6">
                                    <select name="ministry_Id" class="form-control" id="select_cat">
                                        <option value="0" id="select">Select Ministry:</option>
                                        <c:forEach var="clist" items="${ministries}" varStatus="counter">
                                            <option id="aaa" value="${clist.ministry_Id}">${clist.ministryName}${clist.ministry_Short_Desc}</option>
                                        </c:forEach>
                                    </select>
                                    <span id="selectErrorMsg" class="text-danger"></span>
                                </div>
                            </div>

                                <div class="mt-2 col-md-12"></div>
                                <div  >
                                     <div class="col-lg-3"></div>
                                    <div class="col-lg-9">
                                        <button type="button" class="btn btn-primary"  onclick="forward_submit()"><i class="fa fa-floppy-o"></i> Submit </button>
                                        <a type="button" class="btn btn-primary" href="<c:url value='/pmoLogin/taskList'/>"> <i class="glyphicon glyphicon-arrow-left"></i> Back </a>
                                        <!--<input type="button" class="btn btn-primary" value="Submit" id="cmdMinistrySubmit" onclick="fwdSubmit()"></input>-->
                                    </div>
                                </div>
                            </div>

                            <div class="md-form form-group row" id="what_if" style="display:none">
                                <div>
                                    <label class="col-lg-3 control-label"><strong>Enter your Remarks:<span style="color:red;font-weight: bold">*</span></strong></label>
                                    <div class="col-lg-1"></div>
                                    <div class="col-lg-9">
                                        <textarea class="form-control field border-secondary" name="pMO_Remark" id="pmremark"></textarea>
                                        <span class="text-danger" id="pmremarks"></span>
                                    </div>
                                </div>
                                <div class="mt-2 col-md-12"></div>
                                <div>
                                    <label class="col-lg-3 control-label"><strong>Supporting Documents(if any):</strong></label>
                                    <div class="col-lg-1"></div>
                                    <div class="col-lg-9">
                                        <input type="file" name="filler" class="btn btn-primary" accept="image/jpeg,image/png,.doc,.docx,.pdf,.xlsx,.xls">
                                    </div>
                                </div>
                                <div class="mt-2 col-md-12"></div>
                                <div>
                                    <div class="col-lg-3"></div>
                                    <div class="col-lg-9">
                                        <button type="button" class="btn btn-primary" id="pm_forward" onclick="pmo_submit()"><i class="fa fa-floppy-o"></i> Submit </button>
                                        <a type="button" class="btn btn-primary" href="<c:url value='/pmoLogin/taskList'/>"> <i class="glyphicon glyphicon-arrow-left"></i> Back </a>
                                        <!--<input type="button" class="btn btn-primary" value="Submit" id="cmdMinistrySubmit" onclick="fwdSubmit()"></input>-->
                                    </div>
                                </div>
                            </div>

                        </div>
        </div>
        </form>
</div>

<script>

    /*if($('#currentStatus').val()=='PM_Return_PMO'){
        $('#clRemarks').show();
    }*/


    if($('#currentStatus').val()=='PM_Return_PMO'&&$('#previousStats').val()=='Pmo_forward_to_Pm'){
        var d_divRemarks=$('#divRemarks');
        var d_pmo_remarks= $('#clRemarks');
        var td_divRemarks=d_divRemarks.clone();
        var td_pmo_remarks=d_pmo_remarks.clone();
        d_divRemarks.replaceWith(td_pmo_remarks);
        d_pmo_remarks.replaceWith(td_divRemarks);
        if($('#mydiv').val()!='null'||$('#pmid').val()!='null') {
            d_divRemarks.show();
            d_pmo_remarks.show();
        }
       /* if($('#pmoid').val()!='null'){
            $('#cRemarks').show();
        }
        if($('#idss').val()!='null') {
            $('#check').show();
        }

        if($('#mid').val()!='null') {
            $('#mRemarks').show();
        }*/
    }

    if($('#currentStatus').val()=='Ministry_PMO_Returns'&&($('#previousStats').val()=='Pmo_forward'||$('#previousStats').val()=='Department_Ministry_Return')){
        var dt_divRemarks=$('#divRemarks');
        var dt_pmo_remarks= $('#mRemarks');
        var tdt_divRemarks=dt_divRemarks.clone();
        var tdt_pmo_remarks=dt_pmo_remarks.clone();
        dt_divRemarks.replaceWith(tdt_pmo_remarks);
        dt_pmo_remarks.replaceWith(tdt_divRemarks);
        if($('#mydiv').val()!='null'||$('#mid').val()!='null') {
            dt_divRemarks.show();
            dt_pmo_remarks.show();
        }

       /* if($('#pmid').val()!='null'){
            $('#clRemarks').show();
        }
        if($('#pmoid').val()!='null'){
            $('#cRemarks').show();
        }
        if($('#idss').val()!='null') {
            $('#check').show();
        }*/
    }


    if($('#currentStatus').val()=='PM_Return_PMO'||$('#currentStatus').val()=='Ministry_PMO_Returns'){
        if($('#pmid').val()!='null'){
            $('#clRemarks').show();
        }
        if($('#pmoid').val()!='null'){
            $('#cRemarks').show();
        }
        if($('#idss').val()!='null') {
            $('#check').show();
        }
        if($('#mydiv').val()!='null'){
            $('#divRemarks').show();
        }
      if($('#mid').val()!='null') {
          $('#mRemarks').show();
      }
    }

    function my_print(){
        window.print();
    }

   function closed_submit(){
       var filesd=$('#filesd').val();
       var idPMORemarks=$('#idPMORemarks').val();
       var idApplicationNo=$('#idApplicationNo').val();
       var applicationForm =$("#approvetask");
       var doc_type="pmo_closedAttachment";
       var url='${pageContext.request.contextPath}/pmoLogin/submitPMOClose?document_type='+doc_type;
       if(c_call()){
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


   function forward_submit(){
       if(f_call()){
           var pmoRemarks=$('#pmoRemarks').val();
          var applicationForm =$("#approvetask");
           var doc_type = 'PMO to Ministry';
           var  url='${pageContext.request.contextPath}/pmoLogin/submitPMOForward?document_type='+doc_type;
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



   function forward_cancel(){
       document.getElementById("ifYes").reset();
   }
   function closed_cancel(){
       document.getElementById("ifNo").reset();
   }


   function checkDetails(type){
       if(type=='close'){
           $('#ifNo').show();
           $('#ifYes').hide();
           $('#what_if').hide();
       } else if(type=='forward'){
           $('#ifYes').show();
           $('#ifNo').hide();
           $('#what_if').hide();
       }
       else{
           $('#what_if').show();
           $('#ifYes').hide();
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

   function p_call(){
       var returnVal=true;
       var pmremark=$('#pmremark').val();
       if(pmremark==''){
           $('#pmremarks').addClass('error');
           $('#pmremarks').html('Please Enter Remarks').show();
           returnVal=false;
       }
       return returnVal;
   }

   function f_call(){
       var returnVal=true;
       var pmoRemarks=$('#pmoRemarks').val();
       var select_cat=$('#select_cat').val();
       if(pmoRemarks==''){
           $('#pmoRemarks').addClass('error');
           $('#forward_error').html('Please Enter Remarks').show();
           returnVal = false;
       }
       if(select_cat==0){
           $('#select_cat').addClass('error');
           $('#selectErrorMsg').html('Please Select Category').show();
           returnVal=false;
       }
       if(select_cat==22){
           select_cat.hide();
           alert(select_cat);
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
   $('#pmremark').on("keyup blur", function() {
       $('#pmremark').on('keyup blur', function () {
           var pmremark = $(this).val();
           if (pmremark!='') {
               $('#pmremarks').removeClass('error');
               $('#pmremarks').text('');
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
        var url='${pageContext.request.contextPath}/pmoLogin/fetchDocuments?appNo='+appNo;
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
                    "<td><a href='${pageContext.request.contextPath}/pmoLogin/donwloadFiles?uuid="+res[i].uuid +"'' target='_blank'> View </a></td>" +
                    "</tr>";
                }
                $('#publicAttTblId').find('tbody').append(docBtl);
            } else{
                    $('#apple').hide();
                    $('#publicAttTblId').hide();
            }
            }
        });
    });

</script>

</body>
</html>