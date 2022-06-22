<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Add user</title>
    <script type="text/javascript">
        <c:import url="/js/admin.js"/>
    </script>
</head>
<body>
<c:import url="/jsp/headers/adminHeader.jsp"/>
<div class="centerDiv">
    <style>
        <c:import url="/css/adminIndex.css"/>
    </style>
    <div class="addUserForm">
        <form class="formAddUser" id="formAddUser" method="POST" action="controller">
            <input type="hidden" name="command" value="adduser"/>
            <p>
                <label>Роль пользователя:</label>
                <select id="selectRole" onChange="selectUserRole()" name="selectRole">
                    <option value="admin">Администратор</option>
                    <option value="moderator">Модератор</option>
                    <option value="bettor">Игрок</option>
                </select>
            </p>
            <p>
                <label>Логин:</label>
                <input type="text" name="userLogin" required="required" value="${loginUser}"/>
            </p>
            <p>
                <label>Пароль:</label>
                <input type="text" name="userPassword" required="required" value="${passwordUser}"/>
            </p>
            <div class="clientInfos">
                <p>
                    <label>Имя:</label>
                    <input type="text" name="userFirtsName" class="userFirstName" value="${firstnameUser}"/>
                </p>
                <p>
                    <label>Фамилия:</label>
                    <input type="text" name="userLastName" class="userLastName" value="${lastnameUser}"/>
                </p>
                <p>
                    <label>Электронная почта:</label>
                    <input type="text" name="userEmail" class="userEmail" value="${emailUser}"/>
                </p>
            </div>
            <p>
                <button type="submit">Добавить</button>
            </p>
            <p>${isLoginExist}</p>
        </form>
    </div>
    <div class="addUserList">
        <table class="tableListAddedUser">
            <tr class="tableTitle">
                <td>Логин</td>
                <td>Роль пользователя</td>
                <td>Статус авторизации</td>
            </tr>
            <c:forEach var="user" items = "${users }">
                <tr>
                    <td>${user.login }</td>
                    <c:if test="${user.idGroup==1 }">
                        <td>АДМИНИСТРАТОР</td>
                    </c:if>
                    <c:if test="${user.idGroup==2 }">
                        <td>МОДЕРАТОР</td>
                    </c:if>
                    <c:if test="${user.idGroup==3 }">
                        <td>ИГРОК</td>
                    </c:if>
                    <c:if test="${user.authorizationStatus==1 }">
                        <td>АВТОРИЗОВАН</td>
                    </c:if>
                    <c:if test="${user.authorizationStatus==0 }">
                        <td>НЕ АВТОРИЗОВАН</td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>