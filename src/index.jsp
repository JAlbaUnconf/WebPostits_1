<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel = "stylesheet"
          type = "text/css"
          href = "style/style.css" />
    <meta http-equiv="refresh" content="30">
    <title>Marketplace</title>
</head>
<body>
<ul>
    <c:forEach var="topic" items="<%=scot.jalba.PostitsServlet.getTopicData()%>">
        <li>
            <a href="#">
                <c:forEach  var="topicElement" items="title,text,author">
                    <c:if test="${topicElement == 'title'}">
                        <h2><c:out value="${topic.get(topicElement)}"/></h2>
                    </c:if>
                    <c:if test="${topicElement == 'text'}">
                        <p class="text"><c:out value="${topic.get(topicElement)}"/></p>
                    </c:if>
                    <c:if test="${topicElement == 'author'}">
                        <p class="author"><c:out value="${topic.get(topicElement)}"/></p>
                    </c:if>
                </c:forEach>
            </a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
