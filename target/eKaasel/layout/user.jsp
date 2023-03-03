<%--<%@ page import="bt.gov.ditt.sso.client.dto.UserSessionDetailDTO" %>
<%@ page import="bt.gov.ditt.sso.client.SSOClientConstants" %>--%>
<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%@page session="false" %>
<html>
<head>
    <title><sitemesh:write property="title"/></title>
    <link rel="shortcut icon" class="fa fa-leaf" alt="public_grievance" style="height:100px;width:100px" href="<c:url value='/resources/images/logo.png' />"
    <jsp:include page="include/css.jsp"/>
    <jsp:include page="include/js.jsp"/>
    <jsp:include page="include/user-header.jsp"/>
    <jsp:include page="include/open-footer.jsp"/>
    <%-- <jsp:include page="include/open-menu.jsp"/>--%>

</head>
<body>

<div class="card card-body" id="openLayout">
        <sitemesh:write property="body"/>
            <sitemesh:write property="footer"/>

    </div>


</body>
</html>
