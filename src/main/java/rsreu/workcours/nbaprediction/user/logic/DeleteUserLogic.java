package rsreu.workcours.nbaprediction.user.logic;

import rsreu.workcours.nbaprediction.data.dao.BettorDAO;
import rsreu.workcours.nbaprediction.data.dao.DAOFactory;
import rsreu.workcours.nbaprediction.data.DBType;
import rsreu.workcours.nbaprediction.data.dao.UserDAO;

public class DeleteUserLogic {
    public static void deleteUserByID(int id) {
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            UserDAO userDAO = factory.getUserDAO();
            if(id>0) {
                userDAO.deleteUserByID(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void deleteBettorById(int id){
        try(DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)){
            BettorDAO bettorDAO = factory.getBettorDAO();
            if(id>0){
                bettorDAO.deleteBettorById(id);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
