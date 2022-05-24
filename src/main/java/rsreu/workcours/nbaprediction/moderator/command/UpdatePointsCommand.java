package rsreu.workcours.nbaprediction.moderator.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import rsreu.workcours.nbaprediction.actioncommand.ActionCommand;
import rsreu.workcours.nbaprediction.data.QtTeam;
import rsreu.workcours.nbaprediction.moderator.logic.AddPredictionLogic;
import rsreu.workcours.nbaprediction.moderator.logic.UpdatePointsLogic;

import java.sql.Date;
import java.util.List;

public class UpdatePointsCommand implements ActionCommand {
    private static final String PARAM_NAME_SELECTED_INDEX = "selectedIndex";
    private static final String PARAM_NAME_QT11 = "qt11";
    private static final String PARAM_NAME_QT12 = "qt12";
    private static final String PARAM_NAME_QT13 = "qt13";
    private static final String PARAM_NAME_QT14 = "qt14";
    private static final String PARAM_NAME_QT21 = "qt21";
    private static final String PARAM_NAME_QT22 = "qt22";
    private static final String PARAM_NAME_QT23 = "qt23";
    private static final String PARAM_NAME_QT24 = "qt24";
    private static final String PARAM_NAME_GUEST_TEAM_OVERTIME = "team1OverTime";
    private static final String PARAM_NAME_HOME_TEAM_OVERTIME = "team2OverTime";
    private static final String PARAM_NAME_ID_MATCH = "idMatch";
    private static final String PARAM_NAME_UPDATE = "updateMatch";

    @Override
    public String execute(HttpServletRequest request) {
        String page = "/jsp/commands/updatePoints.jsp";
        int selectedIndex = Integer.parseInt(request.getParameter(PARAM_NAME_SELECTED_INDEX));
        int qt11 = Integer.parseInt(request.getParameter(PARAM_NAME_QT11));
        int qt12 = Integer.parseInt(request.getParameter(PARAM_NAME_QT12));
        int qt13 = Integer.parseInt(request.getParameter(PARAM_NAME_QT13));
        int qt14 = Integer.parseInt(request.getParameter(PARAM_NAME_QT14));
        int qt21 = Integer.parseInt(request.getParameter(PARAM_NAME_QT21));
        int qt22 = Integer.parseInt(request.getParameter(PARAM_NAME_QT22));
        int qt23 = Integer.parseInt(request.getParameter(PARAM_NAME_QT23));
        int qt24 = Integer.parseInt(request.getParameter(PARAM_NAME_QT24));
        int updateMatch = Integer.parseInt(request.getParameter(PARAM_NAME_UPDATE));
        int guestTeamOvertime = Integer.parseInt(request.getParameter(PARAM_NAME_GUEST_TEAM_OVERTIME));
        int homeTeamOvertime = Integer.parseInt(request.getParameter(PARAM_NAME_HOME_TEAM_OVERTIME));
        int idMatch = Integer.parseInt(request.getParameter(PARAM_NAME_ID_MATCH));
        int idGuestTeam = UpdatePointsLogic.getIdGuestTeamByIdMatch(idMatch);
        int idHomeTeam = UpdatePointsLogic.getIdHomeTeamByIdMatch(idMatch);
        HttpSession session = request.getSession(false);
        Date date = (Date) session.getAttribute("selectedDate");
        boolean notZeroQt = !UpdatePointsLogic.existZeroQt(qt11, qt12, qt13, qt14)
                && !UpdatePointsLogic.existZeroQt(qt21, qt22, qt23, qt24);

        // Save QtData And Create or Insert Ranting And Update Prediction's result
        if (updateMatch == 0 && notZeroQt) {
            UpdatePointsLogic.updateQtTeam(idMatch, idGuestTeam, qt11, qt12, qt13, qt14, guestTeamOvertime);
            UpdatePointsLogic.updateQtTeam(idMatch, idHomeTeam, qt21, qt22, qt23, qt24, homeTeamOvertime);
            int guestTeamTotalQt = UpdatePointsLogic.getQtSum(qt11, qt12, qt13, qt14, guestTeamOvertime);
            int homeTeamTotalQt = UpdatePointsLogic.getQtSum(qt21, qt22, qt23, qt24, homeTeamOvertime);
            UpdatePointsLogic.addRanting(idGuestTeam, idHomeTeam, guestTeamTotalQt, homeTeamTotalQt, date);
            if (!AddPredictionLogic.isNotExistingResult(idMatch, idGuestTeam)) {
                double average = AddPredictionLogic.getTeamAvg(qt11, qt12, qt13);
                String decision = AddPredictionLogic.getDecision(qt14, average);
                AddPredictionLogic.updateResultByIdMatcIdTeam(idMatch, idGuestTeam, qt14, decision);
            }
            if (!AddPredictionLogic.isNotExistingResult(idMatch, idHomeTeam)) {
                double average = AddPredictionLogic.getTeamAvg(qt21, qt22, qt23);
                String decision = AddPredictionLogic.getDecision(qt24, average);
                AddPredictionLogic.updateResultByIdMatcIdTeam(idMatch, idHomeTeam, qt24, decision);
            }
        }

        QtTeam guestTeam = UpdatePointsLogic.createQtTeam(idMatch, idGuestTeam, qt11, qt12, qt13, qt14,
                guestTeamOvertime);
        QtTeam homeTeam = UpdatePointsLogic.createQtTeam(idMatch, idHomeTeam, qt21, qt22, qt23, qt24, homeTeamOvertime);
        @SuppressWarnings("unchecked")
        List<QtTeam> listGuestTeam = (List<QtTeam>) session.getAttribute("listGuestTeam");
        @SuppressWarnings("unchecked")
        List<QtTeam> listHomeTeam = (List<QtTeam>) session.getAttribute("listHomeTeam");
        if (listGuestTeam != null) {
            listGuestTeam.set(selectedIndex, guestTeam);
        }
        if (listHomeTeam != null) {
            listHomeTeam.set(selectedIndex, homeTeam);
        }
        session.setAttribute("listGuestTeam", listGuestTeam);
        session.setAttribute("listHomeTeam", listHomeTeam);
        return page;
    }
}
