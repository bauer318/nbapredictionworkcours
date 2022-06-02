package rsreu.workcours.nbaprediction.bettor;

import jakarta.servlet.http.HttpServletRequest;
import rsreu.workcours.nbaprediction.actioncommand.ActionCommand;
import rsreu.workcours.nbaprediction.data.UserTypeEnum;

public class PredictionCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = "/jsp/commands/prediction.jsp";
        UserTypeEnum.BETTOR.setUserMenu(request);
        return page;
    }
}
