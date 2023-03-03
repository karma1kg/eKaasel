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
        <div class="header pb-lg-0 pt-lg-0 py-4" style="background: #120f65;">
            <div class="container">
                <div class="d-flex">
                    <a class="header-brand" href="<c:url value='/'/>">
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
                            <%--<a href="#" class="nav-link pr-0 leading-none pt-4" data-toggle="dropdown">
                                <i class="fa fa-sign-out" style="font-size:48px;color:red"></i>
                            <span class="ml-2 d-none d-lg-block">
                              <span class="text-white">Welcome Guest,
                               <select name="myid" class=" form-control" id="mm">

                               </select>
                              </span>
                            </span>
                            </a>--%>

                            <div class="container">
                                <div class="row">
                                    <div class="col-md-12">
					<span class="pull-right" style="padding-top: 5px;">
						<ul class="nav">
                            <li>
                                <div class="dropdown">
                                    <button class="btn btn-primary dropdown-toggle user-info" type="button" data-toggle="dropdown">
                                        <i class="fa fa-user fa-lg"></i>&nbsp;
                                        <span>Welcome</span>PMO User,
								   <span>PSGRD</span>
                                        <span class="caret"></span></button>
                                    <ul class="dropdown-menu" style="text-align: left; min-width: 100%">
                                        <li><a href="#myModal" data-toggle="modal"><i class="fa fa-info-circle fa-fw"></i> Help</a></li>
                                        <li><a href="#"><i class="fa fa-gears fa-fw"></i> Setting</a></li>

                                        <li><a href="/Pages/common/SelectRole.jsp"><i class="fa fa-gears fa-fw"></i> Change Role</a></li>

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
                            <!-- HEADER END-->

                            <div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 id="myModalLabel" class="modal-title"><i class="fa fa-info-circle fa-lg"></i> Help</h4>
                                        </div>
                                        <div class="modal-body">
                                            <p>If you need any help regarding eKaasel applicaiton, feel free to read the user manual here.
                                                <a class="btn-xs btn-link" target="_blank"  href="/resources/images/file.pdf"  class="avatar">
                                                    User Manual </a></p>
                                            </a>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- menu.jsp -->



                            <%--<div class="dropdown-menu dropdown-menu-right dropdown-menu-arrow">
                                <a class="dropdown-item" href="<%= userSessionDetailDTO.getOAuth2Client().getLogoutEndpoint()
                                +"?post_logout_redirect_uri="+userSessionDetailDTO.getOAuth2Client().getLogoutCallback()
                                +"&id_token_hint="+userSessionDetailDTO.getIdToken()%>">
                                <i class="dropdown-icon fe fe-log-out"></i>Log out
                                </a>
                            </div>--%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
