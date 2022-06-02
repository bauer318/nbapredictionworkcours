<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title></title>
    <style>
        <c:import url="/css/menuHeader.css"/>
    </style>
</head>
<body>
<div class="headerMenu">
<header class="menu">
    <form id="predictionListForm" method="POST" action="controller">
        <input type="hidden" name="command" value="menuBettor">
    </form>
    <form id="logoutForm" method="POST" action="controller">
        <input type="hidden" name="command" value="logout">
    </form>
    <div id="menuDiv">
        <div class="menuButtonDiv"><button type="submit" form="predictionListForm">Прогноз</button></div>
        <div class="menuButtonDiv"><button type="submit" form="logoutForm">Выход</button></div>
    </div>
</header>
</div>
</body>
</html>
