package rsreu.workcours.nbaprediction.moderator.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import rsreu.workcours.nbaprediction.actioncommand.ActionCommand;
import rsreu.workcours.nbaprediction.data.Match;
import rsreu.workcours.nbaprediction.data.Ranting;
import rsreu.workcours.nbaprediction.datetime.DateTimeWorker;
import rsreu.workcours.nbaprediction.moderator.logic.AddMatchLogic;
import rsreu.workcours.nbaprediction.moderator.logic.RantingLogic;
import rsreu.workcours.nbaprediction.moderator.logic.UpdateMatchDataLogic;
import rsreu.workcours.nbaprediction.moderator.logic.UpdatePointsLogic;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/*
* Command for edit match
* */
public class UpdateMatchDataCommand implements ActionCommand {
    private static final String PARAM_NAME_MATCH_DATE = "dateMatch";
    private static  final String PARAM_NAME_MATCH_TIME = "timeMatch";
    private static final String PARAM_NAME_HOME_TEAM = "homeTeam";
    private static final String PARAM_NAME_AWAY_TEAM = "awayTeam";
    @Override
    public String execute(HttpServletRequest request) {
        String page = "/jsp/commands/addMatch.jsp";
        String homeTeam = request.getParameter(PARAM_NAME_HOME_TEAM);
        String awayTeam = request.getParameter(PARAM_NAME_AWAY_TEAM);
        String matchDateStr = request.getParameter(PARAM_NAME_MATCH_DATE);
        String matchTimeStr = request.getParameter(PARAM_NAME_MATCH_TIME);
        String dateTimeStr = matchDateStr + " " + matchTimeStr + ":00";
        Date dateTime = DateTimeWorker.stringToDateTimeSQL(dateTimeStr);
        int idAwayTeam = 0;
        int idHomeTeam = 0;
        HttpSession session = request.getSession(false);
        Match clonedMatch = (Match) session.getAttribute("clonedMatch");
        int matchId = clonedMatch.getIdMatch();
        boolean isDeleteMatch = (Boolean)session.getAttribute("isDeleteMatch");
        if(isDeleteMatch){
            idAwayTeam = clonedMatch.getIdGuestTeam();
            idHomeTeam = clonedMatch.getIdHomeTeam();
            UpdateMatchDataLogic.deleteRantingByIdTeamRantingDate(idAwayTeam,dateTime);
            UpdateMatchDataLogic.deleteRantingByIdTeamRantingDate(idHomeTeam,dateTime);
            UpdateMatchDataLogic.deleteResultByIdMatchIdTeam(matchId,idAwayTeam);
            UpdateMatchDataLogic.deleteResultByIdMatchIdTeam(matchId,idHomeTeam);
            UpdateMatchDataLogic.deleteQtTeamByIdMatchIdTeam(matchId,idAwayTeam);
            UpdateMatchDataLogic.deleteQtTeamByIdMatchIdTeam(matchId,idHomeTeam);
            UpdateMatchDataLogic.deleteMatchByIdMatch(matchId);
            List<Ranting> rantings = RantingLogic.getAllRantings();
            RantingLogic.setRantings(rantings);
            Map<Integer, Double> mapFullOffensiveAverageTeams = RantingLogic.getFullOffensiveAverageTeams();
            Map<Integer, Double> mapFullDefensiveAverageTeams = RantingLogic.getFullDefensiveAverageTeams();
            RantingLogic.insertNewOffensiveRanting(mapFullOffensiveAverageTeams, dateTime);
            RantingLogic.insertNewDefensiveRanting(mapFullDefensiveAverageTeams, dateTime);
            session.setAttribute("matchsAdded", AddMatchLogic.getMatchsByDate(dateTime));
            session.setAttribute("listGuestTeam", UpdatePointsLogic.getListGuestTeamByDate(dateTime));
            session.setAttribute("listHomeTeam", UpdatePointsLogic.getListHomeTeamByDate(dateTime));
        } else {
            idAwayTeam = Integer.parseInt(awayTeam);
            idHomeTeam = Integer.parseInt(homeTeam);
            if(idAwayTeam==idHomeTeam){
                request.setAttribute("incorrectData",true);
                request.setAttribute("action", "изменить");
                request.setAttribute("open", "open");
            } else if(matchId>0){
                int clonedIdHomeTeam = clonedMatch.getIdHomeTeam();
                int clonedAwayIdTeam = clonedMatch.getIdGuestTeam();
                boolean isStringCurrentDate = (Boolean)session.getAttribute("isStringCurrentDate");
                Date date = null;
                if(isStringCurrentDate){
                    String currentDateStr = (String)session.getAttribute("currentDate");
                    date = DateTimeWorker.stringToDate(currentDateStr);
                }else{
                    LocalDate currentLocalDate = (LocalDate) session.getAttribute("currentDate");
                    date = DateTimeWorker.localDateToDate(currentLocalDate);
                }
                if(UpdateMatchDataLogic.canEditMatchDate(matchId,clonedAwayIdTeam,clonedIdHomeTeam)){
                    boolean isNewTeam = false;
                    boolean canEdit = false;
                    if(clonedIdHomeTeam != idHomeTeam){
                        isNewTeam = true;
                        if(!UpdateMatchDataLogic.isExistingMatch(idHomeTeam,dateTime)){
                            UpdateMatchDataLogic.editQtTeam(matchId,idHomeTeam);
                            canEdit = true;
                        }
                    }
                    if(clonedAwayIdTeam != idAwayTeam){
                        isNewTeam = true;
                        if(!UpdateMatchDataLogic.isExistingMatch(idAwayTeam,dateTime)){
                            UpdateMatchDataLogic.editQtTeam(matchId,idAwayTeam);
                            canEdit = true;
                        }
                    }
                    if(isNewTeam){
                        if(canEdit){
                            UpdateMatchDataLogic.editMatch(matchId,idAwayTeam,idHomeTeam,dateTime);
                        }
                    }else {
                        UpdateMatchDataLogic.editMatch(matchId,clonedAwayIdTeam,clonedIdHomeTeam,dateTime);
                    }
                        if(date != null){
                            session.setAttribute("matchsAdded", AddMatchLogic.getMatchsByDate(date));
                            session.setAttribute("listGuestTeam", UpdatePointsLogic.getListGuestTeamByDate(date));
                            session.setAttribute("listHomeTeam", UpdatePointsLogic.getListHomeTeamByDate(date));
                        }
                    request.setAttribute("incorrectData",false);
                    request.setAttribute("open", "");
                }else {
                    request.setAttribute("needDeleteCurrentMatch", true);
                    request.setAttribute("action", "изменить");
                    request.setAttribute("open", "open");
                }
            }
        }
        return page;
    }

}
