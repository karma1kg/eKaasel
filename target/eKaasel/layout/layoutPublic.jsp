<%@ page import="bt.gov.ditt.sso.client.SSOClientConstants" %>
<%@ page import="bt.gov.ditt.sso.client.dto.UserSessionDetailDTO" %>
<%--
  Created by IntelliJ IDEA.
  User: deepak
  Date: 5/16/19
  Time: 6:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title><sitemesh:write property='title'/></title>
    <sitemesh:write property='head'/>
    <jsp:include page="include/css.jsp"/>
    <jsp:include page="include/js.jsp"/>

</head>

<body>


<jsp:include page="/WEB-INF/pages/sso/rpIFrame.jsp"/>
<%UserSessionDetailDTO userSessionDetailDTO =(UserSessionDetailDTO)request.getSession().getAttribute(SSOClientConstants.SSO_SESSION_OBJ_KEY);%>
<div class="header py-4" style="background: #120f65;">
    <div class="container">
        <div class="d-flex">
            <a class="header-brand" href="javascript:void(0)" style="margin-top:10px;">
                <img src="<c:url value='/resources/images/logo1.png' />" class="header-brand-img" alt="tabler logo" style="height:68px; width:68px">
            </a>

            <h3 class="text-white"><br/>Government to Citizen Service Delivery Initiative</h3>
            <div class="d-flex order-lg-2 ml-auto">
                <div class="dropdown">
                    <%if (userSessionDetailDTO!=null){%>
                    <%if(userSessionDetailDTO.getFirstName()!=null && !userSessionDetailDTO.getFirstName().equalsIgnoreCase("null")){%>
                    <a href="#" class="nav-link pr-0 leading-none pt-4" data-toggle="dropdown">
                        <span class="ml-2 d-none d-lg-block">
                          <span class="text-white">
                              <i class="fas fa-sign-out-alt" style="font-size:25px;color:white">
                                  <%=userSessionDetailDTO.getFirstName().replaceAll("null", "")%>
                                  <%=userSessionDetailDTO.getMiddleName().replaceAll("null", "")%>
                                  <%=userSessionDetailDTO.getLastName().replaceAll("null","")%>
                              </i>
                          </span>
                        </span>
                    </a>
                    <% }%>
                    <%}else{%>
                    <a href="#" class="nav-link pr-0 leading-none pt-4" data-toggle="dropdown">
                        <i class="fa fa-sign-out" style="font-size:48px;color:red"></i>
                            <span class="ml-2 d-none d-lg-block">
                              <span class="text-white">karma Gayleg </span>
                            </span>
                    </a>
                    <%}%>
                    <div class="dropdown-menu dropdown-menu-right dropdown-menu-arrow">
                        <a class="dropdown-item" href="<%= userSessionDetailDTO.getOAuth2Client().getLogoutEndpoint()
                                +"?post_logout_redirect_uri="+userSessionDetailDTO.getOAuth2Client().getLogoutCallback()
                                +"&id_token_hint="+userSessionDetailDTO.getIdToken()%>">
                            <i class="dropdown-icon fe fe-log-out"></i>Log out
                        </a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<div class="col-12" style="margin-top:20px;">
    <div class="col-12">
        <div class="col-12" id="layoutPublic">
            <sitemesh:write property='body'/>
        </div>
    </div>
</div>
<jsp:include page="include/open-footer.jsp"></jsp:include>
</body>
<script>

    function validateAttach(vl, id, checkId) {
        if (vl != "") {
            $('#' + id).prop('class', 'alert badge-info');
            $('#' + checkId).prop('class', 'fa fa-check pl-2');
        }
        else {
            $('#' + id).prop('class', 'alert badge-danger');
            $('#' + checkId).prop('class', 'fa fa-times pl-2');
        }
    }

</script>
</html>
