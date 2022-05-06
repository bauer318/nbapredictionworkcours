package rsreu.workcours.nbaprediction.user.command;

import jakarta.servlet.http.HttpServletRequest;
import rsreu.workcours.nbaprediction.actioncommand.ActionCommand;

public class SaveEditUserCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = "/jsp/commands/adminEditUser.jsp";
        String open = "";
        request.setAttribute("open",open);
        return page;
    }
}
