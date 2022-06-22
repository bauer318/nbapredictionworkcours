<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Prediction</title>
    <style>
        <c:import url="/css/prediction.css"/>
    </style>
</head>
<body>
<c:import url="/jsp/headers/bettorHeader.jsp"/>
<div class="centerDiv">
    <section class="matchListSection">
        <c:forEach var="predictions" items="${predictions}">
        <div class="match">
            <div class="leagueName">
                <h3>
                    NBA
                </h3>
            </div>
            <div class="matchBody">
                <div class="matchTitle">
                    <div class="matchDateTime">
                        <p>
                            ${predictions.strDate}
                        </p>
                    </div>
                    <div class="matchTeams">
                        <p>
                            ${predictions.strGuestTeam} - ${predictions.strHomeTeam}
                        </p>
                    </div>
                </div>
                <div class="matchPrediction">
                    <p>Тотал : <b>${predictions.total}</b></p>
                    <p>Вероятность того, что результат превысит это значение : <b>${predictions.superiority} %</b></p>
                    <progress value="${predictions.superiority}" max="100"/>
                </div>
            </div>
        </div>
        </c:forEach>
    </section>
</div>
<script type="text/javascript" src="betMenu.js"></script>
</body>
</html>