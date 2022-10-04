<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

  <%--JSP表达式
  作用：用来将程序输出，输出到客户端
  <%= 变量或者表达式%>>
  --%>
  <%= new java.util.Date()%>

  <hr>

  <%--jsp脚本片段--%>
  <%
    int sum = 0;
    for (int i = 0; i <= 100 ; i++) {
      sum+=i;
    }
    out.println("<h1>Sum="+sum+"</h1>");
  %>

  <%
    int x=10;
    out.println(x);
  %>
  <p>这是一个jsp文档</p>
  <%
    int y =2;
    out.println(y);
  %>

  <hr>
  
  <%--在代码嵌入HTML元素--%>
  <%
    for (int i = 0; i < 5; i++) {
  %>
    <h1>Hello,world <%=i%> </h1>
  <%
    }
  %>

  <hr>

  <%--JSP声明--%>
  <%!
    static {
    System.out.println("Loading Servlet!");
    }
    private int globalvav = 0;

    public void kuang(){
      System.out.println("进入了方法kuang");
    }
  %>


  </body>
</html>
