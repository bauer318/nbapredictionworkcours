package rsreu.workcours.nbaprediction.moderator.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import rsreu.workcours.nbaprediction.actioncommand.ActionCommand;
import rsreu.workcours.nbaprediction.datetime.DateTimeWorker;
import rsreu.workcours.nbaprediction.moderator.logic.AddMatchLogic;
import rsreu.workcours.nbaprediction.moderator.logic.UpdatePointsLogic;

import java.sql.Date;

public class AddMatchCommand implements ActionCommand {
    private static final String PARAM_NAME_MATCH_DATE = "matchDate";
    private static final String PARAM_NAME_MATCH_TIME = "matchTime";
    private static final String PARAM_NAME_TEAM1 = "team1";
    private static final String PARAM_NAME_TEAM2 = "team2";

    @Override
    public String execute(HttpServletRequest request) {
        String page = "/jsp/commands/addMatch.jsp";
        String guestTeam = request.getParameter(PARAM_NAME_TEAM1);
        String homeTeam = request.getParameter(PARAM_NAME_TEAM2);
        String dateStr = request.getParameter(PARAM_NAME_MATCH_DATE);
        String timeStr = request.getParameter(PARAM_NAME_MATCH_TIME);
        String dateTimeStr = dateStr + " " + timeStr + ":00";
        Date dateTime = DateTimeWorker.stringToDateTimeSQL(dateTimeStr);
        int idGuestTeam = Integer.parseInt(guestTeam);
        int idHomeTeam = Integer.parseInt(homeTeam);
        HttpSession session = request.getSession(false);
        session.setAttribute("currentTime", timeStr);
        session.setAttribute("currentDate", dateStr);
        session.setAttribute("isStringCurrentDate",true);
        request.setAttribute("team1", idGuestTeam);
        request.setAttribute("team2", idHomeTeam);
        Date date = DateTimeWorker.stringToDate(dateStr);
        Date oldDate = (Date)session.getAttribute("selectedDate");
        if(oldDate!=date) {
            UpdatePointsLogic.setQtTeams(AddMatchLogic.getQtTeamsByDate(date));
        }
        session.setAttribute("selectedDate", date);
        if (idGuestTeam != idHomeTeam) {
            if (AddMatchLogic.isNotExistingMatch(idGuestTeam, idHomeTeam, date)) {
                AddMatchLogic.insertMatch(idGuestTeam, idHomeTeam, dateTime);
                int idMatch = AddMatchLogic.getMatchID();
                AddMatchLogic.insertQtTeam(idMatch, idGuestTeam);
                AddMatchLogic.insertQtTeam(idMatch, idHomeTeam);
                UpdatePointsLogic.addQtTeam(idMatch, idGuestTeam, 0, 0, 0, 0,0);
                UpdatePointsLogic.addQtTeam(idMatch, idHomeTeam, 0, 0, 0, 0,0);
            }
        } else {
            request.setAttribute("sameTeams", "select differents teams");
        }
        session.setAttribute("matchsAdded", AddMatchLogic.getMatchsByDate(date));
        session.setAttribute("listGuestTeam", UpdatePointsLogic.getListGuestTeamByDate(date));
        session.setAttribute("listHomeTeam", UpdatePointsLogic.getListHomeTeamByDate(date));
        return page;
    }

}
