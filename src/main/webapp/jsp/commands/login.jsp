<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Connection Page</title>
    <style>
        <%@include file="/css/login.css"%>
    </style>
</head>
<body>
<div class="formMain">
    <form id="connectionForm" method="POST" action="controller">
        <div class="formInterior">
            <input type="hidden" name="command" value="login"/>
            <p>
                <label for="login" class="loginLabel">Логин  </label>
                <input id="login" name="login" type="text" maxlength="25" placeholder="Введите ваш логин"/>
            </p>
            <p>
                <label for="password" class="passwordLabel">Пароль </label>
                <input type="password" id="password" name="password" maxlength="15" placeholder="Введите ваш пароль"/>
            </p>
            <p style="color:white;">${wrongloginpass}</p>
            <div class="submitDiv">
                <input type="submit" value="Вход"/>
            </div>
        </div>
    </form>
</div>
</body>
</html>