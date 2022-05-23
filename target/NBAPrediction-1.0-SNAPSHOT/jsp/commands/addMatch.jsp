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
    <script>
        <%@include file="/js/admin.js"%>
    </script>
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
                    <button name="btnAddMatch" id="BtnAddMatch" type="submit" form="addMatchForm">Добавить</button>
                </td>
            </tr>
        </table>
    </div>
    <div id="listAddedMatchs">
        <dialog id="dialog" ${open}>
            <form id="dialogForm" action="controller" method="post">
                <input type="hidden" name="command" value="updateMatchData"/>
                <h4>${regime}</h4>
                <label for="dateMatch">Дата </label><br/>
                <input id = "dateMatch"name="dateMatch" type="date" value="${clonedMatch.matchDate}" ${disabled}/><br/>
                <label for="timeMatch">Время </label><br/>
                <input id ="timeMatch" name="timeMatch" type="time" value="${clonedMatch.strTime}" ${disabled}/><br/>
                <label for="homeTeam">Домашняя команда </label><br/>
                <select name="homeTeam" id="homeTeam">
                    <c:forEach var="nbaTeams" items="${nbaTeams }">
                        <c:if test="${clonedMatch.idHomeTeam==nbaTeams.key }">
                            <option selected value="${nbaTeams.key }">${nbaTeams.value }</option>
                        </c:if>
                        <c:if test="${clonedMatch.idHomeTeam!=nbaTeams.key }">
                            <option value="${nbaTeams.key }">${nbaTeams.value }</option>
                        </c:if>
                    </c:forEach>
                </select><br/>
                <label for="awayTeam">Выезная команда</label><br/>
                <select name="awayTeam" id="awayTeam">
                    <c:forEach var="nbaTeams" items="${nbaTeams }">
                        <c:if test="${clonedMatch.idGuestTeam==nbaTeams.key }">
                            <option selected value="${nbaTeams.key }">${nbaTeams.value }</option>
                        </c:if>
                        <c:if test="${clonedMatch.idGuestTeam!=nbaTeams.key }">
                            <option value="${nbaTeams.key }">${nbaTeams.value }</option>
                        </c:if>
                    </c:forEach>
                </select><br/>
                <c:if test="${incorrectData}">
                    <label>Неправильные веденные данные</label>
                </c:if>
                <c:if test="${needDeleteCurrentMatch}">
                    <p>Это сохраненый матч с данными</p>
                    <p>Надо удалить его!!</p>
                </c:if>
                <button type="submit">${action}</button>
                <button type="button" onclick="cancel();">Отмена</button><br/>
            </form>
        </dialog>
        <table>
            <c:forEach var="matchsAdded" items="${matchsAdded }">
                <tr>
                    <td><label>${matchsAdded.strDate }  ${matchsAdded.strTime }</label></td>
                    <td><label>${matchsAdded.strGuestTeam }</label></td>
                    <td><label> - </label></td>
                    <td><label>${matchsAdded.strHomeTeam }</label></td>
                    <form action="controller" method="POST">
                        <input type="hidden" name="command" value="editMatch">
                        <input type="hidden" name="matchId" value="${matchsAdded.idMatch}">
                        <td><button type="submit" name="btnEditMatch" id="btnEditMatch">изменить</button></td>
                    </form>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="deleteMatch">
                        <input type="hidden" name="matchId" value="${matchsAdded.idMatch}">
                    <td><button type="submit" name="btnDeleteMatch" id="btnDeleteMatch" >удалить</button></td>
                    </form>
                </tr>
            </c:forEach>
        </table>
        <label>${sameTeams }</label>
    </div>
</div>

</body>
</html>
