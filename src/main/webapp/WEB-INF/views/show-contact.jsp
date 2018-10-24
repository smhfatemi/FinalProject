<%@ page import="ir.maktab.contactphone.model.contact.Contact" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Unknown
  Date: 10/21/2018
  Time: 11:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
gvjvjjjjjjjjjjjjjjjjjjjjjjjjj
<p>
${msg.intValue()}
</p>
<%
    Contact list = (Contact) request.getAttribute("msg");

    out.println(list);

%>
</body>
</html>
