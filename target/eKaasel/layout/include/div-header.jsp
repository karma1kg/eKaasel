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
                     style="height:100px;width:100px">
            </a>
            <div class="header-panel">
                <h3 class="text-white organization-name">Government to Citizen Service Delivery Initiative</h3>
                <h4 class="text-white organization-name">Prime Ministers Office's Grievance Cell</h4>
            </div>
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
                    <%}else{%>   <%}%>
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
					<span class="pull-right" style="padding-top: 5px;">
						<ul class="nav">
                            <li>
                                <div class="dropdown">
                                    <button class="btn btn-primary dropdown-toggle user-info" type="button" data-toggle="dropdown">
                                        <i class="fa fa-user fa-lg"></i>&nbsp;
                                        <span>Welcome Division,</span>
                                    </button>
                                    <ul class="dropdown-menu" style="text-align: left; min-width: 100%">
                                        <li><a target="_blank"  href="${pageContext.request.contextPath}/resources/images/file.pdf" data-toggle="modal"><i class="fa fa-info-circle fa-fw"></i> Help</a></li>
                                        <li class="divider"></li>
                                        <li><a href="<c:url value='/login/logout'/>"><i class="fa fa-power-off fa-fw"></i> Logout</a></li>
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

    <section class="container menu-section">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span><font color='white'>Menu</font></span>
                </button>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="navbar-collapse collapse ">
                        <ul id="menu-top" class="nav navbar-right">
                            <li><a style="color: #011ED3; font-size: large"; href="<c:url value='/divLogin'/>" onclick="fwdDashboard()"><i style="color: #011ED3" class="fa fa-home fa-fw"></i> Dashboard </a></li>
                            <li><a style="color: #011ED3; font-size: large"; href="<c:url value='/divLogin/usergrievance'/>" onclick="fwdCreateApplication()"><i class="fa fa-home fa-fw" style="color: #011ED3"></i> Lodge your Grievance</a></li>
                            <li><a style="color: #011ED3; font-size: large"; href="<c:url value='/divLogin/grievance_status'/>" onclick="fwdCheckStatus()"><i class="fa fa-file-text fa-fw" style="color: #011ED3"></i> Check Grievance Status</a></li>

                            <li><a style="color: #011ED3; font-size: large"; href="<c:url value='/divLogin/taskList'/>" ><i class="fa fa-tasks fa-fw" style="color: #011ED3"></i> Back To TaskList</a></li>

                            <li><a style="color: #011ED3; font-size: large"; href="<c:url value='/divLogin/generate_report'/>" onclick="fwdViewReport()"><i class="fa fa-bar-chart" style="color: #011ED3"></i> View Report</a></li>
                        </ul>
                    </div>
                </div>

            </div>
        </div>
    </section>
</div>



