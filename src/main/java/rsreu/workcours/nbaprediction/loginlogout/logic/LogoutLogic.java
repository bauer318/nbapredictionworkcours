package rsreu.workcours.nbaprediction.loginlogout.logic;

import rsreu.workcours.nbaprediction.data.dao.DAOFactory;
import rsreu.workcours.nbaprediction.data.DBType;
import rsreu.workcours.nbaprediction.data.dao.UserDAO;

public class LogoutLogic {
    public static void setAuthorizationStatus(String login, int status) {
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            UserDAO userDAO = factory.getUserDAO();
            userDAO.setAuthorizationStatusByLogin(login, status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
