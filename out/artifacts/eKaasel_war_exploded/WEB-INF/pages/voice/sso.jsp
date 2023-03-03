<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Ekasel_sso</title>
    <meta name="decorator" content="/layout/open-layout.jsp"/>
    <link >
</head>
<body>


<div class="row" id="registration">
    <div class="col-lg-12">
        <form class="card form-horizontal" id="affirmationMarriageCertificateFormId"
              action="<c:url value='/'/>" method="post" enctype="multipart/form-data">
            <div class="card-body">
                <div class=" container-fluid row">
                    <div class="card-body">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="panel-body">
                                    <div class="form-group row">
                                        <section id="cover" class="min-vh-100">
                                            <div id="cover-caption">
                                                <div class="container">
                                                    <div class="row text-black-50">
                                                        <div class="col-xl-5 col-lg-6 col-md-8 col-sm-10 mx-auto text-center p-4">
                                                            <h1 class="display-4 py-2 text-bold">Sign In</h1>
                                                            <div class="form-group">
                                                                <input type="text" class="form-control" placeholder="Citizen ID" maxlength="11"/>
                                                            </div>
                                                            <div class="form-group row">
                                                                <input type="password" class="form-control" placeholder="Password">
                                                            </div>
                                                            <div class="row">
                                                                <input type="checkbox" name="remember_me" id="remember_me" class="" />
                                                                <label for="remember_me">&nbsp;&nbsp;&nbsp;&nbsp;
                                                                    Remember Me On this Device</label>
                                                            </div>
                                                            <div class="form-group row">
                                                                <input type="button" class="form-control btn-primary"  value="Sign In">
                                                            </div>
                                                            <div class="row">
                                                                <p>

                                                                    <a href="#"> Forgot Password</a></p>
                                                            </div>
                                                            <div class="row">
                                                                <p>Don't have an account?
                                                                    <a href="#"> Register Here</a></p>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </section>

                                    </div>
                                </div> <br/>
                        </div>
                    </div>
                </div>
            </div>
       </div> </form> </div>
</div>


</body>
</html>