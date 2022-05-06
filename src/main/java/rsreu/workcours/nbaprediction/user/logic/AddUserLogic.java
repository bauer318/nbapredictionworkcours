package rsreu.workcours.nbaprediction.user.logic;

import rsreu.workcours.nbaprediction.data.dao.DAOFactory;
import rsreu.workcours.nbaprediction.data.DBType;
import rsreu.workcours.nbaprediction.data.dao.UserDAO;
import rsreu.workcours.nbaprediction.data.User;

public class AddUserLogic {
    public static boolean isLoginExist(String login) {
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            UserDAO userDAO = factory.getUserDAO();
            User user = userDAO.getUserByLogin(login);
            return (user != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void addAdminModerator(String login, String password, int groupId) {
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            UserDAO userDAO = factory.getUserDAO();
            if(!(login.isEmpty()||password.isEmpty()||groupId<=0)) {
                userDAO.addAdminModerator(login, password, groupId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void addBettor(String firstname, String lastname, String email) {
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            UserDAO userDAO = factory.getUserDAO();
            if(!(firstname.isEmpty()||lastname.isEmpty()||email.isEmpty())) {
                userDAO.addBettor(firstname, lastname, email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
