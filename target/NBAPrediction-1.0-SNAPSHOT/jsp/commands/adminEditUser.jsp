<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Connection Page</title>
    <style>
        <%@include file="/css/adminEditUser.css"%>
    </style>
    <script>
        <%@include file="/js/admin.js"%>
    </script>
</head>
<body>
<%@include file="/jsp/headers/adminHeader.jsp" %>
<div class="centerDiv">
    <dialog ${open}>
        <form id="dialogForm" action="controller" method="post">
            <input type="hidden" name="command" value="saveEditUser"/>
            <h4>Роль Действие</h4>
            <label for="login">Логин </label><br/>
            <input id = "login"name="login" type="text" value="${clonedUser.login}"/><br/>
            <label for="password">Пароль </label><br/>
            <input id ="password" name="password" type="text" value="${clonedUser.password}"/><br/>
            <label for="firstName">Имя</label><br/>
            <input id="firstName" name="firstName" type="text"/><br/>
            <label for="lastName">Фамилия</label><br/>
            <input id="lastName" name="lastName" type="text"/><br/>
            <label for="email">Электроная почта</label><br/>
            <input id="email" name="email" type="email"/><br/>
            <button type="submit">Изменить</button>
            <form id="cancelForm" action="controller" method="post">
                <input type="hidden" name="command" value="cancelEdit">
                <button type="submit">Отмена</button><br/>
            </form>
        </form>
    </dialog>
    <div class="addUserList">
        <table id="tableAddedUsers">
            <tr class="tableTitle">
                <th colspan="2">Логин</th>
                <th colspan="2">Пароль</th>
                <th colspan="4">Актулизация данных</th>
                <th colspan="2">Блокировать</th>
            </tr>
            <c:forEach var = "elem" items="${adminModerators }" varStatus="loop">
                <tr>
                    <form action="controller" method="POST">
                        <input type="hidden" name="command" value="editUser"/>
                        <input type="hidden" name="adminModeratorID" value="${elem.id }"/>
                        <td colspan="2">
                            <label name="userLogin" >${elem.login}</label>
                        </td>
                        <td  colspan="2">
                            <label name="userPassword">${elem.password }</label>
                        </td>
                        <td>
                            <button type="submit" name="editUserButton">Редактировать</button>
                        </td>
                    </form>
                    <td>
                        <form method="POST" action="controller">
                            <input type="hidden" name ="command" value="deleteUser"/>
                            <input type="hidden" name="adminModeratorID" value="${elem.id }"/>
                            <button type="submit">Удалить</button>
                        </form>
                    </td>
                    <td colspan="2">
                        <c:choose>
                            <c:when test="${elem.blockingStatus==1 }"><button type="submit" style="color:red;">Разблокировать</button></c:when>
                            <c:otherwise><button type="submit" style="color:green;">Блокировать</button></c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            <tr class="tableTitle">
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Электронная почта</th>
                <th>Логин</th>
                <th>Пароль</th>
                <th colspan="3">Актулизация данных</th>
                <th>Блокировать</th>
            </tr>
            <c:forEach var="elem" items="${bettors }">
                <tr>
                    <td><label name="userFirstName">${elem.firstname }</label></td>
                    <td><label name="userLastName">${elem.lastname }</label></td>
                    <td><label name="userEmail">${elem.email}</label></td>
                    <td><label name="userLogin">${elem.login}</label></td>
                    <td><label name="userPassword">${elem.password }</label></td>
                    <td><button type="button" name="editUserButton" onclick="updateUserData()">Редактировать</button></td>
                    <td><button type="button" name="deleteUserButton" >Удалить</button></td>
                    <td>
                        <c:choose>
                            <c:when test="${elem.blockingStatus==1 }"><button type="submit" style="color:red;">Разблокировать</button></c:when>
                            <c:otherwise><button type="submit" style="color:green;">Блокировать</button></c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>