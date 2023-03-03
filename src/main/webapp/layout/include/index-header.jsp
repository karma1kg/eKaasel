<%@ page import="bt.gov.ditt.sso.client.SSOClientConstants" %>
<%@ page import="bt.gov.ditt.sso.client.dto.UserSessionDetailDTO" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>


<jsp:include page="/WEB-INF/pages/sso/rpIFrame.jsp"/>
<%UserSessionDetailDTO userSessionDetailDTO =(UserSessionDetailDTO)request.getSession().getAttribute(SSOClientConstants.SSO_SESSION_OBJ_KEY);%>
<header>
    <div class="header py-4" style="background: #120f65;">
        <div class="container">
        <div class="d-flex">
            <a class="header-brand" href="javascript:void(0)" style="margin-top:10px;">
                <img src="<c:url value='/resources/images/logo.png' />" class="header-brand-img" alt="tabler logo" style="height:68px; width:68px">
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
                    <%--<div class="dropdown-menu dropdown-menu-right dropdown-menu-arrow">--%>
                        <%--<a class="dropdown-item" href="<%= userSessionDetailDTO.getOAuth2Client().getLogoutEndpoint()--%>
                                <%--+"?post_logout_redirect_uri="+userSessionDetailDTO.getOAuth2Client().getLogoutCallback()--%>
                                <%--+"&id_token_hint="+userSessionDetailDTO.getIdToken()%>">--%>
                            <%--<i class="dropdown-icon fe fe-log-out"></i>Log out--%>
                        <%--</a>--%>
                    <%--</div>--%>
                </div>
            </div>

        </div>
    </div>
    </div>

</header>
<div class="clearfix"></div>








