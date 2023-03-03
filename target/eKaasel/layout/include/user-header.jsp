<%@ page import="bt.gov.ditt.sso.client.SSOClientConstants" %>
<%@ page import="bt.gov.ditt.sso.client.dto.UserSessionDetailDTO" %>
<%--
  Created by IntelliJ IDEA.
  User: Sonam Tobgay
  Date: 05-Jun-18
  Time: 10:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%--<jsp:include page="/WEB-INF/pages/rpIFrame.jsp"/>--%>
<%UserSessionDetailDTO userSessionDetailDTO =(UserSessionDetailDTO)request.getSession().getAttribute(SSOClientConstants.SSO_SESSION_OBJ_KEY);%>
<div class="card">
        <div class="header pb-lg-0 pt-lg-0 py-4" style="background: #120f65;">
            <div class="d-flex">
            <a class="header-brand" href="<c:url value='#'/>">
                <img src="<c:url value="/resources/images/logo.png"/>" class="header-brand-img" alt="Royal Court of Justice"
                     style="height:85px;width:85px; margin-top: 10px; margin-bottom: 10px; margin-left: 10px; margin-right: 10px">
            </a>
            <div class="header-panel">
                <h3 class="text-white organization-name">Government to Citizen Service Delivery Initiative</h3>
                <h4 class="text-white organization-name">Prime Ministers Office's Grievance Cell</h4>
            </div>
            <div class="d-flex order-lg-2 ml-auto">
                <div class="dropdown">
                  <%--  <%if (userSessionDetailDTO!=null){%>
                    <%if(userSessionDetailDTO.getFirstName()!=null && !userSessionDetailDTO.getFirstName().equalsIgnoreCase("null")){%>
                    <a href="#" class="nav-link pr-0 leading-none pt-4" data-toggle="dropdown">
i
                    </a>
                    <% }%>
                    <%}else{%>   <%}%>--%>

                        <div class="row">
                            <div class="col-md-12">
					<span class="pull-right" style="padding-top: 5px;">
						<ul class="nav">
                            <li>



                                <div class="dropdown">
                                    <button class="btn btn-primary dropdown-toggle user-info" type="button" data-toggle="dropdown">
                                        <i class="fa fa-user fa-lg"></i>&nbsp;

                                        <span>Welcome<%--,<%=userSessionDetailDTO.getFirstName()%></span>
                                        <%if(userSessionDetailDTO.getMiddleName()==null && userSessionDetailDTO.getFirstName().equalsIgnoreCase("null")){%>
                                        <%=userSessionDetailDTO.getMiddleName().replaceAll("null","")%>
                                        <% }%>
<%if (userSessionDetailDTO.getMiddleName()!=null && !userSessionDetailDTO.getFirstName().equalsIgnoreCase("null")){%>
                                        <span><%=userSessionDetailDTO.getMiddleName()%></span>
                                        <%}%>


                                        <%if(userSessionDetailDTO.getLastName()==null && userSessionDetailDTO.getLastName().equalsIgnoreCase("null")){%>
                                        <%=userSessionDetailDTO.getLastName().replaceAll("null","")%>
                                <% }%>

                                 <%if(userSessionDetailDTO.getLastName()==null && userSessionDetailDTO.getFirstName().equalsIgnoreCase("null")){%>
                                 <%=userSessionDetailDTO.getLastName().replaceAll("null","")%>
                                <% }%>
                                        <%if (userSessionDetailDTO.getLastName()!=null && !userSessionDetailDTO.getLastName().equalsIgnoreCase("null")){%>
                                        <span><%=userSessionDetailDTO.getLastName()%></span>
                                        <%}%>--%> </span>
                                    </button>
                                    <ul class="dropdown-menu" style="text-align: left; min-width: 100%">
                                        <li><a target="_blank"  href="${pageContext.request.contextPath}/resources/images/file.pdf" data-toggle="modal"><i class="fa fa-info-circle fa-fw"></i> Help</a></li>
                                        <li class="divider"></li>


                                       <%-- <li><a href="<%= userSessionDetailDTO.getOAuth2Client().getLogoutEndpoint()+"?post_logout_redirect_uri="+userSessionDetailDTO.getOAuth2Client().getLogoutCallback()
                                +"&id_token_hint="+userSessionDetailDTO.getIdToken()%>">
                                            <i class="dropdown-icon fe fe-log-out"></i>Log out </a></li>--%>


                                    </ul>
                                </div>
                            </li>
                        </ul>
					</span>
                            </div>
                        </div>

                </div>
            </div>
        </div>
    </div>
</div>
