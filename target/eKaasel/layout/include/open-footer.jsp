<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<footer class="page-footer-auto mb-0 fixed-bottom text-center" style=" background: #1b1d29; " >
    <div class="container">
        <div class="row align-items-center flex-row-reverse">
            <div class="col-12 col-lg-auto mt-3 mt-lg-0 text-center" style="color: #ffffff;height:50px;">
                Copyright &copy;
                <%
                    String currentDate = new SimpleDateFormat("yyyy").format(new Date());
                    out.print(currentDate);
                %>
                G2C Project Office | OFFICE OF THE PRIME MINISTER.
            </div>
        </div>
    </div>
</footer>