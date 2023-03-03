<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="bt.gov.voice.dto.WorkFlowDTO" %>
<%@ page import="java.util.List" %>
<%--
  Created by Karma Gayleg.
  User: USER
  Date: 6/24/2021
  Time: 10:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE>
<html>
<head>
    <title>eKaaSel</title>
    <meta name="decorator" content="/layout/ministry-layout.jsp">
</head>
<body>
<div class="panel panel-info">
    <div class="panel-heading">
        Sent by Prime Minister's Office
    </div>
    <div class="panel-body">
        <div class="form-group">
            <div class="table-responsive">
                <table class="table table-striped table-bordered table-hover" id=taskList>
                    <thead>
                    <tr>
                        <th>
                            <b style="color: #0e0e0e;">Sl No</b>
                        </th>
                        <th>
                            <b style="color: #0e0e0e;">Application No</b>
                        </th>
                        <th>
                            <b style="color: #0e0e0e;">Current Status</b>
                        </th>
                        <th>
                            <b style="color: #0e0e0e;">Submitted On</b>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <%List<WorkFlowDTO> workFlowDTOs = (List<WorkFlowDTO>) request.getAttribute("myTasklist");%>
                    <%for (int i =0; i < workFlowDTOs.size(); i++){%>
                    <tr>
                        <td><%=1+i%></td>
                        <td> <a href="#" onclick="task_list('<%=workFlowDTOs.get(i).getApplication_number()%>')" id="appNo"><%=workFlowDTOs.get(i).getApplication_number()%></a>
                            <%-- <%=workFlowDTOList.get(i).getApplication_number()%>--%>

                            <%--  <a href="#" id="appNo" onclick="viewApplicationDetails('<%=taskListDTOList.get(i).getAppNo()%>')" class="">
                                  <i class="text-center"><%=taskListDTOList.get(i).getAppNo()%></i>
                              <%}%--%>
                        </td>
                        <td><%=workFlowDTOs.get(i).getCurrent_status()%></td>
                        <td> <%=workFlowDTOs.get(i).getRecord_date()%></td>
                    </tr>
                    <%}%>
                    </tbody>
                </table>
            </div>
            <div class="mt-2 col-md-12"></div>
            <a type="button" class="btn btn-primary" href="${pageContext.request.contextPath}/ministryLogin"> <i class="glyphicon glyphicon-arrow-left"></i> Go Back </a>
        </div>
    </div>
</div>
<div class="panel panel-info">
    <div class="panel-heading">
        Sent by Department
    </div>
    <div class="panel-body">
        <div class="table-responsive" id="tblMinisterFwd">
            <table id="minister" class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                <tr>
                    <th>
                        <b style="color: #0e0e0e;">Sl No</b>
                    </th>
                    <th>
                        <b style="color: #0e0e0e;">Application No</b>
                    </th>
                   <th>
                    <b style="color: #0e0e0e;">Current Status</b>
                </th>
                    <th>
                        <b style="color: #0e0e0e;">Submitted On</b>
                    </th>

                </tr>
                </thead>
                <tbody>
                <%List<WorkFlowDTO> workFlowDTO1s = (List<WorkFlowDTO>) request.getAttribute("tasklists");%>
                <%for (int i =0; i < workFlowDTO1s.size(); i++){%>
                <tr>
                    <td><%=1+i%></td>
                    <td> <a href="#" onclick="task_list1('<%=workFlowDTO1s.get(i).getApplication_number()%>')" id="appNo">
                        <%=workFlowDTO1s.get(i).getApplication_number()%></a>
                        <%-- <%=workFlowDTOList.get(i).getApplication_number()%>--%>
                        <%--  <a href="#" id="appNo" onclick="viewApplicationDetails('<%=taskListDTOList.get(i).getAppNo()%>')" class="">
                              <i class="text-center"><%=taskListDTOList.get(i).getAppNo()%></i>
                          <%}%--%>
                    </td>
                    <td><%=workFlowDTO1s.get(i).getCurrent_status()%></td>
                    <td><%=workFlowDTO1s.get(i).getRecord_date()%></td>
                </tr>
                <%}%>
                </tbody>
            </table>
        </div>
        <div class="mt-2 col-md-12"></div>
        <a type="button" class="btn btn-primary" href="${pageContext.request.contextPath}/ministryLogin"> <i class="glyphicon glyphicon-arrow-left"></i> Go Back </a>
    </div>
    </div>

<script>
    function task_list(appNo){
        var url='${pageContext.request.contextPath}/ministryLogin/empty/getApplicationDetail?appNo='+appNo;
        $('#openLayout_min').load(url);
    }
    function task_list1(appNo){
        var url='${pageContext.request.contextPath}/ministryLogin/empty/getApplicationDetail?appNo='+appNo;
        $('#openLayout_min').load(url);
        //$('#openLayout_min').load(url);
    }
    $(document).ready(function () {
        $('#taskList').DataTable({
            responsive: true
        });
    });

    $(document).ready(function(){
        $('#minister').DataTable({
            responsive:true
        });
    });

    /*  function checkit(appNo){
     var url='${pageContext.request.contextPath}/pmoLogin/getApplicationDetail?appNo='+appNo;
     window.location=url;
     }*/


</script>
</body>
</html>