<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title></title>
    <style type="text/css">
        .headerMenu{
            width: 100%;
            height: 10%;
            display: flex;
            justify-content: center;
        }
        .menuTable button
        {
            background-color: rgb(20, 18, 41);
            color: white;
            border: none;
            outline: none;
        }
        .menu{
            width:100%;
            height:50%;
            background-color: rgb(20, 18, 41);
            border: solid 1px white;
            border-radius: 5px;
        }

        .menuTable td{
            border-left: 250px solid transparent;
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
        <form id="userListMenuForm" method="POST" action="controller">
            <input type="hidden" name="command" value="menuAddUser">
        </form>
        <form id="editUserMenuForm" method="POST" action="controller">
            <input type="hidden" name="command" value="editUser">
        </form>
        <form id="logoutForm" method="POST" action="controller">
            <input type="hidden" name="command" value="logout">
        </form>
        <table class="menuTable">
            <tr>
                <td><button type="submit" form="userListMenuForm">Добавить пользователь</button></td>
                <td><button type="submit" form="editUserMenuForm">Актуализировать данные пользователей</button></td>
                <td><button type="submit" form="logoutForm">Выход</button></td>
            </tr>
        </table>
    </header>
</div>
</body>
</html>