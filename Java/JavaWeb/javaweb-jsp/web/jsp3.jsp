
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%--@include会将两个页面合二为一  不建议--%>
    <%@include file="common/header.jsp"%>
    <h1>网页主体</h1>
    <%@include file="common/footer.jsp"%>

    <hr>

    <%--jsp标签   建议使用
    jsp:include：拼接页面，本质还是三个
    --%>
    <jsp:include page="common/header.jsp"/>
    <h1>网页主体</h1>
    <jsp:include page="common/footer.jsp"/>

</body>
</html>
