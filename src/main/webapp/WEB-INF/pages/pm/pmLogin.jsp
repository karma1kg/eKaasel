<%@ page import="bt.gov.voice.dto.ApplicationDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="bt.gov.voice.dto.WorkFlowDTO" %>
<%--
  Created by Karma Gayleg.
  User: PMO
  Date: 6/23/2021
  Time: 12:37 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <meta name="decorator" content="/layout/pm-layout.jsp">
</head>
<body>

<% List<WorkFlowDTO> tasklist = (List<WorkFlowDTO>) request.getAttribute("tasklist"); %>
<% List<WorkFlowDTO> closed_ones = (List<WorkFlowDTO>) request.getAttribute("closed_ones"); %>

<div class="panel panel-default">
    <div class="panel-body">
        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper" style="margin-left: 0">
            <!-- Content Header (Page header) -->
            <div class="content-header">
                <div class="container-fluid">
                    <div class="row mb-2">
                        <div class="col-sm-6">
                            <h1 class="m-0"  style="color: #000000">Dashboard</h1>
                        </div><!-- /.col -->
                        <div class="col-sm-6">
                            <ol class="breadcrumb float-sm-right">
                                <li class="breadcrumb-item"><a style="color:#057BF9";href="<c:url value='/pmoLogin'/>">Home</a></li>
                                <li class="breadcrumb-item active"  style="color: #000000">Dashboard </li>
                            </ol>
                        </div><!-- /.col -->
                    </div><!-- /.row -->
                </div><!-- /.container-fluid -->
            </div>
            <!-- /.content-header -->

            <!-- Main content -->
            <section class="content">
                <div class="container container-fluid">
                    <!-- Small boxes (Stat box) -->
                    <div class="row">
                        <div class="col-lg-3 col-6">
                            <!-- small box -->
                            <div class="rounded bg-info">
                                <div class="inner">
                                    <h3 style="color:#ffffff;padding-left: 10px"><%=tasklist.size()%></h3>
                                    <p style="font-size: 14px"><h4 style="color:#ffffff;padding-left: 10px">New Grievances</h4></p>
                                </div>
                                <div class="icon">
                                    <i class="ion ion-bag"></i>
                                </div>
                                <a href="<c:url value='/pmLogin/taskList'/>" class="small-box-footer"><h3 style="color:#ffffff;padding-left: 10px">More info <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></h3></a>
                            </div>
                        </div>
                        <!-- ./col -->
                        <div class="col-lg-3 col-6">
                            <!-- small box -->
                            <div class="rounded bg-success">
                                <div class="inner">
                                    <h3 style="color:#000000;padding-left: 10px">${task}<%--<sup style="font-size: 20px"></sup>--%></h3>

                                    <p style="font-size: 14px"><h4 style="color:#000000;padding-left: 10px">Delivered</h4></p>
                                </div>
                                <div class="icon">
                                    <i class="ion ion-stats-bars"></i>
                                </div>
                                <a href="<c:url value='/pmLogin/delivered'/>" class="small-box-footer"><h3 style="color:#000000;padding-left: 10px">More info <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></h3></a>
                            </div>
                        </div>
                        <!-- ./col -->
                        <div class="col-lg-3 col-6">
                            <!-- small box -->
                            <div class="rounded bg-warning">
                                <div class="inner">
                                    <h3 style="color:#000000;padding-left: 10px">${forwards}</h3>
                                    <p style="font-size: 14px"><h4 style="color:#000000;padding-left: 10px">In Process</h4></p>
                                </div>
                                <div class="icon">
                                    <i class="icon ion-person-add"></i>
                                </div>
                                <a href="<c:url value='/pmLogin/forwarded'/>" class="small-box-footer"><h3 style="color:#000000;padding-left: 10px">More info <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></h3></a>
                            </div>
                        </div>
                        <!-- ./col -->
                        <div class="col-lg-3 col-6">
                            <!-- small box -->
                            <div class="rounded bg-danger">
                                <div class="inner">
                                    <h3 style="color:#ffffff;padding-left: 10px"><%=closed_ones.size()%></h3>

                                    <p style="font-size: 14px"><h4 style="color:#ffffff;padding-left: 10px">Closed</h4></p>
                                </div>
                                <div class="icon">
                                    <i class="ion ion-pie-graph"></i>
                                </div>
                                <a href="<c:url value='/pmLogin/closed'/>" class="small-box-footer"><h3 style="color:#ffffff;padding-left: 10px">More info <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></h3></a>
                            </div>
                        </div>
                        <!-- ./col -->
                    </div>
                </div>
            </section>
        </div><!-- /.container-fluid -->

        <!-- /.content -->
    </div>
</div>
</body>

</html>
