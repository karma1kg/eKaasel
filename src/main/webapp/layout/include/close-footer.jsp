<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<footer class="footer" style=" background: #1b1d29;">
    <div class="container">
        <div class="row align-items-center flex-row-reverse">
            <div class="col-auto ml-lg-auto">
            </div>
            <div class="col-12 col-lg-auto mt-3 mt-lg-0 text-center">
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