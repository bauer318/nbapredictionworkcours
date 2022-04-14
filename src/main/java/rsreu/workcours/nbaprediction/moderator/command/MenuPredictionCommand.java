package rsreu.workcours.nbaprediction.moderator.command;

import jakarta.servlet.http.HttpServletRequest;
import rsreu.workcours.nbaprediction.actioncommand.ActionCommand;

public class MenuPredictionCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = "/jsp/commands/addPronostic.jsp";
        return page;
    }
}
