<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Prediction</title>
    <style>
        <c:import url="/css/addPronostic.css"/>
    </style>
    <script type="text/javascript">
        <c:import url="/js/addPronostic.js"/>
    </script>
</head>
<body>
<c:import url="/jsp/headers/moderatorHeader.jsp" />
<div class="centerDiv">
    <c:set var="listQtTeams" value="${qtTeams }"></c:set>
    <c:set var="listGuestTeam" value="${listGuestTeam }"></c:set>
    <c:set var="listHomeTeam" value="${listHomeTeam }"></c:set>
    <c:set var="matchsAdded" value="${matchsAdded }"></c:set>
    <c:set var="mapOffensiveRanting" value="${mapOffensiveRanting }"></c:set>
    <c:set var="mapDefensiveRantings" value="${mapDefensiveRantings }"></c:set>
    <c:set var="mapResult" value="${mapResult }"></c:set>
    <c:set var="mapPrevResult" value="${mapPrevResult }"></c:set>
    <c:set var="mapTotalPointsMatch" value="${mapTotalPointsMatch }"></c:set>
    <c:set var="mapConfirmsText" value="${mapConfirmsText}"></c:set>
    <!--FirstMatch-->
    <c:forEach items="${listGuestTeam }" varStatus="loop">
        <c:set var="listPrevResultsGuestTeam"
               value="${mapPrevResult[listGuestTeam[loop.index].idTeam] }"></c:set>
        <c:set var="listPrevResultsHomeTeam"
               value="${mapPrevResult[listHomeTeam[loop.index].idTeam] }"></c:set>
        <c:set var="mapListGuestTeamByAVG" value="${mapListGuestTeamByAVG}"></c:set>
        <c:set var="mapListHomeTeamByAVG" value="${mapListHomeTeamByAVG}"></c:set>
        <c:set var ="listGuestTeamsListMathcByAVG" value="${mapListGuestTeamByAVG[listGuestTeam[loop.index].idTeam] }"></c:set>
        <c:set var ="listHomeTeamsListMathcByAVG" value="${mapListHomeTeamByAVG[listHomeTeam[loop.index].idTeam] }"></c:set>
        <c:set var="mapListGuestTeamResultByAVG" value="${mapListGuestTeamResultByAVG }"></c:set>
        <c:set var="mapListHomeTeamResultByAVG" value="${mapListHomeTeamResultByAVG }"></c:set>
        <c:set var="listHomeTeamListResultByAVG" value="${mapListHomeTeamResultByAVG[listHomeTeam[loop.index].idTeam] }"></c:set>
        <c:set var="listGuestTeamListResultByAVG" value="${mapListGuestTeamResultByAVG[listGuestTeam[loop.index].idTeam] }"></c:set>
        <c:set var="xyGuest" value="${mapXY[listGuestTeam[loop.index].idTeam]}"></c:set>
        <c:set var="xyHome" value="${mapXY[listHomeTeam[loop.index].idTeam]}"></c:set>
        <c:set var="superiorProbabilityGuestTeam" value="${mapDecision[listGuestTeam[loop.index].idTeam]}"></c:set>
        <c:set var="superiorProbabilityHomeTeam" value="${mapDecision[listHomeTeam[loop.index].idTeam]}"></c:set>
        <form action="controller" method="POST" id="predictForm"
              name="predictForm">
            <input type="hidden" name="command" value="addPrediction">
            <div id="${loop.count }" class="mainDiv">
                <div class="matchTitle">
                    <table class="teamTable">
                        <tr>
                            <td class="tdLabel"><label>${matchsAdded[loop.count-1].strGuestTeam }</label></td>
                            <td><label>${listGuestTeam[loop.index].qt1}</label></td>
                            <td><label>${listGuestTeam[loop.index].qt2}</label></td>
                            <td><label>${listGuestTeam[loop.index].qt3}</label></td>
                            <td><button type="submit" name="saveResult" value="0">Predict</button></td>
                            <td>${mapOffensiveRanting[listGuestTeam[loop.index].idTeam]}</td>
                            <td>${mapDefensiveRantings[listGuestTeam[loop.index].idTeam] }</td>
                            <td>${mapResult[listGuestTeam[loop.index].idTeam].averageDifference }</td>
                            <td><input type="hidden" name="selectedIndex"
                                       value="${loop.index}"></td>
                        </tr>
                        <tr>
                            <td><label class="tdLabel">${matchsAdded[loop.count-1].strHomeTeam }</label></td>
                            <td><label>${listHomeTeam[loop.index].qt1}</label></td>
                            <td><label>${listHomeTeam[loop.index].qt2}</label></td>
                            <td><label>${listHomeTeam[loop.index].qt3}</label></td>
                            <td><button type="submit" name="saveResult" value="1">Save</button></td>
                            <td>${mapOffensiveRanting[listHomeTeam[loop.index].idTeam]}</td>
                            <td>${mapDefensiveRantings[listHomeTeam[loop.index].idTeam] }</td>
                            <td>${mapResult[listHomeTeam[loop.index].idTeam].averageDifference }</td>
                        </tr>
                    </table>
                </div>
                <div>
                    <p>${RantingNotDefined }</p>
                </div>
                <div>
                    <button name="btnOutHidden"
                            onclick="outHiddenPronosticResult(this, ${loop.count })"
                            type="button">Display</button>
                </div>
                <div class="pronosticResult">
                    <div>
                        <table>
                            <thead>
                            <tr>
                                <th><label>Команда</label></th>
                                <th><label>Б/М</label></th>
                                <th><label>Сред</label></th>
                                <th><label>Стредний процент%</label></th>
                                <th><label>Y1</label></th>
                                <th><label>X1</label></th>
                                <th><label>Y2</label></th>
                                <th><label>X2</label></th>
                                <th><label>Вероятность(Б)</label></th>
                                <th><label>Обычные забитые очки</label></th>
                                <th><label>Тотал</label></th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><label>${matchsAdded[loop.count-1].strGuestTeam }</label></td>
                                <td><label>${mapResult[listGuestTeam[loop.index].idTeam].decision }</label></td>
                                <td><label>${mapResult[listGuestTeam[loop.index].idTeam].average }</label></td>
                                <td><label>${mapResult[listGuestTeam[loop.index].idTeam].averagePercent }
                                    %</label></td>
                                <td><label>${xyGuest.y1}</label></td>
                                <td><label>${xyGuest.x1}</label></td>
                                <td><label>${xyGuest.y2}</label></td>
                                <td><label>${xyGuest.x2}</label></td>
                                <td><label>${superiorProbabilityGuestTeam} %</label></td>
                                <td><label>${mapResult[listGuestTeam[loop.index].idTeam].qt13Average }</label></td>
                                <td rowspan="2"><label>${mapTotalPointsMatch[listGuestTeam[loop.index].idMatch] }</label></td>
                            </tr>
                            <tr>
                                <td><label>${matchsAdded[loop.count-1].strHomeTeam }</label></td>
                                <td><label>${mapResult[listHomeTeam[loop.index].idTeam].decision }</label></td>
                                <td><label>${mapResult[listHomeTeam[loop.index].idTeam].average }</label></td>
                                <td><label>${mapResult[listHomeTeam[loop.index].idTeam].averagePercent }
                                    %</label></td>
                                <td><label>${xyHome.y1}</label></td>
                                <td><label>${xyHome.x1}</label></td>
                                <td><label>${xyHome.y2}</label></td>
                                <td><label>${xyHome.x2}</label></td>
                                <td><label>${superiorProbabilityHomeTeam } %</label></td>
                                <td><label>${mapResult[listHomeTeam[loop.index].idTeam].qt13Average }</label></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="regimeDetail">
                        <div style="width: 45%">
                            <table>
                                <thead>
                                <tr>
                                    <th>${mapResult[listGuestTeam[loop.index].idTeam].regime }
                                            ${mapResult[listGuestTeam[loop.index].idTeam].offensiveRegime }
                                            ${mapResult[listGuestTeam[loop.index].idTeam].deffensiveRegime }
                                    </th>
                                    <th colspan="4">${matchsAdded[loop.count-1].strGuestTeam }</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${ listPrevResultsGuestTeam}" varStatus ="loopPrev">
                                    <tr>
                                        <td>${listPrevResultsGuestTeam[loopPrev.count-1].decision }</td>
                                        <td>${listPrevResultsGuestTeam[loopPrev.count-1].averageMatchQt4Difference }</td>
                                        <td>${listPrevResultsGuestTeam[loopPrev.count-1].averageDifference }</td>
                                        <td>${listPrevResultsGuestTeam[loopPrev.count-1].average }</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <label>${mapConfirmsText[listGuestTeam[loop.index].idTeam]}</label>
                        </div>
                        <div style="width: 45%">
                            <table>
                                <thead>
                                <tr>
                                    <th>${mapResult[listHomeTeam[loop.index].idTeam].regime }
                                            ${mapResult[listHomeTeam[loop.index].idTeam].offensiveRegime }
                                            ${mapResult[listHomeTeam[loop.index].idTeam].deffensiveRegime }</th>
                                    <th colspan="2" style="text-align: left;">${matchsAdded[loop.count-1].strHomeTeam }</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${ listPrevResultsHomeTeam}" varStatus ="loopPrev">
                                    <tr>
                                        <td>${listPrevResultsHomeTeam[loopPrev.count-1].decision }</td>
                                        <td>${listPrevResultsHomeTeam[loopPrev.count-1].averageMatchQt4Difference }</td>
                                        <td>${listPrevResultsHomeTeam[loopPrev.count-1].averageDifference }</td>
                                        <td>${listPrevResultsHomeTeam[loopPrev.count-1].average }</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <p>${mapConfirmsText[listHomeTeam[loop.index].idTeam]}</p>
                        </div>
                    </div>
                </div>
                <div>
                    <button name="btnOutHiddenOtherMatch"
                            onclick="outHiddenOtherMatch(this, ${loop.count })" type="button">Display</button>
                </div>
                <div class="othersMatch">
                    <!-- GuestTeam -->
                    <div>
                        <table>
                            <thead>
                            <tr>
                                <th><label>${matchsAdded[loop.count-1].strGuestTeam }</label></th>
                                <th><label>Qt1</label></th>
                                <th><label>Qt2</label></th>
                                <th><label>Qt3</label></th>
                                <th><label>Qt4</label></th>
                                <th><label>Схема</label></th>
                                <th><label>Оскорбительный</label></th>
                                <th><label>Оборонительный</label></th>
                                <th><label>Разница</label></th>
                                <th><label>s/i</label></th>
                                <th><label>Средний</label></th>
                                <th><label>%</label></th>
                                <th><label>Больше QT4%</label></th>
                                <th><label>Обычные забитые очки</label></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${listGuestTeamsListMathcByAVG }" varStatus = "loopGuest">
                                <c:set var="listQuestTeamMatchByAVG" value="${ listGuestTeamsListMathcByAVG[loopGuest.count-1]}"></c:set>
                                <c:set var="listGuestTeamResultByAVG" value="${listGuestTeamListResultByAVG[loopGuest.count-1] }"></c:set>
                                <tr>
                                    <td><label>${ listQuestTeamMatchByAVG[0].team}</label></td>
                                    <td><label>${ listQuestTeamMatchByAVG[0].qt1}</label></td>
                                    <td><label>${ listQuestTeamMatchByAVG[0].qt2}</label></td>
                                    <td><label>${ listQuestTeamMatchByAVG[0].qt3}</label></td>
                                    <td><label>${ listQuestTeamMatchByAVG[0].qt4}</label></td>
                                    <td><label>${listGuestTeamResultByAVG[0].regime } ${listGuestTeamResultByAVG[0].offensiveRegime }
                                            ${listGuestTeamResultByAVG[0].deffensiveRegime }</label></td>
                                    <td><label>${listGuestTeamResultByAVG[0].teamOffensive }</label></td>
                                    <td><label>${listGuestTeamResultByAVG[0].opponentDeffensive }</label></td>
                                    <td><label>${listGuestTeamResultByAVG[0].averageDifference }</label></td>
                                    <td><label>${listGuestTeamResultByAVG[0].decision }</label></td>
                                    <td><label>${listGuestTeamResultByAVG[0].average }</label></td>
                                    <td><label>${listGuestTeamResultByAVG[0].averagePercent } %</label></td>
                                    <td><label>${listGuestTeamResultByAVG[0].mostQt4Percent } %</label></td>
                                    <td><label>${listGuestTeamResultByAVG[0].qt13Average }</label></td>
                                </tr>
                                <tr class="lastRow">
                                    <td><label>${ listQuestTeamMatchByAVG[1].team}</label></td>
                                    <td><label>${ listQuestTeamMatchByAVG[1].qt1}</label></td>
                                    <td><label>${ listQuestTeamMatchByAVG[1].qt2}</label></td>
                                    <td><label>${ listQuestTeamMatchByAVG[1].qt3}</label></td>
                                    <td><label>${ listQuestTeamMatchByAVG[1].qt4}</label></td>
                                    <td><label>${listGuestTeamResultByAVG[1].regime } ${listGuestTeamResultByAVG[1].offensiveRegime }
                                            ${listGuestTeamResultByAVG[1].deffensiveRegime }</label></td>
                                    <td><label>${listGuestTeamResultByAVG[1].teamOffensive }</label></td>
                                    <td><label>${listGuestTeamResultByAVG[1].opponentDeffensive }</label></td>
                                    <td><label>${listGuestTeamResultByAVG[1].averageDifference }</label></td>
                                    <td><label>${listGuestTeamResultByAVG[1].decision }</label></td>
                                    <td><label>${listGuestTeamResultByAVG[1].average }</label></td>
                                    <td><label>${listGuestTeamResultByAVG[1].averagePercent } %</label></td>
                                    <td><label>${listGuestTeamResultByAVG[1].mostQt4Percent } %</label></td>
                                    <td><label>${listGuestTeamResultByAVG[1].qt13Average }</label></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- HomeTeam -->
                    <div>
                        <table>
                            <thead>
                            <tr>
                                <th><label>${matchsAdded[loop.count-1].strHomeTeam }</label></th>
                                <th><label>Qt1</label></th>
                                <th><label>Qt2</label></th>
                                <th><label>Qt3</label></th>
                                <th><label>Qt4</label></th>
                                <th><label>Схема</label></th>
                                <th><label>Оскорбительный</label></th>
                                <th><label>Оборонительный</label></th>
                                <th><label>Разница</label></th>
                                <th><label>Б/М</label></th>
                                <th><label>Средний</label></th>
                                <th><label>%</label></th>
                                <th><label>Больше QT4%</label></th>
                                <th><label>Обычные забитые очки</label></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${listHomeTeamsListMathcByAVG }" varStatus = "loopHome">
                                <c:set var="listHomeTeamMatchByAVG" value="${ listHomeTeamsListMathcByAVG[loopHome.count-1]}"></c:set>
                                <c:set var="listGuestTeamResultByAVG" value="${listGuestTeamListResultByAVG[loopHome.count-1] }"></c:set>
                                <tr>
                                    <td><label>${ listHomeTeamMatchByAVG[0].team}</label></td>
                                    <td><label>${ listHomeTeamMatchByAVG[0].qt1}</label></td>
                                    <td><label>${ listHomeTeamMatchByAVG[0].qt2}</label></td>
                                    <td><label>${ listHomeTeamMatchByAVG[0].qt3}</label></td>
                                    <td><label>${ listHomeTeamMatchByAVG[0].qt4}</label></td>
                                    <td><label>${listHomeTeamResultByAVG[0].regime } ${listHomeTeamResultByAVG[0].offensiveRegime }
                                            ${listHomeTeamResultByAVG[0].deffensiveRegime }</label></td>
                                    <td><label>${listHomeTeamResultByAVG[0].teamOffensive }</label></td>
                                    <td><label>${listHomeTeamResultByAVG[0].opponentDeffensive }</label></td>
                                    <td><label>${listHomeTeamResultByAVG[0].averageDifference }</label></td>
                                    <td><label>${listHomeTeamResultByAVG[0].decision }</label></td>
                                    <td><label>${listHomeTeamResultByAVG[0].average }</label></td>
                                    <td><label>${listHomeTeamResultByAVG[0].averagePercent } %</label></td>
                                    <td><label>${listHomeTeamResultByAVG[0].mostQt4Percent } %</label></td>
                                    <td><label>${listHomeTeamResultByAVG[0].qt13Average }</label></td>
                                </tr>
                                <tr class="lastRow">
                                    <td><label>${ listHomeTeamMatchByAVG[1].team}</label></td>
                                    <td><label>${ listHomeTeamMatchByAVG[1].qt1}</label></td>
                                    <td><label>${ listHomeTeamMatchByAVG[1].qt2}</label></td>
                                    <td><label>${ listHomeTeamMatchByAVG[1].qt3}</label></td>
                                    <td><label>${ listHomeTeamMatchByAVG[1].qt4}</label></td>
                                    <td><label>${listHomeTeamResultByAVG[1].regime } ${listHomeTeamResultByAVG[1].offensiveRegime }
                                            ${listHomeTeamResultByAVG[1].deffensiveRegime }</label></td>
                                    <td><label>${listHomeTeamResultByAVG[1].teamOffensive }</label></td>
                                    <td><label>${listHomeTeamResultByAVG[1].opponentDeffensive }</label></td>
                                    <td><label>${listHomeTeamResultByAVG[1].averageDifference }</label></td>
                                    <td><label>${listHomeTeamResultByAVG[1].decision }</label></td>
                                    <td><label>${listHomeTeamResultByAVG[1].average }</label></td>
                                    <td><label>${listHomeTeamResultByAVG[1].averagePercent } %</label></td>
                                    <td><label>${listHomeTeamResultByAVG[1].mostQt4Percent } %</label></td>
                                    <td><label>${listHomeTeamResultByAVG[1].qt13Average }</label></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <!--End first Div-->
        </form>
    </c:forEach>
    <hr>
</div>
</body>
</html>