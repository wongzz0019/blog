
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%-- /servlet/login 要和web.xml注册的servlet一致--%>
<form action="/servlet/login" method="post">
    <input type="text" name="username">
    <input type="submit">
</form>

</body>
</html>
