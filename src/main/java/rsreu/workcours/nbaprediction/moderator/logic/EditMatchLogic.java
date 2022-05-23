package rsreu.workcours.nbaprediction.moderator.logic;

import rsreu.workcours.nbaprediction.data.DBType;
import rsreu.workcours.nbaprediction.data.Match;
import rsreu.workcours.nbaprediction.data.dao.DAOFactory;
import rsreu.workcours.nbaprediction.data.dao.MatchDAO;

public class EditMatchLogic {
    public static Match getMatchById(int id){
        Match match = null;
        try(DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)){
            MatchDAO matchDAO = factory.getMatchDAO();
            match = matchDAO.getMatchById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return match;
    }
}
