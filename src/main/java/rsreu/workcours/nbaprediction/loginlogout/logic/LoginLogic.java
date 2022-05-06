package rsreu.workcours.nbaprediction.loginlogout.logic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import rsreu.workcours.nbaprediction.data.*;
import rsreu.workcours.nbaprediction.data.Bettor;
import rsreu.workcours.nbaprediction.data.User;
import rsreu.workcours.nbaprediction.data.dao.BettorDAO;
import rsreu.workcours.nbaprediction.data.dao.DAOFactory;
import rsreu.workcours.nbaprediction.data.dao.UserDAO;

import java.util.List;

public class LoginLogic {
    public static boolean checkLogin(String enterLogin, String enterPass) {
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            UserDAO userDAO = factory.getUserDAO();
            User user = userDAO.getUserByLogin(enterLogin);
            if (user != null) {
                return (user.getPassword().equals(enterPass));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<User> getAllUsers() {
        List<User> users = null;
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            UserDAO userDAO = factory.getUserDAO();
            users = userDAO.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    public static List<Bettor> getAllBettors(){
        List<Bettor> bettors = null;
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            BettorDAO bettorDAO = factory.getBettorDAO();
            bettors = bettorDAO.getAllBettors();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bettors;
    }
    public static void setAuthorizationStatus(String login, int status) {
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            UserDAO userDAO = factory.getUserDAO();
            userDAO.setAuthorizationStatusByLogin(login, status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static UserTypeEnum getUserRoleByLogin(String login) {
        UserTypeEnum role = null;
        try (DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE)) {
            UserDAO userDAO = factory.getUserDAO();
            int groupID = userDAO.getGroupIDByLogin(login);
            role = UserTypeEnum.getUserRole(groupID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return role;
    }
    public static void setUserSession(HttpServletRequest request, String login, UserTypeEnum role) {
        HttpSession session = request.getSession();
        session.setAttribute("login", login);
        session.setAttribute("role", role);
    }
}
