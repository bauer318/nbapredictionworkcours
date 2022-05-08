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
    <dialog id="dialog" ${open}>
        <form id="dialogForm" action="controller" method="post">
            <input type="hidden" name="command" value="updateUser"/>
            <input type="hidden" name="isBettor" value="${isBettor}">
            <h4>${regime}</h4>
            <h6>${userRole}</h6>
            <label for="login">Логин </label><br/>
            <input id = "login"name="login" type="text" value="${clonedUser.login}" ${disabled}/><br/>
            <label for="password">Пароль </label><br/>
            <input id ="password" name="password" type="text" value="${clonedUser.password}" ${disabled}/><br/>
            <c:if test="${isBettor}">
            <label for="firstName">Имя</label><br/>
            <input id="firstName" name="firstname" type="text"  value="${clonedUser.firstname}" ${disabled}/><br/>
            <label for="lastName">Фамилия</label><br/>
            <input id="lastName" name="lastname" type="text" value="${clonedUser.lastname}" ${disabled}/><br/>
            <label for="email">Электроная почта</label><br/>
            <input id="email" name="email" type="text" value="${clonedUser.email}" ${disabled}/><br/>
            </c:if>
            <c:if test="${incorrectData}">
                <label>Неправильные веденные данные</label>
            </c:if>
            <c:if test="${isExistLogin}">
                <label>Этот логин уже существует</label>
            </c:if>
            <button type="submit">${action}</button>
            <button type="button" onclick="cancel();">Отмена</button><br/>
        </form>
    </dialog>
    <div class="addUserList">
        <table id="tableAddedUsers">
            <tr class="tableTitle">
                <th colspan="3">Логин</th>
                <th colspan="2">Пароль</th>
                <th colspan="3">Актулизация данных</th>
            </tr>
             <c:forEach var = "elem" items="${adminModerators }" varStatus="loop">
                <tr>
                    <form action="controller" method="POST">
                        <input type="hidden" name="command" value="editUser"/>
                        <input type="hidden" name="userId" value="${elem.id }"/>
                        <td colspan="3">
                            <label name="userLogin">${elem.login}</label>
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
                            <input type="hidden" name="userId" value="${elem.id }"/>
                            <c:choose>
                                <c:when test="${elem.id==id}"> <button type="button" disabled>Удалить</button></c:when>
                                <c:otherwise><button type="submit">Удалить</button></c:otherwise>
                            </c:choose>
                        </form>
                    </td>
                    <td>
                        <form method="post" action="controller">
                            <input type="hidden" name="command" value="blockUnblockUser">
                            <input type="hidden" name="userId" value="${elem.id }"/>
                            <input type="hidden" name="userBlockingStatus" value="${elem.blockingStatus}">
                            <c:choose>
                                    <c:when test="${elem.id==id}">
                                        <button type="button"  disabled>блокировать</button>
                                    </c:when>
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${elem.blockingStatus==1 }"><button type="submit" style="color:red;">Разблокировать</button></c:when>
                                            <c:otherwise><button type="submit" style="color:green;">Блокировать</button></c:otherwise>
                                        </c:choose>
                                    </c:otherwise>
                            </c:choose>
                        </form>
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
            </tr>
            <c:forEach var="bettor" items="${bettors }" varStatus="bettorLop">
                <tr>
                    <form method="post" action="controller">
                        <input type="hidden" name="command" value="editUser"/>
                        <input type="hidden" name="userId" value="${bettor.id }"/>
                        <td><label name="userFirstName">${bettor.firstname }</label></td>
                        <td><label name="userLastName">${bettor.lastname }</label></td>
                        <td><label name="userEmail">${bettor.email}</label></td>
                        <td><label name="userLogin">${bettor.login}</label></td>
                        <td><label name="userPassword">${bettor.password }</label></td>
                        <td><button type="submit" name="editUserButton">Редактировать</button></td>
                    </form>
                    <td>
                        <form method="POST" action="controller">
                            <input type="hidden" name ="command" value="deleteUser"/>
                            <input type="hidden" name="userId" value="${bettor.id }"/>
                            <button type="submit">Удалить</button>
                        </form>
                    </td>
                    <td>
                        <form method="post" action="controller">
                            <input type="hidden" name="command" value="blockUnblockUser">
                            <input type="hidden" name="userId" value="${bettor.id }"/>
                            <input type="hidden" name="userBlockingStatus" value="${bettor.blockingStatus}">
                        <c:choose>
                            <c:when test="${bettor.blockingStatus==1 }"><button type="submit" style="color:red;">Разблокировать</button></c:when>
                            <c:otherwise><button type="submit" style="color:green;">Блокировать</button></c:otherwise>
                        </c:choose>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>