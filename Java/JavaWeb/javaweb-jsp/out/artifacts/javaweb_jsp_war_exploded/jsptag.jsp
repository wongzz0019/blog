<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


    <%--jsp:include--%>

    <jsp:forward page="jsptag2.jsp">
        <jsp:param name="name" value="huangzongzhao"/>
        <jsp:param name="age" value="18"/>
    </jsp:forward>

</body>
</html>
