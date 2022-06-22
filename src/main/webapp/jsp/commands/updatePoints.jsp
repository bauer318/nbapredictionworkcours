<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Update Points</title>
    <style>
        <c:import url="/css/updatePoints.css"/>
    </style>
</head>
<body>
<c:import url="/jsp/headers/moderatorHeader.jsp"/>
<div class="centerDiv">
    <div id="listMatchs">
        <table>
            <thead>
            <tr>
                <th>№</th>
                <th>Дата и время</th>
                <th>Команда</th>
                <th>QT1</th>
                <th>QT2</th>
                <th>QT3</th>
                <th>QT4</th>
                <th>Овертайм</th>
            </tr>
            </thead>
            <c:set var="matchsAdded" value="${matchsAdded }"></c:set>
            <c:set var="listGuestTeam" value="${listGuestTeam }"></c:set>
            <c:set var="listHomeTeam" value="${listHomeTeam }"></c:set>
            <c:forEach items="${listGuestTeam }" varStatus="loop">
                <form action="controller" method="POST" id="updatePointsForm" name="updatePointsForm">
                    <input type="hidden" name="command" value="updatePoints"/>
                    <tbody>
                    <tr class="firstRow">
                        <td rowspan="2">${loop.index+1}</td>
                        <td rowspan="2"><label>${matchsAdded[loop.count-1].strDate} ${matchsAdded[loop.count-1].strTime}</label></td>
                        <td><label>${listGuestTeam[loop.index].team }</label></td>
                        <td><input type="number" name="qt11" value="${listGuestTeam[loop.index].qt1}" min="0" max="70"></td>
                        <td><input type="number" name="qt12" value="${listGuestTeam[loop.index].qt2}" min="0" max="70"></td>
                        <td><input type="number" name="qt13" value="${listGuestTeam[loop.index].qt3}" min="0" max="70"></td>
                        <td><input type="number" name="qt14" value="${listGuestTeam[loop.index].qt4}" min="0" max="70"></td>
                        <td><input type="number" name="team1OverTime" value="${listGuestTeam[loop.index].qt5}" min="0" max="70" ></td>
                        <td><button type="submit" name="updateMatch" value="1">Обновлять</button></td>
                        <td><input type="hidden" name="selectedIndex" value="${loop.index}"></td>
                        <td><input type="hidden" name="idMatch" value="${matchsAdded[loop.count-1].idMatch}"></td>
                    </tr>
                    <tr>
                        <td><label>${listHomeTeam[loop.index].team }</label></td>
                        <td><input type="number" name="qt21" value="${listHomeTeam[loop.index].qt1 }" min="0" max="70" ></td>
                        <td><input type="number" name="qt22" value="${listHomeTeam[loop.index].qt2 }" min="0" max="70" ></td>
                        <td><input type="number" name="qt23" value="${listHomeTeam[loop.index].qt3 }" min="0" max="70" ></td>
                        <td><input type="number" name="qt24" value="${listHomeTeam[loop.index].qt4 }" min="0" max="70" ></td>
                        <td><input type="number" name="team2OverTime" value="${listHomeTeam[loop.index].qt5 }" min="0" max="70"></td>
                        <td><button type="submit" name="updateMatch" value="0">Сохранить</button></td>
                    </tr>
                    </tbody>
                </form>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>