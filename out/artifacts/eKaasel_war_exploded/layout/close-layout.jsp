<%@ page import="com.spring.mvc.auth.dto.Privilege" %>
<%@ page import="com.spring.mvc.auth.dto.Service" %>
<%@ page import="com.spring.mvc.auth.dto.UserRolePrivilegeDto" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String roleName = "";
    String userId = "";
    if (session.getAttribute("UserRolePrivilege") != null) {
        UserRolePrivilegeDto userBean = (UserRolePrivilegeDto) session.getAttribute("UserRolePrivilege");
        String LocationId="";
        if(userBean.getJurisdictions().length >=1){
            for (int n = 0; n < userBean.getJurisdictions().length; n++) {
                LocationId=userBean.getJurisdictions()[n].getLocationID();
            }
        }
        for (int m = 0; m < userBean.getRoles().length; m++) {
            for (int i = 0; i < userBean.getRoles()[m].getServices().length; i++) {
                Service svc = userBean.getCurrentRole().getServices()[i];
                for (int j = 0; j < svc.getPrivileges().length; j++) {
                    Privilege priv = svc.getPrivileges()[j];
                }
            }
        }
        roleName = userBean.getCurrentRole().getRoleName();
        userId=userBean.getUserType();
    }%>
<% UserRolePrivilegeDto userBean = (UserRolePrivilegeDto) session.getAttribute("UserRolePrivilege");%>
<html>
<head>
    <title><sitemesh:write property="title"/></title>
    <link rel="shortcut icon" class="fa fa-leaf" alt="Royal Court of Justice" style="height:100px;width:100px" href="<c:url value='/resources/images/logo1.png' />"
    <jsp:include page="include/css.jsp"/>
    <jsp:include page="include/js.jsp"/>
</head>
<jsp:include page="include/close-header.jsp"/>
<jsp:include page="include/close-menu.jsp"/>
<body class="">
<div class="my-3 my-md-5">
    <div class="container">
        <sitemesh:write property="body"/>
    </div>
</div>
<jsp:include page="include/close-footer.jsp"/>
<script type="text/javascript">
    function checkLanguage(evt,id){
        var charCode = (evt.which) ? evt.which : event.keyCode
        if ((charCode > 48 && charCode < 57) || (charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123))//0-9
        {
            $("#"+id).addClass('has-error');
            $("#"+id+"ErrorMsg").html('Please select Dzongkha Font.');
            setTimeout('$("#'+id+'").removeClass("has-error")',5000);
            $("#"+id+"ErrorMsg").show();
            setTimeout('$("#'+id+'ErrorMsg").hide()',5000);
            return false;
        }
        else{
            return true;
        }
    }
    function validateAttachment(vl,id){
        if(vl!=""){
            $('#'+id).prop('class', 'alert badge-info');
        }
        else{
            $('#'+id).prop('class', 'alert badge-danger');
        }
    }
</script>
</body>
</html>
