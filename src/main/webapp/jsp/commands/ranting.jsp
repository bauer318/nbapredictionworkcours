<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Ranting</title>
  <style>
    <c:import url="/css/ranting.css"/>
  </style>
</head>
<body>
<c:import url="/jsp/headers/moderatorHeader.jsp"/>
<div class="centerDiv">
  <form id="rantingForm" method="POST" action="controller">
    <input type="hidden" name="command" value="ranting">
  </form>
  <div>
    <input type="date" name="rantingDate" form="rantingForm" value="${currentRantingDate }">
  </div>
  <div>
    <button type="submit" form="rantingForm" name="addNewRanting" value="0">Set Current Ranting</button>
  </div>
  <div id="offensive">
    <h3>Offensive</h3>
    <table>
      <thead>
      <tr>
        <th>№</th>
        <th>Team</th>
        <th>PM</th>
      </tr>
      </thead>
      <c:forEach var="offensiveRantings" items="${offensiveRantings }" varStatus="loop">
        <tbody>
        <tr class="firstRow">
          <td><label>${loop.count }. </label></td>
          <td><label>${offensiveRantings.teamName }</label></td>
          <td><label>${offensiveRantings.offensiveAverage }</label></td>
        </tr>
        </tbody>
      </c:forEach>
    </table>
  </div>
  <div id="deffensive">
    <h3>Defensive</h3>
    <table>
      <thead>
      <tr>
        <th>№</th>
        <th>Team</th>
        <th>PE</th>
      </tr>
      </thead>
      <c:forEach var="defensiveRantings" items="${defensiveRantings }" varStatus="loop">
        <tbody>
        <tr class="firstRow">
          <td><label>${loop.count }. </label></td>
          <td><label>${defensiveRantings.teamName }</label></td>
          <td><label>${defensiveRantings.defensiveAverage }</label></td>
        </tr>
        </tbody>
      </c:forEach>
    </table>
  </div>
  <div>
    <button type="submit" form="rantingForm" name="addNewRanting" value="1">Add New Ranting</button>
  </div>
</div>
</body>
</html>