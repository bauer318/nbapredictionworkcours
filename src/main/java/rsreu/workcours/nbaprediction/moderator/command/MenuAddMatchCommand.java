package rsreu.workcours.nbaprediction.moderator.command;

import jakarta.servlet.http.HttpServletRequest;
import rsreu.workcours.nbaprediction.actioncommand.ActionCommand;

public class MenuAddMatchCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = "/jsp/commands/addMatch.jsp";
        return page;
    }
}
