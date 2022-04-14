package rsreu.workcours.nbaprediction.actioncommand;

import jakarta.servlet.http.HttpServletRequest;

public interface ActionCommand {
    String execute (HttpServletRequest request);
}
