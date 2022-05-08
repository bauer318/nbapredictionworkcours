<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Edit user</title>
    <style>
        dialog
        {
            background: rgba(255, 255, 255, 0.7);
            width: 300px;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.5);
            border-radius: 5px;
        }
        body{
            width: 350px;
            height: 57%;
            background-color:rgb(37,75,75);
            margin: 0 auto;
        }
        form{
            background-color: aqua;
            height: 100%;
            margin-top: 5%;

        }
        input{
            display: block;
            margin-left: 0;
            width: 95%;
            margin-left: 2%;
            margin-right: 2%;
        }
        label{
            margin-left: 2%;
        }
        button{
            margin-left: 13%;
            margin-right: 13%;
        }
        h4{
            text-align: center;
        }
    </style>
</head>
<body>
<dialog>
    <form>
        <input type="hidden" name="command" value="editUser"/>
        <h4>Роль Действие</h4>
        <label for="login">Логин </label><br/>
        <input id = "login"name="login" type="text" required/><br/>
        <label for="password">Пароль </label><br/>
        <input id ="password" name="password" type="text" required/><br/>
        <label for="firstName">Имя</label><br/>
        <input id="firstName" name="firstName" type="text" required/><br/>
        <label for="lastName">Фамилия</label><br/>
        <input id="lastName" name="lastName" type="text" required/><br/>
        <label for="email">Электроная почта</label><br/>
        <input id="email" name="email" type="email" required/><br/>
        <button type="submit" id="save">Изменить</button>
        <button type="button" id="cancel">Отмена</button><br/>
    </form>
</dialog>
</body>
</html>
