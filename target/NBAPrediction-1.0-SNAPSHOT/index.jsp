<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Вход в систему</title>
</head>
<body>
<form action="controller" method="post"
      style="position: absolute; left: 50%; margin-left: -100px; text-align: center">
    <h3>NBA Prediction</h3>
    <input type="hidden" name="command" value="connected"/>
    <button type="submit">Вход в систему</button>
</form>
</body>
</html>