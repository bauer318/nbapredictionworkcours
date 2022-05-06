package rsreu.workcours.nbaprediction.moderator.logic;

import rsreu.workcours.nbaprediction.data.dao.DAOFactory;
import rsreu.workcours.nbaprediction.data.DBType;
import rsreu.workcours.nbaprediction.data.dao.MatchDAO;
import rsreu.workcours.nbaprediction.data.dao.QtTeamDAO;
import rsreu.workcours.nbaprediction.data.Match;
import rsreu.workcours.nbaprediction.data.QtTeam;
import rsreu.workcours.nbaprediction.data.Ranting;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UpdatePointsLogic {
    private static List<QtTeam> qtTeams = new ArrayList<QtTeam>();
    private static List<QtTeam> qtGuestTeams = new ArrayList<QtTeam>();
    private static List<QtTeam> qtHomeTeams = new ArrayList<QtTeam>();

    public static int getIdGuestTeamByIdMatch(int idMatch) {
        int guestTeamId = 0;
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            MatchDAO matchDAO = factory.getMatchDAO();
            guestTeamId = matchDAO.getIdGuestTeamByIdMatch(idMatch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return guestTeamId;
    }

    public static boolean existZeroQt(int qt11, int qt12, int qt13, int qt14) {
        return (qt11 == 0 || qt12 == 0 || qt13 == 0 || qt14 == 0);
    }

    public static int getIdHomeTeamByIdMatch(int idMatch) {
        int homeTeamId = 0;
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            MatchDAO matchDAO = factory.getMatchDAO();
            homeTeamId = matchDAO.getIdHomeTeamByIdMatch(idMatch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return homeTeamId;
    }

    public static QtTeam createQtTeam(int idMatch, int idTeam, int qt1, int qt2, int qt3, int qt4, int qt5) {
        return new QtTeam(idMatch, idTeam, qt1, qt2, qt3, qt4, qt5);
    }

    public static int getQtSum(int... qt) {
        int sum = 0;
        for (int i = 0; i < qt.length; i++) {
            sum += qt[i];
        }
        return sum;
    }

    public static void addRanting(int guestTeamId, int homeTeamId, int guestTeamTotalQt, int homeTeamTotalQt,
                                  Date ranting_date) {
        createRanting(guestTeamId, guestTeamTotalQt, homeTeamTotalQt, ranting_date);
        createRanting(homeTeamId, homeTeamTotalQt, guestTeamTotalQt, ranting_date);

    }

    private static void createRanting(int id_team, int totalPoints, int totalOpponentsPoints, Date date) {
        Ranting ranting = RantingLogic.getLastRantingByTeamIdBeforeDate(id_team, date);
        Ranting firstRanting = RantingLogic.getLastRantingByTeamId(id_team);
        if (firstRanting == null) {
            RantingLogic.insertNewRanking(id_team, date, 1, 0, 0, totalPoints, totalOpponentsPoints);
        } else if (ranting != null) {
            int totalPointsNew = ranting.getTotalPoints() + totalPoints;
            int totalOpponentsPointsNew = ranting.getTotalOpponentsPoints() + totalOpponentsPoints;
            int numberMatchNew = ranting.getMatchNumber() + 1;
            if (RantingLogic.isNotExistingRanting(id_team, date)) {
                RantingLogic.insertNewRanking(id_team, date, numberMatchNew, 0, 0, totalPointsNew,
                        totalOpponentsPointsNew);
            } else {
                RantingLogic.updateRankingByIdTeamRantingDate(id_team, date, numberMatchNew, 0, 0, totalPointsNew,
                        totalOpponentsPointsNew);
            }
        }
    }

    public static List<QtTeam> getQtTeams(QtTeam guestTeam, QtTeam homeTeam, int guestTeamIndex, int homeTeamIndex,
                                          Date date) {
        qtTeams.set(guestTeamIndex, guestTeam);
        qtTeams.set(homeTeamIndex, homeTeam);
        return qtTeams;
    }

    public static void updateQtTeam(int id_match, int id_team, int qt1, int qt2, int qt3, int qt4, int qt5) {
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            QtTeamDAO qtTeamDAO = factory.getQtTeamDAO();
            qtTeamDAO.updateQtTeam(id_match, id_team, qt1, qt2, qt3, qt4, qt5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addQtTeam(int id_match, int id_team, int qt1, int qt2, int qt3, int qt4, int qt5) {
        QtTeam qtTeam = new QtTeam(id_match, id_team, qt1, qt2, qt3, qt4, qt5);
        qtTeams.add(qtTeam);
    }

    public static List<QtTeam> getListGuestTeamByDate(Date date) {
        List<Match> matchsByDate = AddMatchLogic.getMatchsByDate(date);
        List<Integer> listIdGuestTeamByDate = new ArrayList<Integer>();
        List<Integer> listIdMatch = new ArrayList<Integer>();
        List<QtTeam> listGuestTeamByDate = new ArrayList<QtTeam>();
        Iterator<Match> iter = matchsByDate.iterator();
        while (iter.hasNext()) {
            listIdGuestTeamByDate.add(iter.next().getIdGuestTeam());
        }
        iter = matchsByDate.iterator();
        while (iter.hasNext()) {
            listIdMatch.add(iter.next().getIdMatch());
        }
        int size = listIdMatch.size();
        for (int i = 0; i < size; i++) {
            int idTeam = listIdGuestTeamByDate.get(i);
            int idMatch = listIdMatch.get(i);
            QtTeam team = AddMatchLogic.getQtTeamByIdTeamIdMatch(idTeam, idMatch);
            listGuestTeamByDate.add(team);
        }
        return listGuestTeamByDate;

    }

    public static List<QtTeam> getListHomeTeamByDate(Date date) {
        List<Match> matchsByDate = AddMatchLogic.getMatchsByDate(date);
        List<Integer> listIdHomeTeamByDate = new ArrayList<Integer>();
        List<Integer> listIdMatch = new ArrayList<Integer>();
        List<QtTeam> listHomeTeamByDate = new ArrayList<QtTeam>();
        Iterator<Match> iter = matchsByDate.iterator();
        while (iter.hasNext()) {
            listIdHomeTeamByDate.add(iter.next().getIdHomeTeam());
        }
        iter = matchsByDate.iterator();
        while (iter.hasNext()) {
            listIdMatch.add(iter.next().getIdMatch());
        }
        int size = listIdMatch.size();
        for (int i = 0; i < size; i++) {
            int idTeam = listIdHomeTeamByDate.get(i);
            int idMatch = listIdMatch.get(i);
            QtTeam team = AddMatchLogic.getQtTeamByIdTeamIdMatch(idTeam, idMatch);
            listHomeTeamByDate.add(team);
        }
        return listHomeTeamByDate;

    }

    public static List<QtTeam> getQtTeams() {
        return qtTeams;
    }

    public static void setQtTeams(List<QtTeam> qtTeams) {
        UpdatePointsLogic.qtTeams = qtTeams;
    }

    public static List<QtTeam> getQtGuestTeams() {
        return qtGuestTeams;
    }

    public static void setQtGuestTeams(List<QtTeam> qtGuestTeams) {
        UpdatePointsLogic.qtGuestTeams = qtGuestTeams;
    }

    public static List<QtTeam> getQtHomeTeams() {
        return qtHomeTeams;
    }

    public static void setQtHomeTeams(List<QtTeam> qtHomeTeams) {
        UpdatePointsLogic.qtHomeTeams = qtHomeTeams;
    }

}
