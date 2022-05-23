package rsreu.workcours.nbaprediction.moderator.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import rsreu.workcours.nbaprediction.actioncommand.ActionCommand;
import rsreu.workcours.nbaprediction.data.Match;
import rsreu.workcours.nbaprediction.moderator.logic.EditMatchLogic;

public class EditMatchCommand implements ActionCommand {
    private static final String PARAM_NAME_ID_MATCH = "matchId";
    @Override
    public String execute(HttpServletRequest request) {
        String page = "/jsp/commands/addMatch.jsp";
        String matchIdStr = request.getParameter(PARAM_NAME_ID_MATCH);
        int matchId = Integer.parseInt(matchIdStr);
        Match clonedMatch = EditMatchLogic.getMatchById(matchId);
        if(clonedMatch!=null){
            HttpSession session = request.getSession(false);
            session.setAttribute("clonedMatch", clonedMatch.clone());
            request.setAttribute("action", "изменить");
            request.setAttribute("regime","Редактирование матча");
            request.setAttribute("open", "open");
        }
        return page;
    }
}
