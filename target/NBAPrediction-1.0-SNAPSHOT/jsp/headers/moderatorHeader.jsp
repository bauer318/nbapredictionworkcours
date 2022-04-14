<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title></title>
    <style type="text/css">
        .headerMenu{
            width:80%;
            margin: 0% auto;
        }
        .menuTable{
            margin: 0% auto;
        }
        .menuTable button
        {
            background-color: rgb(20, 18, 41);
            color: white;
            border: none;
            outline: none;
            height: 25px;
        }
        .menu{
            width:100%;
            background-color: rgb(20, 18, 41);
            border: solid 1px white;
            border-radius: 5px;
        }

        .menuTable td{
            border-left: 2px solid transparent;
        }
        .menu button:hover{
            background-color: grey;
        }
        .menu button:focus{
            background-color: grey;
            border-top: solid white 1px;
            border-left: solid white 1px;
            border-right: solid white 1px;
            border-radius: 5px;
        }
        .menu form{
            display : none
        }
    </style>
</head>
<body>
<div class="headerMenu">
    <header class="menu">
        <form id="addMatchMenuForm" method="POST" action="controller">
            <input type="hidden" name="command" value="menuAddMatch">
        </form>
        <form id="updatePointsMenuForm" method="POST" action="controller">
            <input type="hidden" name="command" value="menuUpdatePoints">
        </form>
        <form id="rantingMenuForm" method="POST" action="controller">
            <input type="hidden" name="command" value="menuRanting">
        </form>
        <form id="predicitonMenuForm" method="POST" action="controller">
            <input type="hidden" name="command" value="menuPrediction">
        </form>
        <form id="historyMenuForm" method="POST" action="controller">
            <input type="hidden" name="command" value="menuHistory">
        </form>
        <table class="menuTable">
            <tr>
                <td style="text-align:center;"><button type="submit" form="addMatchMenuForm">Add Match</button></td>
                <td style="text-align:center;"><button type="submit" form="updatePointsMenuForm">Update Points</button></td>
                <td style="text-align:center;"><button type="submit" form="rantingMenuForm">Ranting</button></td>
                <td style="text-align:center;"><button type="submit" form="predicitonMenuForm">Prediction</button></td>
                <td style="text-align:center;"><button type="submit" form="historyMenuForm">History</button></td>
            </tr>
        </table>
    </header>
</div>
</body>
</html>