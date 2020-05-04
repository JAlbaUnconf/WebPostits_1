<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        *{
            margin:0;
            padding:0;
        }
        body{
            font-family:arial,sans-serif;
            font-size:100%;
            margin:3em;
            background:#666;
            color:#fff;
        }
        h2,p{
            font-size:100%;
            font-weight:normal;
        }
        ul,li{
            list-style:none;
        }
        ul{
            overflow:hidden;
            padding:3em;
        }
        ul li a{
            text-decoration:none;
            color:#000;
            background:#ffc;
            display:block;
            height:20em;
            width:20em;
            padding:1em;
        }
        ul li{
            margin:1em;
            float:left;
        }
    </style>
    <meta http-equiv="refresh" content="5">
    <title>Marketplace</title>
</head>
<body>
<ul>
    <c:forEach  var="nam" items="<%=scot.jalba.PostitsServlet.getNames()%>">
        <li>
            <a href="#">
                <c:forEach  var="nal" items="${nam}">
                    <p><c:out value="${nal}"/></p>
                </c:forEach>
            </a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
