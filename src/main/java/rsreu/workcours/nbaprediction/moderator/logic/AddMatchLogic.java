package rsreu.workcours.nbaprediction.moderator.logic;

import rsreu.workcours.nbaprediction.data.*;
import rsreu.workcours.nbaprediction.data.Match;
import rsreu.workcours.nbaprediction.data.QtTeam;
import rsreu.workcours.nbaprediction.data.dao.DAOFactory;
import rsreu.workcours.nbaprediction.data.dao.MatchDAO;
import rsreu.workcours.nbaprediction.data.dao.QtTeamDAO;
import rsreu.workcours.nbaprediction.data.dao.TeamDAO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddMatchLogic {
    public static Map<Integer, String> getNbaTeams(){
        Map<Integer, String> nbaTeams = new HashMap<>();
        try(DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            TeamDAO teamDAO = factory.getTeamDAO();
            nbaTeams = teamDAO.getAllNbaTeams();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nbaTeams;
    }
    public static List<Match> getMatchsByDate(Date date){
        List<Match> matchs = new ArrayList<>();
        try(DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            MatchDAO matchDAO = factory.getMatchDAO();
            matchs = matchDAO.getMatchsByDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matchs;
    }
    public static String getTeamNameById(int id) {
        String team = null;
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)){
            TeamDAO teamDAO = factory.getTeamDAO();
            team = teamDAO.getTeamNameById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return team;
    }
    public static void insertMatch(int idGuestTeam, int idHomeTeam, Date matchDate) {
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)){
            MatchDAO matchDAO = factory.getMatchDAO();
            matchDAO.insertMatch(idGuestTeam, idHomeTeam, matchDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean isNotExistingMatch(int idGuestTeam, int idHomeTeam, Date date) {
        List<Match> matchs = new ArrayList<>();
        try(DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            MatchDAO matchDAO = factory.getMatchDAO();
            matchs = matchDAO.getMatchsByHomeTeamGuestTeamMatchDate(idGuestTeam, idHomeTeam, date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matchs.isEmpty();
    }
    public static void insertQtTeam(int id_match, int id_team) {
        try(DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)){
            QtTeamDAO qtTeamDAO = factory.getQtTeamDAO();
            qtTeamDAO.insertQtTeam(id_match, id_team);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static int getMatchID() {
        int idMatch=1;
        try(DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            MatchDAO matchDAO = factory.getMatchDAO();
            idMatch = matchDAO.getLastIdMatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idMatch;
    }
    public static List<QtTeam> getQtTeamsByDate(Date date){
        List<QtTeam> qtTeams = new ArrayList<>();
        try(DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            QtTeamDAO qtTeamDAO = factory.getQtTeamDAO();
            qtTeams = qtTeamDAO.getQtTeamByMatchDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qtTeams;
    }

    public static QtTeam getQtTeamByIdTeamIdMatch(int idTeam, int idMatch) {
        QtTeam qtTeam = null;
        try(DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            QtTeamDAO qtTeamDAO = factory.getQtTeamDAO();
            qtTeam = qtTeamDAO.getQtTeamByIdTeamIdMatch(idTeam, idMatch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qtTeam;
    }
}
