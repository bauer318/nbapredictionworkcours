<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Add Match</title>
    <style>
        <c:import url="/css/addMatch.css"/>
    </style>
</head>
<body>
<c:import url="/jsp/headers/moderatorHeader.jsp"/>
<div class="centerDiv">
    <div id="addMatchHeader">
        <form action="controller" method="POST" id="addMatchForm">
            <input type="hidden" name="command" value="addMatch">
        </form>
        <table>
            <tr>
                <td>
                    <label for="matchDate">Date: </label><input type="date" name="matchDate" id="matchDate" form="addMatchForm" value="${currentDate }">
                </td>
                <td>
                    <label for="matchTime">Time: </label><input type="time" name="matchTime" id="matchTime" form="addMatchForm" value="${currentTime}">
                </td>
                <td>
                    <select name="team1" id="team1" form="addMatchForm">
                        <c:forEach var="nbaTeams" items="${nbaTeams }">
                            <c:if test="${team1==nbaTeams.key }">
                                <option selected value="${nbaTeams.key }">${nbaTeams.value }</option>
                            </c:if>
                            <c:if test="${team1!=nbaTeams.key }">
                                <option value="${nbaTeams.key }">${nbaTeams.value }</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </td>
                <td><label> - </label></td>
                <td>
                    <select name="team2" id="team2" form="addMatchForm">
                        <c:forEach var="nbaTeams" items="${nbaTeams }">
                            <c:if test="${team2==nbaTeams.key }">
                                <option selected value="${nbaTeams.key }">${nbaTeams.value }</option>
                            </c:if>
                            <c:if test="${team2!=nbaTeams.key }">
                                <option value="${nbaTeams.key }">${nbaTeams.value }</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <button name="btnAddMatch" id="BtnAddMatch" type="submit" form="addMatchForm">Add</button>
                </td>
            </tr>
        </table>
    </div>
    <div id="listAddedMatchs">
        <table>
            <c:forEach var="matchsAdded" items="${matchsAdded }">
                <tr>
                    <td><label>${matchsAdded.strDate }  ${matchsAdded.strTime }</label></td>
                    <td><label>${matchsAdded.strGuestTeam }</label></td>
                    <td><label> - </label></td>
                    <td><label>${matchsAdded.strHomeTeam }</label></td>
                    <td><button type="submit" name="btnEditMatch" id="btnEditMatch" disabled="disabled">Edit</button></td>
                    <td><button type="submit" name="btnDeleteMatch" id="btnDeleteMatch" disabled="disabled">Delete</button></td>
                </tr>
            </c:forEach>
        </table>
        <label>${sameTeams }</label>
    </div>
</div>

</body>
</html>
