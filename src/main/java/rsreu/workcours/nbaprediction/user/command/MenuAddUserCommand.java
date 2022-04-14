package rsreu.workcours.nbaprediction.user.command;

import jakarta.servlet.http.HttpServletRequest;
import rsreu.workcours.nbaprediction.actioncommand.ActionCommand;
import rsreu.workcours.nbaprediction.loginlogout.logic.LoginLogic;
import rsreu.workcours.nbaprediction.data.User;

import java.util.List;

public class MenuAddUserCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = "/jsp/commands/adminIndex.jsp";
        List<User> users = LoginLogic.getAllUsers();
        request.setAttribute("users", users);
        return page;
    }
}
