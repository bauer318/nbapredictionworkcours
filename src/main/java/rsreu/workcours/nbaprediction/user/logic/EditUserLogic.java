package rsreu.workcours.nbaprediction.user.logic;

import rsreu.workcours.nbaprediction.data.dao.DAOFactory;
import rsreu.workcours.nbaprediction.data.DBType;
import rsreu.workcours.nbaprediction.data.dao.UserDAO;
import rsreu.workcours.nbaprediction.data.User;

import java.util.ArrayList;
import java.util.List;

public class EditUserLogic {
    public static List<User> getAdminModerators(){
        List<User> adminModerators = new ArrayList<User>();
        try(DAOFactory daoFactory = DAOFactory.getInstance(DBType.ORACLE)){
            UserDAO userDAO = daoFactory.getUserDAO();
            adminModerators = userDAO.getAdminModer();
        }catch (Exception e){
            e.printStackTrace();
        }
        return adminModerators;
    }
    public static User getUserById(int id){
        User user = null;
        try(DAOFactory daoFactory=DAOFactory.getInstance(DBType.ORACLE)){
            UserDAO userDAO = daoFactory.getUserDAO();
            user = userDAO.getUserById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
    public static boolean isExistUser(User user){
        return user!=null;
    }
}
