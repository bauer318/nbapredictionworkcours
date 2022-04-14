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
    <div class="addUserList">
        <table>
            <tr class="tableTitle">
                <th width="20%" colspan="2">Логин</th>
                <th width="20%" colspan="2">Пароль</th>
                <th width="30%" colspan="4">Актулизация данных</th>
                <th width="10" colspan="2">Блокировать</th>
            </tr>
            <c:forEach var = "elem" items="${adminModerators }">
                <tr>
                    <form action="controller" method="POST">
                        <input type="hidden" name="command" value="saveAdminModeratorData"/>
                        <input type="hidden" name="adminModeratorID" value="${elem.id }"/>
                        <td width="10%" colspan="2">
                            <input type="text" name="userLogin" class="userlogin" value="${elem.login }" readonly  required>
                        </td>
                        <td width="10%" colspan="2">
                            <input type="text" name="userPassword" class="userpassword" value="${elem.password }" readonly required>
                        </td>
                        <td>
                            <button type="button" name="editUserButton" onclick="updateUserData()">Редактировать</button>
                        </td>
                        <td>
                            <button type="submit" name="saveUserButton">Сохранить</button>
                        </td>
                    </form>
                    <td>
                        <form method="POST" action="controller">
                            <input type="hidden" name ="command" value="deleteAdminModerator"/>
                            <input type="hidden" name="adminModeratorID" value="${elem.id }"/>
                            <button type="submit">Удалить</button>
                        </form>
                    </td>
                    <td width="5%" colspan="2">
                        <c:choose>
                            <c:when test="${elem.blockingStatus==1 }"><button type="submit" style="color:red;">Разблокировать</button></c:when>
                            <c:otherwise><button type="submit" style="color:green;">Блокировать</button></c:otherwise>
                        </c:choose>
                    </td>
                </tr>

            </c:forEach>
            <tr class="tableTitle">
                <th width="10%">Имя</th>
                <th width="10%">Фамилия</th>
                <th width="10%">Электронная почта</th>
                <th width="10%">Логин</th>
                <th width="10%">Пароль</th>
                <th width="20%" colspan="3">Актулизация данных</th>
                <th width="10">Блокировать</th>
            </tr>
            <c:forEach var="elem" items="${bettors }">
                <tr>
                    <td width="10%"><input type="text" name="userFirstName" class="userfirstname" readonly value="${elem.firstname }"></td>
                    <td width="10%"><input type="text" name="userLastName" class="userlastname" readonly value="${elem.lastname }"></td>
                    <td width="10%"><input type="text" name="userEmail" class="useremail" readonly value="${elem.email }"></td>
                    <td width="10%"><input type="text" name="userLogin" class="userlogin" value="${elem.login }" readonly ></td>
                    <td width="10%"><input type="text" name="userPassword" class="userpassword" value="${elem.password }"readonly></td>
                    <td><button type="button" name="editUserButton" onclick="updateUserData()" form="bettorDataForm">Редактировать</button></td>
                    <td><button type="submit" name="saveUserButton" form="bettorEditForm">Сохранить</button></td>
                    <td><button type="button" name="deleteUserButton" form="bettorDeleteForm">Удалить</button></td>
                    <td width="5%">
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
<footer>

</footer>
</body>
</html>