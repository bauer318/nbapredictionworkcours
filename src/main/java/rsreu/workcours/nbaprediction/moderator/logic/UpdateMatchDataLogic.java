package rsreu.workcours.nbaprediction.moderator.logic;

import rsreu.workcours.nbaprediction.data.DBType;
import rsreu.workcours.nbaprediction.data.Match;
import rsreu.workcours.nbaprediction.data.QtTeam;
import rsreu.workcours.nbaprediction.data.dao.*;

import java.sql.Date;

public class UpdateMatchDataLogic {

    public static void editMatch(int matchId, int awayTeamId,int homeTeamId, Date matchDateTime){
        try(DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)){
            MatchDAO matchDAO = factory.getMatchDAO();
            matchDAO.editeMatch(matchId,awayTeamId,homeTeamId,matchDateTime);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void editQtTeam(int idMatch, int idTeam,int newIdTeam){
        try(DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)){
            QtTeamDAO qtTeamDAO = factory.getQtTeamDAO();
            qtTeamDAO.updateQtTeam(idMatch,idTeam,0,0,0,0,0,newIdTeam);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /*
    * We can edit only not saved match, if this is already saved then we must delete this and add other
    * */
    public static boolean canEditMatchDate(int idMatch, int idGuestTeam, int idHomeTeam){
        QtTeam qtGuestTeam = AddMatchLogic.getQtTeamByIdTeamIdMatch(idGuestTeam,idMatch);
        QtTeam qtHomeTeam = AddMatchLogic.getQtTeamByIdTeamIdMatch(idHomeTeam,idMatch);
        if(qtGuestTeam!=null && qtHomeTeam!=null){
            return (
                    qtGuestTeam.getQt1()==0 || qtGuestTeam.getQt2()==0 ||
                            qtGuestTeam.getQt3()==0 || qtGuestTeam.getQt4()==0 ||
                    qtHomeTeam.getQt1()==0 ||qtHomeTeam.getQt2()==0 ||qtHomeTeam.getQt3()==0 ||qtHomeTeam.getQt4()==0);
        }
        if(qtGuestTeam==null || qtHomeTeam==null){
            return true;
        }
        return false;
    }
    public static boolean isExistingMatch(int idTeam, Date date){
        try(DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)){
            MatchDAO matchDAO = factory.getMatchDAO();
            return matchDAO.getMatchByIdTeamDate(idTeam,date)!=null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public static void deleteRantingByIdTeamRantingDate(int idTeam, Date rantingDate){
        try(DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)){
            RantingDAO rantingDAO = factory.getRatingDAO();
            rantingDAO.deleteRantingByIdTeamRantingDate(idTeam,rantingDate);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void deleteResultByIdMatchIdTeam(int idMatch, int idTeam){
        try(DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)){
            ResultDAO resultDAO = factory.getResultDAO();
            resultDAO.deleteResult(idMatch, idTeam);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void deleteQtTeamByIdMatchIdTeam(int idMatch, int idTeam){
        try(DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)){
            QtTeamDAO qtTeamDAO = factory.getQtTeamDAO();
            qtTeamDAO.deleteQtTeam(idMatch, idTeam);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void deleteMatchByIdMatch(int idMatch){
        try(DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)){
            MatchDAO matchDAO = factory.getMatchDAO();
            matchDAO.deleteMatch(idMatch);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
