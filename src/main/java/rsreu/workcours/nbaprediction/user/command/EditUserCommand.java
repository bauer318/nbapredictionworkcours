package rsreu.workcours.nbaprediction.user.command;

import jakarta.servlet.http.HttpServletRequest;
import rsreu.workcours.nbaprediction.actioncommand.ActionCommand;
import rsreu.workcours.nbaprediction.loginlogout.logic.LoginLogic;
import rsreu.workcours.nbaprediction.data.Bettor;
import rsreu.workcours.nbaprediction.data.User;
import rsreu.workcours.nbaprediction.user.logic.EditUserLogic;

import java.util.List;

public class EditUserCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = "/jsp/commands/adminEditUser.jsp";
        List<User> adminModerators = EditUserLogic.getAdminModerators();
        request.setAttribute("adminModerators", adminModerators);
        List<Bettor> bettors = LoginLogic.getAllBettors();
        request.setAttribute("bettors", bettors);
        return page;
    }
}
