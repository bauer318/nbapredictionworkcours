package rsreu.workcours.nbaprediction.user.logic;

import rsreu.workcours.nbaprediction.data.DAOFactory;
import rsreu.workcours.nbaprediction.data.DBType;
import rsreu.workcours.nbaprediction.data.UserDAO;

public class DeleteAdminModeratorLogic {
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
}
