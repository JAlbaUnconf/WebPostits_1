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
        h2{
            font-width: bold;
            font-size: 120%;
        }
        p{
            font-size:100%;
            font-weight:normal;
        }
        .author {
            margin-top: 2em;
            font-style: italic;
        }
        .text {
            margin-top: 1em;
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
  -moz-box-shadow:5px 5px 7px rgba(33,33,33,1);
  -webkit-box-shadow: 5px 5px 7px rgba(33,33,33,.7);
  box-shadow: 5px 5px 7px rgba(33,33,33,.7);
  -moz-transition:-moz-transform .15s linear;
  -o-transition:-o-transform .15s linear;
  -webkit-transition:-webkit-transform .15s linear;
}
ul li{
  margin:1em;
  float:left;
}
ul li h2{
  font-size:140%;
  font-weight:bold;
  padding-bottom:10px;
}
ul li p{
  font-family:"Reenie Beanie",arial,sans-serif;
}
ul li a{
  -webkit-transform: rotate(-6deg);
  -o-transform: rotate(-6deg);
  -moz-transform:rotate(-6deg);
}
ul li:nth-child(even) a{
  -o-transform:rotate(4deg);
  -webkit-transform:rotate(4deg);
  -moz-transform:rotate(4deg);
  position:relative;
  top:5px;
  background:#cfc;
}
ul li:nth-child(3n) a{
  -o-transform:rotate(-3deg);
  -webkit-transform:rotate(-3deg);
  -moz-transform:rotate(-3deg);
  position:relative;
  top:-5px;
  background:#ccf;
}
ul li:nth-child(5n) a{
  -o-transform:rotate(5deg);
  -webkit-transform:rotate(5deg);
  -moz-transform:rotate(5deg);
  position:relative;
  top:-10px;
}
ul li a:hover,ul li a:focus{
  box-shadow:10px 10px 7px rgba(0,0,0,.7);
  -moz-box-shadow:10px 10px 7px rgba(0,0,0,.7);
  -webkit-box-shadow: 10px 10px 7px rgba(0,0,0,.7);
  -webkit-transform: scale(1.25);
  -moz-transform: scale(1.25);
  -o-transform: scale(1.25);
  position:relative;
  z-index:5;
}
ol{text-align:center;}
ol li{display:inline;padding-right:1em;}
ol li a{color:#fff;}
    </style>
    <meta http-equiv="refresh" content="30">
    <title>Marketplace</title>
</head>
<body>
<a href="index.jsp" style="color:white">marketplace 1</a>
<ul>
    <c:forEach var="topic" items="${topic_repository}">
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
