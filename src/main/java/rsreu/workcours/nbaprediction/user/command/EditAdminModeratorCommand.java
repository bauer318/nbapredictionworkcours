package rsreu.workcours.nbaprediction.user.command;

import jakarta.servlet.http.HttpServletRequest;
import rsreu.workcours.nbaprediction.actioncommand.ActionCommand;

public class EditAdminModeratorCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = "/jsp/commands/adminEditUser.jsp";
        System.out.println(request.getParameter("idMod"));
        return page;
    }
}
