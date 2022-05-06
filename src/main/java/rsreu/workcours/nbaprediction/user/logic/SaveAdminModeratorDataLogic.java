package rsreu.workcours.nbaprediction.user.logic;

import rsreu.workcours.nbaprediction.data.dao.DAOFactory;
import rsreu.workcours.nbaprediction.data.DBType;
import rsreu.workcours.nbaprediction.data.dao.UserDAO;

public class SaveAdminModeratorDataLogic {
    public static String getLoginByID(int id) {
        String login = "";
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            UserDAO userDAO = factory.getUserDAO();
            if(id>0) {
                login = userDAO.getLoginByID(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return login;
    }
    public static void updateUserByID(int id, String login, String password) {
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            UserDAO userDAO = factory.getUserDAO();
            if(id>0 && !login.isEmpty() && !password.isEmpty()) {
                userDAO.udpdateUserByID(id, login, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
