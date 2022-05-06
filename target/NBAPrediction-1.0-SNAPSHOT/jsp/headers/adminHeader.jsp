<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <form id="userListMenuForm" method="POST" action="controller">
            <input type="hidden" name="command" value="menuAddUser">
        </form>
        <form id="editUserMenuForm" method="POST" action="controller">
            <input type="hidden" name="command" value="menuEditUser">
        </form>
        <form id="logoutForm" method="POST" action="controller">
            <input type="hidden" name="command" value="logout">
        </form>
        <div id="menuDiv">
            <div class="menuButtonDiv"><button type="submit" form="userListMenuForm">Добавить пользователь</button></div>
            <div class="menuButtonDiv"><button type="submit" form="editUserMenuForm" >Актуализировать данные пользователей</button></div>
            <div class="menuButtonDiv"><button type="submit" form="logoutForm">Выход</button></div>
        </div>
    </header>
</div>
</body>
</html>