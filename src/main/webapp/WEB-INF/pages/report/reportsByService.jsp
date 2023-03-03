<%--
  Created by IntelliJ IDEA.
  User: N-Zepa
  Date: 23-Jul-19
  Time: 9:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<meta name="decorator" content="/layout/close-layout.jsp"/>
<html>
<head>
    <title>Reports</title>
</head>
<body>
<div class="row">
    <span style="font-size: 20px"><b>&nbsp;&nbsp;&nbsp;&nbsp;Judicial Service </b> >> Statistical Report by Service</span>

    <div class="col-12">
        <form class="card form-horizontal" id="reportByServiceFormId" action="<c:url value='/reportsByService'/>" method="post">
            <div class="card-body">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-md-2">From Date :<span class="text-danger">*</span></label>

                                    <div class="col-md-4">
                                        <input type="text" class="form-control datepicker field" id="fromDate"
                                               name="fromDate">
                                        <span class="text-danger" id="fromDateErrorMsg"></span>
                                    </div>
                                    <label class="col-md-2">To Date :<span class="text-danger">*</span></label>

                                    <div class="col-md-4">
                                        <input type="text" class="form-control datepicker field" id="toDate"
                                               name="toDate">
                                        <span class="text-danger" id="toDateErrorMsg"></span>
                                    </div>
                                </div>
                                    <div class="form-group">
                                        <div class="col-sm-4 pull-right">
                                            <button type="button" id="btnSearchByService" class="btn btn-primary">
                                                <i class="fe fe-bar-chart">Generate</i></button>
                                        </div>
                                    </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 hidden" id="reportByServiceTableIdDiv">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <strong><h2>List of Applications</h2></strong>
                        <div class="pull-right" id="exportButtons"></div>
                        <div class="table-responsive">
                            <table class="table card-table table-vcenter text-nowrap table-bordered"
                                   id="reportsByServiceTableId">
                                <thead>
                                <tr>
                                    <th>Sl No.</th>
                                    <th>Court Name</th>
                                    <th>Total</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
