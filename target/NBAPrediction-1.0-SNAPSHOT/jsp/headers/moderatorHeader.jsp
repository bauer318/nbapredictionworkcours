<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title></title>
    <style>
        <c:import url="/css/menuHeader.css"/>
    </style>
</head>
<body>
<div class="headerMenu">
    <header class="menu">
        <form id="addMatchMenuForm" method="POST" action="controller">
            <input type="hidden" name="command" value="menuAddMatch">
        </form>
        <form id="updatePointsMenuForm" method="POST" action="controller">
            <input type="hidden" name="command" value="menuUpdatePoints">
        </form>
        <form id="rantingMenuForm" method="POST" action="controller">
            <input type="hidden" name="command" value="menuRanting">
        </form>
        <form id="predicitonMenuForm" method="POST" action="controller">
            <input type="hidden" name="command" value="menuPrediction">
        </form>
        <form id="logoutForm" method="POST" action="controller">
            <input type="hidden" name="command" value="logout">
        </form>
        <div id="menuDiv">
            <div class="menuButtonDiv"><button type="submit" form="addMatchMenuForm">Добавить матч</button></div>
            <div class="menuButtonDiv"><button type="submit" form="updatePointsMenuForm">Обновлять очки</button></div>
            <div class="menuButtonDiv"><button type="submit" form="rantingMenuForm">Рейтинг</button></div>
            <div class="menuButtonDiv"><button type="submit" form="predicitonMenuForm">Прогноз матчей</button></div>
            <div class="menuButtonDiv"><button type="submit" form="logoutForm">Выход</button></div>
        </div>
    </header>
</div>
</body>
</html>