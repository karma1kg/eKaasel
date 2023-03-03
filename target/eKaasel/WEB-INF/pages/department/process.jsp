<%@ page import="bt.gov.voice.dto.WorkFlowDTO" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 8/14/2021
  Time: 1:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="decorator" content="/layout/dept-layout.jsp">
    <title>Ekaasel</title>
</head>
<body>

<div class="panel panel-info">
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
                        <th>
                            <b style="color: #0e0e0e;">Application Status</b>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <%List<WorkFlowDTO> workFlowDTOList = (List<WorkFlowDTO>) request.getAttribute("process");%>
                    <%for (int i =0; i < workFlowDTOList.size(); i++){%>
                    <tr>
                        <td><%=1+i%></td>
                        <td> <%=workFlowDTOList.get(i).getApplication_number()%>
                        </td>
                        <td><%=workFlowDTOList.get(i).getCurrent_status()%></td>
                        <td> <%=workFlowDTOList.get(i).getRecord_date()%></td>
                        <td> <%=workFlowDTOList.get(i).getAction_trail()%></td>
                    </tr>
                    <%}%>
                    </tbody>
                </table>
            </div>
            <div class="mt-2 col-md-12"></div>
            <a type="button" class="btn btn-primary" href="${pageContext.request.contextPath}/dept_login"> <i class="glyphicon glyphicon-arrow-left"></i> Go Back </a>
        </div>
    </div>
</div>


<script>
    $(document).ready(function () {
        $('#taskList').DataTable({
            responsive: true
        });
    });
</script>

</body>
</html>
